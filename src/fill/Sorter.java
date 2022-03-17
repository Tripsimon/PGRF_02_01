package fill;

import java.awt.*;
import java.util.List;

public class Sorter {

    public void sortExample(){

        //Pomocná proměná na porhazování hodnot
        int a ;

        //Náhodné pole pro demonstraci sort systému
        int[] veci = new int[9];
        veci[0] = 2;
        veci[1] = 9;
        veci[2] = 3;
        veci[3] = 1;
        veci[4] = 8;
        veci[5] = 2;
        veci[6] = 4;
        veci[7] = 4;
        veci[8] = 5;

        //Výpis pole před sortem
        System.out.println("Začíná seřazovací sekvence");
        for (int k:veci)
        {
            System.out.println(k);
        }


        //Vlastní bubblesort
        for (int i = 0; i < veci.length ; i++) {
            for (int j = 1; j <= veci.length -1; j++) {
                if (veci[j-1] > veci[j]){
                    a = veci[j-1];
                    veci[j-1] = veci[j];
                    veci[j] = a;
                }
            }
        }

        //Výpis výsledku
        System.out.println("Seřazovací sekvence dokončena");
        for (int k:veci)
        {
            System.out.println(k);
        }

    }


    public List<Point> sortPoints(List<Point> p){

        //Pomocná proměná na porhazování hodnot
        Point a;
        List<Point> points = p;

        //Vlastní bubblesort
        for (int i = 0; i < points.size() ; i++) {
            for (int j = 1; j <= points.size() -1; j++) {
                if (points.get(j-1).getX() > points.get(j).getX()){
                    a = points.get(j-1);
                    points.set(j-1,points.get(j));
                    points.set(j,a);
                }
            }
        }

        return points;
    }

    public void sortInt(List<Integer> p){

        //Pomocná proměná na porhazování hodnot
        Integer a;
        List<Integer> points = p;

        //Vlastní bubblesort
        for (int i = 0; i < points.size() ; i++) {
            for (int j = 1; j <= points.size() -1; j++) {
                if (points.get(j-1) > points.get(j)){
                    a = points.get(j-1);
                    points.set(j-1,points.get(j));
                    points.set(j,a);
                }
            }
        }


    }

}
