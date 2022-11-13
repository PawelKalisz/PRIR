package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static com.company.JuliaSet.*;
import static com.company.JuliaSet.MAX_ITERATIONS;
import static com.company.licz.getImage;


class licz extends Thread{

    public static BufferedImage image;
    @Override
    public void run()
    {
        int w = 1920;
        int h = 1080;
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                double zx = 1.5 * (x - w / 2) / (0.5 * ZOOM * w) + MOVE_X;
                double zy = ((y - (h / 2)) / (0.5 * ZOOM * h)) + JuliaSet.MOVE_Y;
                float i = MAX_ITERATIONS;
                while (zx * zx + zy * zy < 4 && i > 0) {
                    double tmp = zx * zx - zy * zy + CX;
                    zy = 2.0 * zx * zy + CY;
                    zx = tmp;
                    i--;
                }
                int c = Color.HSBtoRGB((MAX_ITERATIONS / i) % 1, 1, i > 0 ? 1 : 0);
                image.setRGB(x, y, c);
                setImage(image);
            }
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public static BufferedImage getImage() {
        return image;
    }
}


public class JuliaSet extends JPanel {
    public static final int MAX_ITERATIONS = 300;
    public static final double ZOOM = 1;
    public static final double CX = -0.7;
    public static final double CY = 0.27015;
    public static final double MOVE_X = 0;
    public static final double MOVE_Y = 0;

    public JuliaSet() {
        setPreferredSize(new Dimension(1920, 1080));
        setBackground(Color.white);
    }

    void drawJuliaSet(Graphics2D g) {
        BufferedImage image = getImage();
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        drawJuliaSet(g);
    }

    public static void main(String[] args) throws InterruptedException {

        licz licz = new licz();
        licz.start();
        licz.join();
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("Julia Set");
            f.setResizable(false);
            f.add(new JuliaSet(), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}