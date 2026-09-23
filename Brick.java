import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Brick {

    public enum Type {
        NORMAL,
        STRONG,
        BONUS,
        UNBREAKABLE
    }

    private int x;
    private int y;

    private final int width = 70;
    private final int height = 25;

    private Type type;
    private int hits;
    private boolean destroyed = false;

    public Brick(int x, int y, Type type) {

        this.x = x;
        this.y = y;
        this.type = type;

        if (type == Type.STRONG) {
            hits = 2;
        } else {
            hits = 1;
        }
    }

    public void draw(Graphics g) {

        if (destroyed) {
            return;
        }

        switch (type) {

            case NORMAL:
                g.setColor(Color.RED);
                break;

            case STRONG:
                g.setColor(Color.BLUE);
                break;

            case BONUS:
                g.setColor(Color.YELLOW);
                break;

            case UNBREAKABLE:
                g.setColor(Color.DARK_GRAY);
                break;
        }

        g.fillRect(x, y, width, height);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    public boolean hit() {

        if (type == Type.UNBREAKABLE) {
            return false;
        }

        hits--;

        if (hits <= 0) {
            destroyed = true;
            return true;
        }

        return false;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public boolean isUnbreakable() {
        return type == Type.UNBREAKABLE;
    }

    public Type getType() {
        return type;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getPoints() {

        switch (type) {

            case BONUS:
                return 30;

            case STRONG:
                return 20;

            case NORMAL:
                return 10;

            default:
                return 0;
        }
    }
}