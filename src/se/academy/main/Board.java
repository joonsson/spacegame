package se.academy.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private Craft craft;
    private ArrayList<Alien> aliens;
    private boolean ingame;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = 1280;
    private final int B_HEIGHT = 720;
    private final int DELAY = 15;

    private final int[][] pos = {
            {2380, 682}, {2500, 59}, {1380, 89},
            {780, 109}, {580, 139}, {680, 239},
            {790, 568}, {760, 600}, {790, 150},
            {980, 209}, {560, 45}, {510, 550},
            {930, 420}, {590, 320}, {530, 640},
            {940, 59}, {990, 500}, {920, 200},
            {900, 259}, {660, 50}, {540, 380},
            {810, 220}, {860, 400}, {740, 180},
            {820, 128}, {490, 170}, {700, 30}
    };


    }
}
