import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PowerUp {

    public enum Type {
        BIG_PADDLE,
        EXTRA_LIFE,
        SLOW_BALL,
        MULTI_BALL
    }

    private int x;
    private int y;

    private final int size = 20;
    private final int speed = 3;

    private Type type;

    public PowerUp(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void move() {
        y += speed;
    }

    public void draw(Graphics g) {

        switch (type) {

            case BIG_PADDLE:
                g.setColor(Color.GREEN);
                break;

            case EXTRA_LIFE:
                g.setColor(Color.PINK);
                break;

            case SLOW_BALL:
                g.setColor(Color.CYAN);
                break;

            case MULTI_BALL:
                g.setColor(Color.MAGENTA);
                break;
        }

        g.fillOval(x, y, size, size);

        g.setColor(Color.WHITE);
        g.drawOval(x, y, size, size);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public Type getType() {
        return type;
    }

    public int getY() {
        return y;
    }
}