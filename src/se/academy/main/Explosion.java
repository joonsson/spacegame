package se.academy.main;


public class Explosion extends Sprite{
    private int n;
    public Explosion(int x, int y) {
        super(x, y);

        initExplosion();
    }
    private void initExplosion() {
        loadImage("explosion1.png");
        getImageDimensions();
        n = 0;
    }
    public int getN() {
        return n;
    }
    public void increaseN() {
        n++;
    }
}
