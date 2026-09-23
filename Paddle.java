import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle {

    private int x;
    private final int y = 520;

    private final int width = 120;
    private final int height = 15;

    private int speed = 6;

    public Paddle(int x) {
        this.x = x;
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

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
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
}