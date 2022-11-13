package com.company;

import java.util.ArrayList;
import java.util.Random;



class licz extends Thread{

    double radius, numberOfPoints, wynik;
    int pointsInside;
    public static ArrayList<Double> wyniki = new ArrayList<>();
    public licz(double radius, double numberOfPoints){
        this.radius = radius;
        this.numberOfPoints = numberOfPoints;
    }

    boolean isPointInCircle(double x, double y, double Cx, double Cy, double radius)
    {
        return Math.sqrt(Math.pow((x - Cx),2) + Math.pow((y - Cy),2)) <= radius;
    }

    double approximateCricleArea()
    {
        double squareSide = radius*2;
        double Cx = radius;
        double Cy = radius;
        int pointsInside = 0;
        double x,y;

        Random random = new Random();
        for (int i = 0; i < numberOfPoints ; i++) {
            x =  random.nextDouble()*squareSide;
            y =  random.nextDouble()*squareSide;
            if (isPointInCircle(x, y, Cx, Cy, radius))
                pointsInside = pointsInside + 1;
        }

        return pointsInside / numberOfPoints * Math.pow(squareSide,2);
    }



    @Override
    public void run()
    {
        double squareSide = radius*2;
        double Cx = radius;
        double Cy = radius;
        pointsInside = 0;
        double x,y;

        Random random = new Random();
        for (int i = 0; i < numberOfPoints ; i++) {
            x =  random.nextDouble()*squareSide;
            y =  random.nextDouble()*squareSide;
            if (isPointInCircle(x, y, Cx, Cy, radius))
                pointsInside = pointsInside + 1;
        }

        setWynik(pointsInside / numberOfPoints * Math.pow(squareSide,2));
        setPointsInside(pointsInside);
    }

    public void setWynik(double wynik) {
        this.wynik = wynik;
    }

    public double getWynik() {
        return wynik;
    }


    public int getPointsInside() {
        return pointsInside;
    }

    public void setPointsInside(int pointsInside) {
        this.pointsInside = pointsInside;
    }
}



public class Main {



    public static void main(String[] args) throws InterruptedException {

        int promien = 1;
        double liczba_punktow = 100000000;
        int liczba_watkow = 35;
        int punkty_w_srodku = 0;
        ArrayList<licz> obiekty = new ArrayList<>();
        long startTime = System.nanoTime();

    //System.out.println(pointsInside / numberOfPoints * Math.pow(squareSide,2));
        int pom = 0;
        for (int i = 0; i < liczba_watkow; i++) {
            obiekty.add(new licz(promien,liczba_punktow/liczba_watkow));
            obiekty.get(i).start();
            //punkty_w_srodku += obiekty.get(i).getPointsInside();
            //System.out.println(punkty_w_srodku);
        }

        while (true) {
            pom = 0;
            for (int i = 0; i < liczba_watkow; i++) {
                if (obiekty.get(i).isAlive()) {
                    pom++;
                }
            }
            if (pom == 0) break;
        }
        for (int i = 0; i < liczba_watkow; i++) {
            punkty_w_srodku += obiekty.get(i).getPointsInside();
        }
        double wynik = punkty_w_srodku / liczba_punktow * Math.pow(promien*2,2);
        System.out.println(wynik);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(duration);

    }
}
