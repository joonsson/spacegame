package se.academy.main;

import java.util.Random;

public class Alien extends Sprite {
    public static final int INITIAL_X = 2000;

    public Alien(int x, int y) {
        super(x, y);

        initAlien();
    }
    private void initAlien() {
        loadImage("alien.png");
        getImageDimensions();
    }
    public void move() {
        if (x < 0) {
            x = INITIAL_X;
        }
        x -= 10;
    }
}
