import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameFrame extends JFrame {

    public GameFrame() {

        setTitle("Brick Breaker - Java");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        setVisible(true);

        SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow());
    }
}