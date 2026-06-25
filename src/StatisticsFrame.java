import javax.swing.*;
import java.awt.*;

/**
 * StatisticsFrame class.
 * Responsibility: Swing window for showing the logged-in player's
 * personal statistics, freshly read from the database.
 */
public class StatisticsFrame extends JFrame {

    private Player currentPlayer;
    private PlayerService playerService;

    private JLabel lblUsername;
    private JLabel lblWins;
    private JLabel lblLosses;
    private JLabel lblDraws;
    private JLabel lblScore;
    private JButton btnRefresh;
    private JButton btnClose;

    public StatisticsFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();

        setTitle("My Statistics");
        setSize(340, 280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblTitle = new JLabel("Player Statistics");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(lblTitle, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1; mainPanel.add(new JLabel("Username:"), gbc);
        lblUsername = new JLabel("-");
        gbc.gridx = 1; mainPanel.add(lblUsername, gbc);

        gbc.gridx = 0; gbc.gridy = 2; mainPanel.add(new JLabel("Wins:"), gbc);
        lblWins = new JLabel("-");
        gbc.gridx = 1; mainPanel.add(lblWins, gbc);

        gbc.gridx = 0; gbc.gridy = 3; mainPanel.add(new JLabel("Losses:"), gbc);
        lblLosses = new JLabel("-");
        gbc.gridx = 1; mainPanel.add(lblLosses, gbc);

        gbc.gridx = 0; gbc.gridy = 4; mainPanel.add(new JLabel("Draws:"), gbc);
        lblDraws = new JLabel("-");
        gbc.gridx = 1; mainPanel.add(lblDraws, gbc);

        gbc.gridx = 0; gbc.gridy = 5; mainPanel.add(new JLabel("Score:"), gbc);
        lblScore = new JLabel("-");
        gbc.gridx = 1; mainPanel.add(lblScore, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnRefresh = new JButton("Refresh");
        btnClose = new JButton("Close");
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnClose);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);

        btnRefresh.addActionListener(e -> loadStatistics());
        btnClose.addActionListener(e -> dispose());

        loadStatistics();
    }

    private void loadStatistics() {
        Player refreshed = playerService.getPlayerById(currentPlayer.getId());
        if (refreshed == null) {
            refreshed = currentPlayer;
        }
        lblUsername.setText(refreshed.getUsername());
        lblWins.setText(String.valueOf(refreshed.getWins()));
        lblLosses.setText(String.valueOf(refreshed.getLosses()));
        lblDraws.setText(String.valueOf(refreshed.getDraws()));
        lblScore.setText(String.valueOf(refreshed.getScore()));
    }
}
