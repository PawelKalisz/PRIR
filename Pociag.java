package com.company;
import java.util.Random;

public class Pociag extends Thread {
    //definicja stanˇw samolotu
    static int LOTNISKO = 1;
    static int START = 2;
    static int LOT = 3;
    static int KONIEC_LOTU = 4;
    static int KATASTROFA = 5;
    static int TANKUJ = 1000;
    static int REZERWA = 500;
    //zmienne pomocnicze
    int numer;
    int paliwo;
    int stan;
    String pociag;
    Tory l;
    Random rand;

    public Pociag(int numer, int paliwo, Tory l, String pociag) {
        this.numer = numer;
        this.paliwo = paliwo;
        this.stan = LOT;
        this.l = l;
        rand = new Random();
        this.pociag = pociag;
    }




    public void run() {
        while (true) {
            if (stan == LOTNISKO) {
                if (rand.nextInt(2) == 1) {
                    stan = START;
                    paliwo = TANKUJ;
                    System.out.println("prosze o pozwolenie na odjazd:  " + pociag);
                    stan = l.start(numer);
                } else {
                    System.out.println("Postoje sobie jeszcze troche");
                }
            } else if (stan == START) {
                System.out.println("Odjazd pociagu:  " + pociag);
                stan = LOT;
            } else if (stan == LOT) {
                paliwo -= rand.nextInt(500);
                if (paliwo <= REZERWA) {
                    stan = KONIEC_LOTU;
                } else try {
                    sleep(rand.nextInt(1000));
                } catch (Exception e) {
                }
            } else if (stan == KONIEC_LOTU) {
                System.out.println("Prosze o zatrzymanie pociagu: " + pociag);
                stan = l.laduj();
                if (stan == KONIEC_LOTU) {
                    paliwo -= rand.nextInt(500);
                    if (paliwo <= 0)
                    {
                        paliwo = 0;
                        stan = KATASTROFA;
                    }
                    System.out.println("REZERWA " + paliwo);

                }
            } else if (stan == KATASTROFA) {
                System.out.println("Brak zasilania w pociagu: " + pociag);

                l.zmniejsz(numer);

            }
        }
    }


}
