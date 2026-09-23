import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Ball ball;
    private Paddle paddle;

    private ArrayList<Brick> bricks;

    private Timer timer;

    private int score = 0;
    private int lives = 3;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private boolean gameOver = false;
    private boolean gameWon = false;

    public GamePanel() {

        setBackground(Color.BLACK);

        setFocusable(true);
        addKeyListener(this);

        ball = new Ball(390, 450);
        paddle = new Paddle(340);

        createBricks();

        timer = new Timer(10, this);
        timer.start();
    }

    private void createBricks() {

        bricks = new ArrayList<>();

        int rows = 5;
        int columns = 10;

        int brickWidth = 70;
        int brickHeight = 25;

        int startX = 40;
        int startY = 70;

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < columns; col++) {

                int x = startX + col * (brickWidth + 5);
                int y = startY + row * (brickHeight + 5);

                bricks.add(new Brick(x, y));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!gameOver && !gameWon) {

            movePaddle();
            moveBall();
            checkCollisions();

            repaint();
        }
    }

    private void movePaddle() {

        if (leftPressed) {
            paddle.moveLeft();
        }

        if (rightPressed) {
            paddle.moveRight(getWidth());
        }
    }

    private void moveBall() {

        ball.move();

        // Left wall
        if (ball.getX() <= 0) {
            ball.reverseX();
        }

        // Right wall
        if (ball.getX() + ball.getDiameter() >= getWidth()) {
            ball.reverseX();
        }

        // Top wall
        if (ball.getY() <= 0) {
            ball.reverseY();
        }

        // Ball falls below screen
        if (ball.getY() > getHeight()) {

            lives--;

            if (lives <= 0) {
                gameOver = true;
            } else {
                ball.reset(390, 450);
            }
        }
    }

    private void checkCollisions() {

        // Paddle collision
        if (ball.getBounds().intersects(paddle.getBounds())) {

            ball.reverseY();
        }

        // Brick collision
        for (Brick brick : bricks) {

            if (!brick.isDestroyed() &&
                    ball.getBounds().intersects(brick.getBounds())) {

                brick.destroy();

                score += 10;

                ball.reverseY();

                break;
            }
        }

        // Check whether all bricks are destroyed
        boolean allDestroyed = true;

        for (Brick brick : bricks) {

            if (!brick.isDestroyed()) {
                allDestroyed = false;
                break;
            }
        }

        if (allDestroyed) {
            gameWon = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Draw paddle
        paddle.draw(g);

        // Draw ball
        ball.draw(g);

        // Draw bricks
        for (Brick brick : bricks) {
            brick.draw(g);
        }

        // Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));

        g.drawString("Score: " + score, 20, 30);
        g.drawString("Lives: " + lives, 700, 30);

        // Game Over
        if (gameOver) {

            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 45));

            g.drawString("GAME OVER", 270, 300);

            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press ENTER to restart", 295, 340);
        }

        // Game Won
        if (gameWon) {

            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 45));

            g.drawString("YOU WIN!", 300, 300);

            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press ENTER to restart", 295, 340);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }

        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        if (key == KeyEvent.VK_ENTER) {

            if (gameOver || gameWon) {
                restartGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void restartGame() {

        score = 0;
        lives = 3;

        gameOver = false;
        gameWon = false;

        ball.reset(390, 450);

        paddle = new Paddle(340);

        createBricks();
    }
}