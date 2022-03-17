package fill;

/*
import raster.Raster;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class PatternScanLine {

    private FilledLineRasterizer lineCreator;
    private Color fillColor = Color.green;
    private Color patternColor = Color.red;
    private Color borderColor = Color.yellow;
    private List<Point> points;
    private Sorter sortMachine = new Sorter();
    private List<Point> intersections;
    private Raster r;

    public PatternScanLine(Raster r, Color bgrC, Color bordC, List<Point> seznamB){
        this.r = r;
        this.lineCreator = new FilledLineRasterizer(r);
        this.fillColor = bgrC;
        this.borderColor = bordC;
        this.points = seznamB;
        intersections = new ArrayList<>();
    }


    public void fill(Point p) {

    }

    public void fill() {

        //Výpis pointu
        System.out.println("Zadané bbody:");
        int pocitac = 1;
        for (Point k:points)
        {
            System.out.println("Bod:"+pocitac+" Souřadnice X: "+ k.getX() + " Souřadnice Y: " +k.getY());
            pocitac++;
        }

        //Deklarace proměné pro Maximum a minimum
        int minY = points.get(0).getY();
        int maxY = minY;

        List<Edge> edges = new ArrayList<>();
        Edge workedEdge;
        for (int i = 0; i < points.size(); i++){

            if (i+1 < points.size()){
                workedEdge =(new Edge(points.get(i),points.get(i+1)));
            }else{
                workedEdge =(new Edge(points.get(i),points.get(0)));
            }


            if (!workedEdge.isHorizontal()){
                workedEdge.orientation();
                edges.add(workedEdge);
                if (workedEdge.getY1() < minY){minY = workedEdge.getY1();}
                if (workedEdge.getY2() > maxY){maxY = workedEdge.getY2();}
            }
        }


        System.out.println("Minimum Y: "+minY);
        System.out.println("Maximum Y: "+maxY);

        //Výpočet průsečíků
        List<Point> pruseciky = new ArrayList<>();
        List<Point> tempPruseciky = new ArrayList<>();

        for (int y = minY; y <= maxY; y++){
            for (int i =0; i< edges.size();i++){
                if(edges.get(i).hasIntersection(y))
                {
                    tempPruseciky.add(new Point(edges.get(i).getIntersection(y),y));
                }

            }

            sortMachine.sortPoints(tempPruseciky);
            pruseciky.addAll(tempPruseciky);
            tempPruseciky.clear();

            for (int i = 0; i < pruseciky.size(); i += 2) {
                int x1 = pruseciky.get(i).getX();
                int x2 = pruseciky.get(i + 1).getX();

                for (int x = x1; x <= x2; x++) {
                    if (x%2==0){
                        r.setPixel(x, y, fillColor.getRGB());
                    }else{
                        r.setPixel(x, y, patternColor.getRGB());
                    }

                }

            }

        }




        for (Edge e:edges) {
            lineCreator.rasterize(e.getX1(),e.getY1(),e.getX2(),e.getY2(),borderColor);
        }
    }
}*/
