import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {

    private double x;
    private double y;

    private double dx;
    private double dy;

    private final int diameter = 18;

    private double speed;

    public Ball(double x, double y, double speed) {
        this.x = x;
        this.y = y;

        this.speed = speed;

        dx = speed;
        dy = -speed;
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) x, (int) y, diameter, diameter);
    }

    public Rectangle getBounds() {
        return new Rectangle(
                (int) x,
                (int) y,
                diameter,
                diameter
        );
    }

    public void reverseX() {
        dx = -dx;
    }

    public void reverseY() {
        dy = -dy;
    }

    public void setDirection(double dx, double dy) {

        double length = Math.sqrt(dx * dx + dy * dy);

        if (length == 0) {
            return;
        }

        this.dx = (dx / length) * speed;
        this.dy = (dy / length) * speed;
    }

    public void setSpeed(double speed) {

        double length = Math.sqrt(dx * dx + dy * dy);

        if (length == 0) {
            this.speed = speed;
            return;
        }

        this.speed = speed;

        dx = (dx / length) * speed;
        dy = (dy / length) * speed;
    }

    public void increaseSpeed(double amount) {
        setSpeed(speed + amount);
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public double getSpeed() {
        return speed;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public void reset(double x, double y, double speed) {

        this.x = x;
        this.y = y;

        this.speed = speed;

        dx = speed;
        dy = -speed;
    }
}
