package se.academy.main;

public class Missile extends Sprite {
    private final int BOARD_WIDTH = 1280;
    private final int MISSILE_SPEED = 20;

    public Missile(int x, int y) {
        super(x, y);

        initMissile();
    }
    private void initMissile() {
        loadImage("missile.png");
        getImageDimensions();
    }
    public void move() {
        x += MISSILE_SPEED;

        if (x > BOARD_WIDTH) {
            vis = false;
        }
    }
}
