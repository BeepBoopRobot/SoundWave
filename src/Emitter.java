import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Emitter {
    private double freq;
    private int rings;
    private int pos;
    private boolean atEdge;
    private double speed;
    private ArrayList<Ring> waves;
    private int maxRadius;
    private Image image;
    private int end;

    public Emitter(double speed, double freq, int rings, int maxRadius, int end) {
        this.speed = speed;
        this.maxRadius = maxRadius;
        this.freq = freq;
        this.rings = rings;
        this.pos = 0;
        this.atEdge = false;
        this.end = end;
        this.waves = new ArrayList<>();
        this.image = new Image("/tie.png");
    }

    void generateRings() {
        for (int i = 0; i < rings; i++) {
            waves.add(new Ring(((double) i / rings) * maxRadius, pos+40));
        }
    }

    public void draw(GraphicsContext gc, int h) {
        gc.drawImage(image, pos + 40, h / 2 - 60, 120, 120);
        for (Ring r : waves) {
            gc.strokeOval(r.getX() - r.getRadius() / 2, h / 2 - r.getRadius() / 2, r.getRadius(), r.getRadius());
        }
    }

    public void update() {
        if (pos >= end+maxRadius/2) {
            pos = -160;
        } else {
            pos += speed;
        }
        for (Ring r : waves) {
            if (r.getRadius() >= maxRadius) {
                r.reset(pos + 120);
            } else {
                r.update();
            }
        }
    }

    public void upSpeed() {
        speed += 1;
    }

    public void downSpeed() {
        speed -= 1;
    }
}
