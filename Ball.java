import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {

    private int x;
    private int y;
    private int diameter = 20;

    private int dx = 3;
    private int dy = -3;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, diameter, diameter);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }

    public void reverseX() {
        dx = -dx;
    }

    public void reverseY() {
        dy = -dy;
    }

    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
        dx = 3;
        dy = -3;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }
}