import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * PlayerService class.
 * Responsibility: handle login, retrieving player data, updating
 * statistics, and retrieving the Top 5 scorers. All SQL/database
 * operations for the players table live in this class only.
 */
public class PlayerService {

    /**
     * Checks username and password against the database.
     * Returns the matching Player on success, or null on failure.
     */
    public Player login(String username, String password) {
        String sql = "SELECT * FROM players WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToPlayer(rs);
                }
            }

        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Re-fetches a player's latest data from the database by id.
     * Used to refresh statistics after they have been updated.
     */
    public Player getPlayerById(int id) {
        String sql = "SELECT * FROM players WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToPlayer(rs);
                }
            }

        } catch (Exception e) {
            System.out.println("Get player error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Updates wins/losses/draws and score after a finished round.
     * result must be one of: "WIN", "LOSE", "DRAW".
     *
     * Scoring rule (can be changed, must match README/video explanation):
     *   WIN  -> +10 points
     *   DRAW -> +3 points
     *   LOSE -> +0 points
     */
    public void updateStatistics(Player player, String result) {
        int additionalScore = 0;
        String sql = "";

        if (result.equalsIgnoreCase("WIN")) {
            additionalScore = 10;
            sql = "UPDATE players SET wins = wins + 1, score = score + ? WHERE id = ?";
        } else if (result.equalsIgnoreCase("LOSE")) {
            additionalScore = 0;
            sql = "UPDATE players SET losses = losses + 1, score = score + ? WHERE id = ?";
        } else if (result.equalsIgnoreCase("DRAW")) {
            additionalScore = 3;
            sql = "UPDATE players SET draws = draws + 1, score = score + ? WHERE id = ?";
        } else {
            System.out.println("Unknown game result: " + result);
            return;
        }

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, additionalScore);
            stmt.setInt(2, player.getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Update statistics error: " + e.getMessage());
        }
    }

    /**
     * Retrieves the Top 5 players ordered by score (then by wins
     * as a tiebreaker), read directly from the database.
     */
    public List<Player> getTopFiveScorers() {
        List<Player> topPlayers = new ArrayList<>();
        String sql = "SELECT * FROM players ORDER BY score DESC, wins DESC LIMIT 5";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                topPlayers.add(mapRowToPlayer(rs));
            }

        } catch (Exception e) {
            System.out.println("Get top scorers error: " + e.getMessage());
        }
        return topPlayers;
    }

    private Player mapRowToPlayer(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String uname = rs.getString("username");
        int wins = rs.getInt("wins");
        int losses = rs.getInt("losses");
        int draws = rs.getInt("draws");
        int score = rs.getInt("score");
        return new Player(id, uname, wins, losses, draws, score);
    }
}
