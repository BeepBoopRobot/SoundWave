public class Emitter {
    public Emitter(double speed, double freq, int rings) {
        this.speed = speed;
        this.freq = freq;
        this.rings = rings;
        this.pos = 0;
        this.atEdge = false;
    }

    private double freq;
    private int rings;
    private int pos;
    private boolean atEdge;
    private double speed;
}
