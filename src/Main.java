import javax.swing.SwingUtilities;

/**
 * Main class.
 * Responsibility: start the program and open the Login Window.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }
}
