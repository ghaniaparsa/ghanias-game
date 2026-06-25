import javax.swing.*;
import java.awt.*;

/**
 * GameFrame class.
 * Responsibility: Swing window for playing Rock-Paper-Scissors.
 * Connects the choice buttons to GameLogic, and updates the
 * database statistics through PlayerService after each round.
 */
public class GameFrame extends JFrame {

    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;

    private JButton btnRock;
    private JButton btnPaper;
    private JButton btnScissors;

    private JLabel lblPlayerChoice;
    private JLabel lblComputerChoice;
    private JLabel lblResult;

    private JButton btnBackToMenu;

    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();

        setTitle("Rock Paper Scissors - Game (" + currentPlayer.getUsername() + ")");
        setSize(420, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel outerPanel = new JPanel(new BorderLayout(10, 10));
        outerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblInstruction = new JLabel("Choose Rock, Paper, or Scissors:", SwingConstants.CENTER);
        lblInstruction.setFont(new Font("SansSerif", Font.BOLD, 14));
        outerPanel.add(lblInstruction, BorderLayout.NORTH);

        // Choice buttons
        JPanel choicePanel = new JPanel(new GridLayout(1, 3, 10, 0));
        btnRock = new JButton("Rock");
        btnPaper = new JButton("Paper");
        btnScissors = new JButton("Scissors");

        for (JButton btn : new JButton[]{btnRock, btnPaper, btnScissors}) {
            btn.setFont(new Font("SansSerif", Font.BOLD, 14));
            btn.setPreferredSize(new Dimension(110, 60));
        }
        choicePanel.add(btnRock);
        choicePanel.add(btnPaper);
        choicePanel.add(btnScissors);

        // Result display
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        lblPlayerChoice = new JLabel("Your Choice: -", SwingConstants.CENTER);
        lblComputerChoice = new JLabel("Computer's Choice: -", SwingConstants.CENTER);
        lblResult = new JLabel("Make your move!", SwingConstants.CENTER);
        lblResult.setFont(new Font("SansSerif", Font.BOLD, 16));

        for (JLabel lbl : new JLabel[]{lblPlayerChoice, lblComputerChoice, lblResult}) {
            lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        resultPanel.add(lblPlayerChoice);
        resultPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        resultPanel.add(lblComputerChoice);
        resultPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        resultPanel.add(lblResult);

        JPanel centerPanel = new JPanel(new BorderLayout(0, 10));
        centerPanel.add(choicePanel, BorderLayout.NORTH);
        centerPanel.add(resultPanel, BorderLayout.CENTER);
        outerPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnBackToMenu = new JButton("Back to Menu");
        bottomPanel.add(btnBackToMenu);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(outerPanel);

        btnRock.addActionListener(e -> handlePlayerChoice(GameLogic.ROCK));
        btnPaper.addActionListener(e -> handlePlayerChoice(GameLogic.PAPER));
        btnScissors.addActionListener(e -> handlePlayerChoice(GameLogic.SCISSORS));

        btnBackToMenu.addActionListener(e -> {
            MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
            menuFrame.setVisible(true);
            this.dispose();
        });
    }

    private void handlePlayerChoice(String playerChoice) {
        // Disable choice buttons while the computer "decides"
        setChoiceButtonsEnabled(false);

        lblPlayerChoice.setText("Your Choice: " + playerChoice);
        lblComputerChoice.setText("Computer's Choice: ...");
        lblResult.setText("Computer is choosing...");

        // Small delay purely for user experience, same idea as the
        // computer-move delay used in the Tic-Tac-Toe version.
        Timer timer = new Timer(500, e -> revealResult(playerChoice));
        timer.setRepeats(false);
        timer.start();
    }

    private void revealResult(String playerChoice) {
        // Generate the computer's move
        String computerChoice = gameLogic.computerChoice();
        lblComputerChoice.setText("Computer's Choice: " + computerChoice);

        // Decide the round's result
        String result = gameLogic.determineResult(playerChoice, computerChoice);

        String message;
        switch (result) {
            case "WIN":
                message = "You win! (+10 points)";
                break;
            case "LOSE":
                message = "You lose! Computer wins. (+0 points)";
                break;
            default:
                message = "It's a draw! (+3 points)";
                break;
        }
        lblResult.setText(message);

        // Update database statistics after the round ends
        playerService.updateStatistics(currentPlayer, result);

        setChoiceButtonsEnabled(true);

        JOptionPane.showMessageDialog(this, "Round result: " + result + "\n" + message);
    }

    private void setChoiceButtonsEnabled(boolean enabled) {
        btnRock.setEnabled(enabled);
        btnPaper.setEnabled(enabled);
        btnScissors.setEnabled(enabled);
    }
}
