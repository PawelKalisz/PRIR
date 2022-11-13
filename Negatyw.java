package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

import static com.company.Negatyw.height;
import static com.company.Negatyw.width;


class licz1 extends Thread{
    public static BufferedImage image1;
    public licz1(BufferedImage image1)
    {
        this.image1 = image1;
    }
    @Override
    public void run()
    {
        System.out.println(height);
        System.out.println(width);
        for(int i=1; i<(height-1)/2; i++)
        {
            for (int j = 1; j < (width - 1)/2; j++) {

                //odczyt składowych koloru RGB
                Color c = new Color(image1.getRGB(j, i));
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();

                int final_red, final_green, final_blue;

                final_red = 255 - red;
                final_green = 255 - green;
                final_blue = 255 - blue;
                Color newColor = new Color(final_red, final_green, final_blue);
                image1.setRGB(j, i, newColor.getRGB());

            }
        }
        File ouptut = new File("negatyw.jpg");
        try {
            ImageIO.write(image1, "jpg", ouptut);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class licz2 extends Thread{
    public static BufferedImage image1;
    public licz2(BufferedImage image1)
    {
        this.image1 = image1;
    }
    @Override
    public void run()
    {
        System.out.println(height);
        System.out.println(width);
        for(int i=(height-1)/2; i<height-1; i++)
        {
            for (int j = (width - 1)/2; j < width - 1; j++) {

                //odczyt składowych koloru RGB
                Color c = new Color(image1.getRGB(j, i));
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();

                int final_red, final_green, final_blue;

                final_red = 255 - red;
                final_green = 255 - green;
                final_blue = 255 - blue;
                Color newColor = new Color(final_red, final_green, final_blue);
                image1.setRGB(j, i, newColor.getRGB());

            }
        }
        File ouptut = new File("negatyw.jpg");
        try {
            ImageIO.write(image1, "jpg", ouptut);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class licz3 extends Thread{
    public static BufferedImage image1;
    public licz3(BufferedImage image1)
    {
        this.image1 = image1;
    }
    @Override
    public void run()
    {
        System.out.println(height);
        System.out.println(width);
        for(int i=(height-1)/2; i<height-1; i++)
        {
            for (int j = 1; j < (width- 1)/2; j++) {

                //odczyt składowych koloru RGB
                Color c = new Color(image1.getRGB(j, i));
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();

                int final_red, final_green, final_blue;

                final_red = 255 - red;
                final_green = 255 - green;
                final_blue = 255 - blue;
                Color newColor = new Color(final_red, final_green, final_blue);
                image1.setRGB(j, i, newColor.getRGB());

            }
        }
        File ouptut = new File("negatyw.jpg");
        try {
            ImageIO.write(image1, "jpg", ouptut);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class licz4 extends Thread{
    public static BufferedImage image1;
    public licz4(BufferedImage image1)
    {
        this.image1 = image1;
    }
    @Override
    public void run()
    {
        System.out.println(height);
        System.out.println(width);
        for(int i=1; i<(height-1)/2; i++)
        {
            for (int j = (width - 1)/2; j < width - 1; j++) {

                //odczyt składowych koloru RGB
                Color c = new Color(image1.getRGB(j, i));
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();

                int final_red, final_green, final_blue;

                final_red = 255 - red;
                final_green = 255 - green;
                final_blue = 255 - blue;
                Color newColor = new Color(final_red, final_green, final_blue);
                image1.setRGB(j, i, newColor.getRGB());

            }
        }
        File ouptut = new File("negatyw.jpg");
        try {
            ImageIO.write(image1, "jpg", ouptut);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


public class Negatyw {
    public static BufferedImage image;
    public static int width;
    public static int height;

    public Negatyw() {
        try {

                File input = new File("pexel.jpg");
                image = ImageIO.read(input);
                licz1 licz1 = new licz1(image);
                licz2 licz2 = new licz2(image);
                licz3 licz3 = new licz3(image);
                licz4 licz4 = new licz4(image);
                width = image.getWidth();
                height = image.getHeight();

                licz1.start();
                licz2.start();
                licz3.start();
                licz4.start();
                licz1.join();
                licz2.join();
                licz3.join();
                licz4.join();

        } catch (Exception e) {}
        }








    static public void main(String[] args) throws InterruptedException {


        long startTime = System.nanoTime();
        Negatyw negatyw = new Negatyw();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(duration);


    }
}