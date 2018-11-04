public class Ring {
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    private double radius;
    private int x;

    Ring(double radius, int x) {
        this.radius = radius;
        this.x = x;
    }

    public void reset(int newX) {
        radius = 0;
        this.x = newX;
    }

    void update() {
        this.radius += 16;
    }
}
