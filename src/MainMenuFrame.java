import javax.swing.*;
import java.awt.*;

/**
 * MainMenuFrame class.
 * Responsibility: Swing window for the main menu after login.
 * Lets the user navigate to Game, Statistics, or Top Scorers windows.
 */
public class MainMenuFrame extends JFrame {

    private Player currentPlayer;

    private JButton btnStartGame;
    private JButton btnStatistics;
    private JButton btnTopScorers;
    private JButton btnExit;

    public MainMenuFrame(Player player) {
        this.currentPlayer = player;

        setTitle("Rock Paper Scissors - Main Menu");
        setSize(380, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblWelcome = new JLabel("Welcome, " + currentPlayer.getUsername() + "!");
        lblWelcome.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnStartGame = new JButton("Start Game");
        btnStatistics = new JButton("My Statistics");
        btnTopScorers = new JButton("Top 5 Scorers");
        btnExit = new JButton("Exit");

        for (JButton btn : new JButton[]{btnStartGame, btnStatistics, btnTopScorers, btnExit}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(200, 35));
            btn.setPreferredSize(new Dimension(200, 35));
        }

        mainPanel.add(lblWelcome);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(btnStartGame);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(btnStatistics);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(btnTopScorers);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(btnExit);

        add(mainPanel);

        btnStartGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setVisible(true);
            this.dispose();
        });

        btnStatistics.addActionListener(e -> {
            StatisticsFrame statisticsFrame = new StatisticsFrame(currentPlayer);
            statisticsFrame.setVisible(true);
        });

        btnTopScorers.addActionListener(e -> {
            TopScorersFrame topFrame = new TopScorersFrame();
            topFrame.setVisible(true);
        });

        btnExit.addActionListener(e -> System.exit(0));
    }
}
