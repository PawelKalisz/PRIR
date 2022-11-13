package com.company;


import static com.company.Glowna.pociagi;

public class Tory {
    static int LOTNISKO=1;
    static int START=2;
    static int LOT=3;
    static int KONIEC_LOTU=4;
    static int KATASTROFA=5;
    int ilosc_pasow;
    int ilosc_zajetych;
    int ilosc_samolotow = pociagi.size();
    Tory(int ilosc_pasow){
        this.ilosc_pasow=ilosc_pasow;
        this.ilosc_zajetych=0;
    }
    synchronized int start(int numer){
        ilosc_zajetych--;
        System.out.println("Pozwolenie na start samolotowi "+numer);
        return START;
    }
    synchronized int laduj(){
        try{
            Thread.currentThread().sleep(1000);//sleep for 1000 ms
        }
        catch(Exception ie){
        }
        if(ilosc_zajetych<ilosc_pasow){
            ilosc_zajetych++;
            System.out.println("Pozwolenie ladowanie na pasie "+ilosc_zajetych);
            return LOTNISKO;
        }
        else
        {return KONIEC_LOTU;}
    }
    synchronized void zmniejsz(int numer){
        pociagi.get(numer).stop();
        pociagi.remove(numer);
    }
}