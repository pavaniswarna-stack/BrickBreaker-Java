import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel
        implements ActionListener, KeyListener {

    // Different states of the game
    private enum GameState {
        MENU,
        PLAYING,
        PAUSED,
        LEVEL_COMPLETE,
        GAME_OVER,
        WIN
    }

    private GameState gameState = GameState.MENU;

    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private Paddle paddle;

    private ArrayList<Ball> balls;
    private ArrayList<Brick> bricks;
    private ArrayList<PowerUp> powerUps;

    private Timer timer;

    private int score = 0;
    private int highScore = 0;
    private int lives = 3;
    private int level = 1;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private Random random = new Random();

    public GamePanel() {

        setBackground(Color.BLACK);

        setFocusable(true);
        addKeyListener(this);

        highScore = ScoreManager.loadHighScore();

        timer = new Timer(10, this);
        timer.start();
    }

    // Start a new game
    private void startGame() {

        score = 0;
        lives = 3;
        level = 1;

        paddle = new Paddle(340, 520);

        balls = new ArrayList<>();
        bricks = new ArrayList<>();
        powerUps = new ArrayList<>();

        createLevel();

        gameState = GameState.PLAYING;

        requestFocusInWindow();
    }

    // Create the current level
    private void createLevel() {

        bricks.clear();
        balls.clear();
        powerUps.clear();

        paddle.resetSize();

        double ballSpeed = 3.0 + (level - 1) * 0.6;

        Ball ball = new Ball(
                390,
                450,
                ballSpeed
        );

        balls.add(ball);

        int rows = 3 + level;
        int columns = 10;

        int startX = 40;
        int startY = 70;

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < columns; col++) {

                int x = startX + col * 75;
                int y = startY + row * 30;

                Brick.Type type;

                // Level 3 contains unbreakable bricks
                if (level >= 3 && row == 0) {

                    type = Brick.Type.UNBREAKABLE;

                // Level 2 and above contain strong bricks
                } else if (level >= 2 && row % 3 == 0) {

                    type = Brick.Type.STRONG;

                // Last row contains bonus bricks
                } else if (row == rows - 1) {

                    type = Brick.Type.BONUS;

                } else {

                    type = Brick.Type.NORMAL;
                }

                bricks.add(
                        new Brick(
                                x,
                                y,
                                type
                        )
                );
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameState == GameState.PLAYING) {

            movePaddle();
            moveBalls();
            movePowerUps();

            checkLevelCompletion();

            repaint();
        }
    }

    // Move paddle
    private void movePaddle() {

        if (leftPressed) {
            paddle.moveLeft();
        }

        if (rightPressed) {
            paddle.moveRight(WIDTH);
        }
    }

    // Move all balls
    private void moveBalls() {

        Iterator<Ball> ballIterator = balls.iterator();

        while (ballIterator.hasNext()) {

            Ball ball = ballIterator.next();

            ball.move();

            // Left wall
            if (ball.getX() <= 0) {
                ball.reverseX();
            }

            // Right wall
            if (ball.getX() + ball.getDiameter() >= WIDTH) {
                ball.reverseX();
            }

            // Top wall
            if (ball.getY() <= 45) {
                ball.reverseY();
            }

            // Paddle collision
            if (ball.getBounds().intersects(paddle.getBounds())
                    && ball.getDy() > 0) {

                double paddleCenter =
                        paddle.getX()
                                + paddle.getWidth() / 2.0;

                double ballCenter =
                        ball.getX()
                                + ball.getDiameter() / 2.0;

                double difference =
                        ballCenter - paddleCenter;

                double normalized =
                        difference
                                / (paddle.getWidth() / 2.0);

                // Keep value between -1 and 1
                normalized =
                        Math.max(-1.0,
                                Math.min(1.0, normalized));

                double newDx =
                        normalized * ball.getSpeed();

                double remaining =
                        ball.getSpeed() * ball.getSpeed()
                                - newDx * newDx;

                double newDy =
                        -Math.sqrt(
                                Math.max(0, remaining)
                        );

                ball.setDirection(
                        newDx,
                        newDy
                );
            }

            // Brick collision
            checkBrickCollision(ball);

            // Ball goes below screen
            if (ball.getY() > HEIGHT) {

                ballIterator.remove();
            }
        }

        // All balls lost
        if (balls.isEmpty()) {

            lives--;

            if (lives <= 0) {

                gameState = GameState.GAME_OVER;

                saveScore();

            } else {

                double ballSpeed =
                        3.0 + (level - 1) * 0.6;

                Ball newBall =
                        new Ball(
                                390,
                                450,
                                ballSpeed
                        );

                balls.add(newBall);
            }
        }
    }

    // Check collision between ball and bricks
    private void checkBrickCollision(Ball ball) {

        for (Brick brick : bricks) {

            if (brick.isDestroyed()) {
                continue;
            }

            if (ball.getBounds()
                    .intersects(brick.getBounds())) {

                // Unbreakable brick
                if (brick.isUnbreakable()) {

                    ball.reverseY();

                    return;
                }

                boolean destroyed = brick.hit();

                ball.reverseY();

                if (destroyed) {

                    score += brick.getPoints();

                    if (score > highScore) {
                        highScore = score;
                    }

                    // 25 percent chance of power-up
                    if (random.nextInt(100) < 25) {

                        PowerUp.Type type =
                                PowerUp.Type.values()[
                                        random.nextInt(
                                                PowerUp.Type.values().length
                                        )
                                ];

                        powerUps.add(
                                new PowerUp(
                                        brick.getBounds().x + 25,
                                        brick.getBounds().y,
                                        type
                                )
                        );
                    }

                    // Slowly increase ball speed
                    for (Ball b : balls) {

                        b.increaseSpeed(0.03);
                    }
                }

                break;
            }
        }
    }

    // Move power-ups
    private void movePowerUps() {

        Iterator<PowerUp> iterator =
                powerUps.iterator();

        while (iterator.hasNext()) {

            PowerUp powerUp = iterator.next();

            powerUp.move();

            // Paddle catches power-up
            if (powerUp.getBounds()
                    .intersects(paddle.getBounds())) {

                applyPowerUp(
                        powerUp.getType()
                );

                iterator.remove();

            } else if (powerUp.getY() > HEIGHT) {

                iterator.remove();
            }
        }
    }

    // Apply power-up effect
    private void applyPowerUp(
            PowerUp.Type type) {

        switch (type) {

            case BIG_PADDLE:

                paddle.increaseSize();

                break;

            case EXTRA_LIFE:

                lives++;

                break;

            case SLOW_BALL:

                for (Ball ball : balls) {

                    ball.setSpeed(
                            Math.max(
                                    2.0,
                                    ball.getSpeed() - 1.0
                            )
                    );
                }

                break;

            case MULTI_BALL:

                if (!balls.isEmpty()) {

                    Ball original =
                            balls.get(0);

                    Ball extra =
                            new Ball(
                                    original.getX(),
                                    original.getY(),
                                    original.getSpeed()
                            );

                    extra.setDirection(
                            -original.getDy(),
                            original.getDx()
                    );

                    balls.add(extra);
                }

                break;
        }
    }

    // Check if level is completed
    private void checkLevelCompletion() {

        boolean completed = true;

        for (Brick brick : bricks) {

            if (!brick.isDestroyed()
                    && !brick.isUnbreakable()) {

                completed = false;
                break;
            }
        }

        if (completed) {

            if (level >= 3) {

                gameState = GameState.WIN;

                saveScore();

            } else {

                gameState =
                        GameState.LEVEL_COMPLETE;
            }
        }
    }

    // Go to next level
    private void nextLevel() {

        level++;

        createLevel();

        gameState = GameState.PLAYING;

        requestFocusInWindow();
    }

    // Save high score
    private void saveScore() {

        if (score > highScore) {

            highScore = score;

            ScoreManager.saveHighScore(
                    score
            );
        }
    }

    // Restart game
    private void restartGame() {

        startGame();
    }

    // Draw everything
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (gameState == GameState.MENU) {

            drawMenu(g);

        } else {

            drawGame(g);

            if (gameState == GameState.PAUSED) {

                drawCenteredMessage(
                        g,
                        "GAME PAUSED",
                        "Press P to Resume"
                );
            }

            if (gameState == GameState.LEVEL_COMPLETE) {

                drawCenteredMessage(
                        g,
                        "LEVEL " + level + " COMPLETE!",
                        "Press ENTER for Level "
                                + (level + 1)
                );
            }

            if (gameState == GameState.GAME_OVER) {

                drawCenteredMessage(
                        g,
                        "GAME OVER",
                        "Press ENTER to Restart"
                );
            }

            if (gameState == GameState.WIN) {

                drawCenteredMessage(
                        g,
                        "YOU WIN!",
                        "Press ENTER to Play Again"
                );
            }
        }
    }

    // Draw main menu
    private void drawMenu(Graphics g) {

        g.setColor(Color.WHITE);

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        48
                )
        );

        String title = "BRICK BREAKER";

        int titleWidth =
                g.getFontMetrics()
                        .stringWidth(title);

        g.drawString(
                title,
                (WIDTH - titleWidth) / 2,
                180
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        g.drawString(
                "Press ENTER to Start",
                275,
                280
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                )
        );

        // ASCII characters only
        g.drawString(
                "LEFT / RIGHT - Move Paddle",
                285,
                330
        );

        g.drawString(
                "P - Pause / Resume",
                315,
                360
        );

        g.drawString(
                "High Score: " + highScore,
                325,
                420
        );
    }

    // Draw the actual game
    private void drawGame(Graphics g) {

        // Header
        g.setColor(Color.WHITE);

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        g.drawString(
                "Score: " + score,
                20,
                30
        );

        g.drawString(
                "Level: " + level,
                350,
                30
        );

        g.drawString(
                "Lives: " + lives,
                680,
                30
        );

        // Draw bricks
        for (Brick brick : bricks) {
            brick.draw(g);
        }

        // Draw paddle
        if (paddle != null) {
            paddle.draw(g);
        }

        // Draw balls
        for (Ball ball : balls) {
            ball.draw(g);
        }

        // Draw power-ups
        for (PowerUp powerUp : powerUps) {
            powerUp.draw(g);
        }
    }

    // Draw messages in the center
    private void drawCenteredMessage(
            Graphics g,
            String title,
            String subtitle) {

        g.setColor(Color.WHITE);

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        40
                )
        );

        int titleWidth =
                g.getFontMetrics()
                        .stringWidth(title);

        g.drawString(
                title,
                (WIDTH - titleWidth) / 2,
                280
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        20
                )
        );

        int subtitleWidth =
                g.getFontMetrics()
                        .stringWidth(subtitle);

        g.drawString(
                subtitle,
                (WIDTH - subtitleWidth) / 2,
                325
        );
    }

    // Keyboard input
    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        // Left arrow
        if (key == KeyEvent.VK_LEFT) {

            leftPressed = true;
        }

        // Right arrow
        if (key == KeyEvent.VK_RIGHT) {

            rightPressed = true;
        }

        // ENTER
        if (key == KeyEvent.VK_ENTER) {

            if (gameState == GameState.MENU) {

                startGame();

            } else if (gameState
                    == GameState.LEVEL_COMPLETE) {

                nextLevel();

            } else if (gameState == GameState.GAME_OVER
                    || gameState == GameState.WIN) {

                restartGame();
            }
        }

        // P = Pause / Resume
        if (key == KeyEvent.VK_P) {

            if (gameState == GameState.PLAYING) {

                gameState = GameState.PAUSED;

            } else if (gameState == GameState.PAUSED) {

                gameState = GameState.PLAYING;
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
}