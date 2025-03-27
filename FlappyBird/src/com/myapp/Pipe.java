package com.myapp;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Pipe {
    private int x, y;
    private int height;
    private final int WIDTH = 52; // Chiều rộng ống (chuẩn Flappy Bird)
    private final int SPEED = 3; // Tốc độ di chuyển
    private Image pipeImage;
    private boolean isTop; // Ống trên hay dưới
    private boolean scored = false;

    public Pipe(int x, int y, int height, boolean isTop) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.isTop = isTop;
        try {
            String imagePath = isTop ? "/resources/toppipe.png" : "/resources/bottompipe.png";
            pipeImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))).getImage();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Không load được hình pipe");
        }
    }

    public void draw(Graphics g) {
        if (pipeImage != null) {
            g.drawImage(pipeImage, x, y, WIDTH, height, null);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, WIDTH, height);
        }
    }

    public void update() {
        x -= SPEED; // Di chuyển sang trái
    }

    public  boolean isTop() {return isTop;}
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return WIDTH; }
    public int getHeight() { return height; }
    public boolean getScored() { return scored; }
    public void setScored(boolean scored) { this.scored = scored; }
}
