/**
 * Player class.
 * Responsibility: store player data such as id, username, wins,
 * losses, draws, and score. This is a simple model class with no
 * database or GUI logic inside it.
 */
public class Player {

    private int id;
    private String username;
    private int wins;
    private int losses;
    private int draws;
    private int score;

    public Player(int id, String username, int wins, int losses, int draws, int score) {
        this.id = id;
        this.username = username;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public int getScore() {
        return score;
    }
}
