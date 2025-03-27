package com.myapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MyFrame extends JFrame {
    private JLabel  labelRestart;
    private Image backgroundImage;
    private Bird bird;
    private static final int PIPE_SPACING = 150;
    private static final int GAP_HEIGHT = 120;
    private ArrayList<Pipe> pipes;
    private Random random;
    private boolean gameOver = false;
    private final Timer timer;
    private int point = 0;

    public MyFrame() {
        try {
            backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/flappybirdbg.png"))).getImage();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Khonhgj load được hình nền");
            System.exit(0);
        }
        setTitle("Flappy Bird");
        setSize(360, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
                if (bird != null) {
                    bird.draw(g);
                }
                if (pipes != null) {
                    for (Pipe pipe : pipes) {
                        pipe.draw(g);
                    }
                }
                g.setColor(Color.black);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Point: " + point, 20, 30);
                if (gameOver) {
                    g.setColor(Color.RED);
                    g.setFont(new Font("Arial", Font.BOLD, 40));
                    g.drawString("Game Over", 60, 320);
                }
            }
        };
        panel.setLayout(null);

        initializeGame();

        setContentPane(panel);

        timer = new Timer(30, e -> {
            if (!gameOver) {
                bird.update();
                updatePipes();
                checkCollisions();
            }
            panel.repaint();
        });
        timer.start();

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
                    bird.jump();
                }
                if(e.getKeyCode() == KeyEvent.VK_R){
                    resetGame();
                }
            }
        });

        panel.setFocusable(true);
        panel.requestFocusInWindow();
        setVisible(true);
    }

    private void initializeGame() {
        bird = new Bird(100, 320, 1, 24, 24);
        pipes = new ArrayList<>();
        random = new Random();
        point = 0;
        gameOver = false;
        addPipe();
    }

    private void addPipe() {
        int minHeight = 50;
        int maxHeight = getHeight() - GAP_HEIGHT - minHeight;
        int topHeight = random.nextInt(maxHeight - minHeight + 1) + minHeight;
        int bottomY = topHeight + GAP_HEIGHT;
        pipes.add(new Pipe(360, 0, topHeight, true));
        pipes.add(new Pipe(360, bottomY, getHeight() - bottomY, false));
    }

    private void resetGame() {
        // Reset trạng thái game
        pipes.clear(); // Xóa tất cả ống
        point = 0;     // Reset điểm
        gameOver = false;
        bird.reset();  // Reset vị trí và vận tốc của bird
        addPipe();     // Thêm cặp ống mới
        timer.start(); // Khởi động lại timer
    }

    private void updatePipes() {
        for (int i = pipes.size() - 1; i >= 0; i--) {
            Pipe pipe = pipes.get(i);
            pipe.update();
            if (pipe.getX() + pipe.getWidth() < 0) {
                pipes.remove(i);
            }
            if(pipe.isTop() && !pipe.getScored() && bird.getX() + bird.getWidth() > pipe.getX() + pipe.getWidth()){
                point++ ;
                pipe.setScored(true);
            }
        }
        // Thêm ống mới khi ống cuối cùng cách mép phải đủ PIPE_SPACING
        if (!pipes.isEmpty()) {
            Pipe lastPipe = pipes.get(pipes.size() - 1);
            if (lastPipe.getX() + lastPipe.getWidth() < 360 - PIPE_SPACING) {
                addPipe();
            }
        } else {
            addPipe(); // Thêm lại nếu không còn ống nào
        }
    }

    private void checkCollisions() {
        for (Pipe pipe : pipes) {
            if (checkGameOver(pipe, bird)) {
                gameOver = true;
                timer.stop(); // Dừng game loop khi va chạm
                break;
            }
        }
    }

    private boolean checkGameOver(Pipe pipe, Bird bird) {
        // Kiểm tra va chạm
        boolean xOverlap = bird.getX() + bird.getWidth() > pipe.getX() && bird.getX() < pipe.getX() + pipe.getWidth();
        boolean yOverlap;
        if (pipe.isTop()) {
            yOverlap = bird.getY() < pipe.getY() + pipe.getHeight();
        } else {
            yOverlap = bird.getY() + bird.getHeight() > pipe.getY();
        }
        return xOverlap && yOverlap;
    }
}