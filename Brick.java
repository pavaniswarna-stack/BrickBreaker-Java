import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Brick {

    private int x;
    private int y;

    private int width = 70;
    private int height = 25;

    private boolean destroyed = false;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {

        if (!destroyed) {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void destroy() {
        destroyed = true;
    }
}