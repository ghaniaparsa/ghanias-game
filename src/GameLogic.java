import java.util.Random;

/**
 * GameLogic class.
 * Responsibility: handle the Rock-Paper-Scissors rules — picking a
 * random move for the computer, and deciding the winner of a round.
 * Unlike Tic-Tac-Toe, this game has no board: each round is just one
 * choice from the player compared against one choice from the computer.
 */
public class GameLogic {

    public static final String ROCK = "ROCK";
    public static final String PAPER = "PAPER";
    public static final String SCISSORS = "SCISSORS";

    private static final String[] CHOICES = {ROCK, PAPER, SCISSORS};

    private Random random;

    public GameLogic() {
        random = new Random();
    }

    /**
     * Picks a random choice for the computer.
     * A simple random move is acceptable, the same way the
     * computer's move is chosen in the Tic-Tac-Toe version.
     */
    public String computerChoice() {
        int index = random.nextInt(CHOICES.length);
        return CHOICES[index];
    }

    /**
     * Checks whether a choice string is one of the 3 valid moves.
     */
    public boolean isValidChoice(String choice) {
        return ROCK.equals(choice) || PAPER.equals(choice) || SCISSORS.equals(choice);
    }

    /**
     * Determines the round result from the player's point of view.
     * Returns "WIN", "LOSE", or "DRAW".
     */
    public String determineResult(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "DRAW";
        }

        boolean playerWins =
                (playerChoice.equals(ROCK) && computerChoice.equals(SCISSORS)) ||
                (playerChoice.equals(PAPER) && computerChoice.equals(ROCK)) ||
                (playerChoice.equals(SCISSORS) && computerChoice.equals(PAPER));

        return playerWins ? "WIN" : "LOSE";
    }
}
