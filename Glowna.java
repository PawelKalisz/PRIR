package com.company;


import java.util.ArrayList;
import java.util.Random;

public class Glowna {
    static int ilosc_pociagow = 100;
    static int ilosc_torow = 1;
    static Tory tory;
    static Random random = new Random();
    public static ArrayList<Pociag> pociagi = new ArrayList<>();
    public Glowna() {
    }

    public static String generujNazwe()
    {
        int var1 = (int) Math.pow(random.nextInt() % 2,2);

        StringBuilder nr = new StringBuilder();

        if (var1 == 0) nr.append("TLK");
        else nr.append("IC");
        nr.append((1 + random.nextInt(2)) * 10000 + random.nextInt(10000));

        return String.valueOf(nr);
    }


    public static void main(String[] args) {


        tory = new Tory(ilosc_torow);

        for (int i = 0; i < ilosc_pociagow+1; i++)
        {
            pociagi.add(new Pociag(i, 2000, tory, generujNazwe()));
        }
        for (int i = 0; i < ilosc_pociagow+1; i++)
        {
            pociagi.get(i).start();
        }

    }


}