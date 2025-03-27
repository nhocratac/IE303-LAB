package com.myapp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class Bird {
    private int x,y;
    private Image image;
    private int speed;
    private int width = 34;
    private int height = 24;
    private int velocity = 0; // Vận tốc theo phương thẳng đứng
    private final int GRAVITY = 1; // Trọng lực
    private final int JUMP_VELOCITY = -10; // Vận tốc khi nhảy lên

    public Bird(int x, int y, int speed, int width, int height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
        try {
            image = new   ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/flappybird.png"))).getImage();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("khoogn tai duoc anh cua chim");
            System.exit(0);
        }
    }

    public void draw(Graphics g) {
        if(image != null) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            g.setColor(Color.black);
            g.fillOval(x, y, width, height);
        }
    }

    public void update ( ) {
        velocity += GRAVITY;
        y += velocity;
        // Giới hạn không cho bird rơi ra ngoài màn hình (tùy chọn)
        if (y > 640 - height) {
            y = 640 - height;
            velocity = 0;
        }
        if (y < 0) {
            y = 0;
            velocity = 0;
        }
    }

    public void jump() {
        velocity = JUMP_VELOCITY; // Nhảy lên khi nhấn phím
    }

    // getter va setter
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public void reset() {
        this.velocity = 0;
        this.x =100;
        this.y =320;
        this.speed = 1;
        this.width = 24;
        this.height = 24;
    }
}
