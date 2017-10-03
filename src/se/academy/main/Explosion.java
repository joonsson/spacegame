package se.academy.main;


import javax.swing.*;

public class Explosion extends Sprite{
    private Timer timer = new Timer();
    public Explosion(int x, int y) {
        super(x, y);

        initExplosion();
    }
    private void initExplosion() {
        loadImage("explosion1.png");
        getImageDimensions();
        timer.start();
    }
    public Timer getTimer() {
        return timer;
    }
}
