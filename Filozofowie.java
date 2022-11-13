package com.company;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore ;


class Filozof1 extends Thread {
    static int MAX = 5;
    static Semaphore[] widelec1 = new Semaphore[1000];
    int mojNum;

    public Filozof1(int nr) {
        mojNum = nr;
    }

    public void run() {
        while (true) {
// myslenie
            System.out.println("Mysle ¦ " + mojNum);
            try {
                Thread.sleep((long) (7000 * Math.random()));
            } catch (InterruptedException e) {
            }
            widelec1[mojNum].acquireUninterruptibly(); //przechwycenie L widelca
            widelec1[(mojNum + 1) % MAX].acquireUninterruptibly(); //przechwycenie P widelca
// jedzenie
            System.out.println("Zaczyna jesc " + mojNum);
            try {
                Thread.sleep((long) (5000 * Math.random()));
            } catch (InterruptedException e) {
            }
            System.out.println("Konczy jesc " + mojNum);
            widelec1[mojNum].release(); //zwolnienie L widelca
            widelec1[(mojNum + 1) % MAX].release(); //zwolnienie P widelca
        }
    }
}


class Filozof2 extends Thread {
    static int MAX=5;
    static Semaphore [] widelec2 = new Semaphore [1000] ;
    int mojNum;
    public Filozof2 ( int nr ) {
        mojNum=nr ;
    }
    public void run ( ) {
        while ( true ) {
// myslenie
            System.out.println ( "Mysle ¦ " + mojNum) ;
            try {
                Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            if (mojNum == 0) {
                widelec2 [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ;
                widelec2 [mojNum].acquireUninterruptibly ( ) ;
            } else {
                widelec2 [mojNum].acquireUninterruptibly ( ) ;
                widelec2 [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ;
            }
// jedzenie
            System.out.println ( "Zaczyna jesc "+mojNum) ;
            try {
                Thread.sleep ( ( long ) (3000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            System.out.println ( "Konczy jesc "+mojNum) ;
            widelec2 [mojNum].release ( ) ;
            widelec2 [ (mojNum+1)%MAX].release ( ) ;
        }
    }

}



class Filozof3 extends Thread {
    static int MAX = 5;
    static Semaphore[] widelec3 = new Semaphore[1000];
    int mojNum;
    Random losuj;

    public Filozof3(int nr) {
        mojNum = nr;
        losuj = new Random(mojNum);
    }

    public void run() {
        while (true) {
// myslenie
            System.out.println("Mysle ¦ " + mojNum);
            try {
                Thread.sleep((long) (5000 * Math.random()));
            } catch (InterruptedException e) {
            }
            int strona = losuj.nextInt(2);
            boolean podnioslDwaWidelce = false;
            do {
                if (strona == 0) {
                    widelec3[mojNum].acquireUninterruptibly();
                    if (!(widelec3[(mojNum + 1) % MAX].tryAcquire())) {
                        widelec3[mojNum].release();
                    } else {
                        podnioslDwaWidelce = true;
                    }
                } else {
                    widelec3[(mojNum + 1) % MAX].acquireUninterruptibly();
                    if (!(widelec3[mojNum].tryAcquire())) {
                        widelec3[(mojNum + 1) % MAX].release();
                    } else {
                        podnioslDwaWidelce = true;
                    }
                }
            } while (podnioslDwaWidelce == false);
            System.out.println("Zaczyna jesc " + mojNum);
            try {
                Thread.sleep((long) (3000 * Math.random()));
            } catch (InterruptedException e) {
            }
            System.out.println("Konczy jesc " + mojNum);
            widelec3[mojNum].release();
            widelec3[(mojNum + 1) % MAX].release();
        }
    }
}



public class Main {

    public static void main(String[] args) {
        System.out.println("Wybierz wariant programu [1-3]");
        Scanner scanner = new Scanner(System.in);
        int var = scanner.nextInt();
        System.out.println("Podaj liczbe filozofow [2-100]");
        int var2 = scanner.nextInt();
        Filozof1.MAX = var2;
        Filozof2.MAX = var2;
        Filozof3.MAX = var2;
        switch (var){
            case 1:
            {
                for (int i = 0; i < Filozof1.MAX; i++) {
                    Filozof1.widelec1[i] = new Semaphore(1);
                }
                for (int i = 0; i < Filozof1.MAX; i++) {
                    new Filozof1(i).start();
                }
            }
            case 2:
            {
                for (int i = 0; i < Filozof2.MAX; i++) {
                    Filozof2.widelec2[i] = new Semaphore(1);
                }
                for (int i = 0; i < Filozof2.MAX;  i++) {
                    new Filozof2(i).start();
                }
            }
            case 3:
            {
                for ( int i =0; i<Filozof3.MAX;  i++) {
                    Filozof3.widelec3 [ i ]=new Semaphore ( 1 ) ;
                }
                for ( int i =0; i<Filozof3.MAX;  i++) {
                    new Filozof3(i).start();
                }
            }

        }
    }

}


