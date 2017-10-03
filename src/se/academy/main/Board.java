package se.academy.main;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private Craft craft;
    private ArrayList<Alien> aliens;
    private ArrayList<Explosion> explosions;
    private boolean ingame;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = 1280;
    private final int B_HEIGHT = 720;
    private final int DELAY = 15;

    private final int[][] pos = {
            {2380, 682}, {2500, 59}, {13800, 89},
            {1280, 109}, {8600, 139}, {6800, 239},
            {1460, 568}, {7060, 600}, {7900, 150},
            {4601, 209}, {5600, 45}, {5100, 550},
            {5800, 420}, {5900, 320}, {5300, 640},
            {1440, 240}, {9900, 500}, {9200, 200},
            {1580, 259}, {6600, 50}, {5400, 380},
            {2620, 220}, {8600, 400}, {7400, 180},
            {1350, 128}, {4900, 170}, {7000, 30}
    };
    public Board() {
        initBoard();
    }
    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        craft = new Craft(ICRAFT_X, ICRAFT_Y);
        initAliens();
        initExplosions();

        timer = new Timer(DELAY, this);
        timer.start();
    }
    public void initAliens() {
        aliens = new ArrayList<>();
        for (int[] p : pos) {
            aliens.add(new Alien(p[0], p[1]));
        }
    }
    public void initExplosions() {
        explosions = new ArrayList<>();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {
            drawObjects(g);
        } else {
            drawGameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }
    private void drawObjects(Graphics g) {
        if (craft.isVisible()) {
            g.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
        }
        ArrayList<Missile> ms = craft.getMissiles();
        for (Missile m: ms) {
            if (m.isVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }
        for (Alien a: aliens) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }
        for (Explosion e: explosions) {
            if (e.isVisible()) {
                g.drawImage(e.getImage(),e.getX(),e.getY(),this);
            }
        }
        g.setColor(Color.WHITE);
        g.drawString("Aliens left: " + aliens.size(), 5, 15);
    }
    private void drawGameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.WHITE);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) /2, B_HEIGHT / 2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        inGame();

        updateCraft();
        updateMissiles();
        updateAliens();
        updateExplosions();

        checkCollisions();

        repaint();
    }
    private void inGame() {
        if (!ingame) {
            timer.stop();
        }
    }
    private void updateCraft() {
        if (craft.isVisible()) {
            craft.move();
        }
    }
    private void updateMissiles() {
        ArrayList<Missile> ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Missile m = ms.get(i);
            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }
    private void updateAliens() {
        if (aliens.isEmpty()) {
            ingame = false;
            return;
        }
        for (int i = 0; i < aliens.size(); i++) {
            Alien a = aliens.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                aliens.remove(i);
            }
        }
    }
    private void updateExplosions() {
        for (Explosion e: explosions) {
            Timer etimer = e.getTimer();
            if (etimer.getDelay() > 500) {
                e.loadImage("explosion2.png");
            }
            if (etimer.getDelay() > 1000) {
                e.loadImage("explosion3.png");
            }
            if (etimer.getDelay() > 1500) {
                e.setVisible(false);
            }
        }
    }
    public void checkCollisions() {
        Rectangle r3 = craft.getBounds();

        for (Alien alien: aliens) {
            Rectangle r2 = alien.getBounds();

            if (r3.intersects(r2)) {
                explosions.add(new Explosion(craft.getX(), craft.getY()));
                craft.setVisible(false);
                alien.setVisible(false);
                ingame = false;
            }
        }
        ArrayList<Missile> ms = craft.getMissiles();
        for (Missile m: ms) {
            Rectangle r1 =m.getBounds();

            for (Alien alien: aliens) {
                Rectangle r2 = alien.getBounds();

                if (r1.intersects(r2)) {
                    explosions.add(new Explosion(alien.getX(), alien.getY()));
                    m.setVisible(false);
                    alien.setVisible(false);
                }
            }
        }
    }
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }
        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }

}
