import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle {

    private int x;
    private int y;

    private int width = 120;
    private final int height = 15;

    private final int speed = 7;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        x -= speed;

        if (x < 0) {
            x = 0;
        }
    }

    public void moveRight(int panelWidth) {

        x += speed;

        if (x + width > panelWidth) {
            x = panelWidth - width;
        }
    }

    public void increaseSize() {
        width = Math.min(width + 30, 200);
    }

    public void resetSize() {
        width = 120;
    }

    public void draw(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRoundRect(x, y, width, height, 10, 10);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getY() {
        return y;
    }
}