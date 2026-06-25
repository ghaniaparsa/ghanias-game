import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * TopScorersFrame class.
 * Responsibility: Swing window for showing the Top 5 scorers
 * using a JTable. Data is retrieved live from the database
 * through PlayerService, never hardcoded.
 */
public class TopScorersFrame extends JFrame {

    private JTable table;
    private PlayerService playerService;

    public TopScorersFrame() {
        playerService = new PlayerService();

        setTitle("Top 5 Scorers");
        setSize(420, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"Username", "Wins", "Losses", "Draws", "Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Get Top 5 players from playerService
        List<Player> topPlayers = playerService.getTopFiveScorers();

        // Add each player data into the table model
        for (Player p : topPlayers) {
            model.addRow(new Object[]{
                    p.getUsername(), p.getWins(), p.getLosses(), p.getDraws(), p.getScore()
            });
        }

        table = new JTable(model);
        table.setRowHeight(24);

        add(new JScrollPane(table));
    }
}
