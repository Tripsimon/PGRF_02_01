package solids;


import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Block extends Solid {
    public Block() {
        //Vertexy
        getvB().add(new Vertex(0, 0, 0, new Col(0,255,255)));
        getvB().add(new Vertex(0.5, 0, 0, new Col(0,255,255)));
        getvB().add(new Vertex(0, 1, 0, new Col(0,255,255)));
        getvB().add(new Vertex(0.5, 1, 0, new Col(0,255,255)));

        getvB().add(new Vertex(0, 0, 0.5, new Col(0,255,255)));
        getvB().add(new Vertex(0.5, 0, 0.5, new Col(0,255,255)));
        getvB().add(new Vertex(0, 1, 0.5, new Col(0,255,255)));
        getvB().add(new Vertex(0.5, 1, 0.5, new Col(0,255,255)));

        //Indexy
        //Spodní část
        getiB().add(0);
        getiB().add(1);
        getiB().add(2);
        getiB().add(3);
        getiB().add(1);
        getiB().add(2);


        //Levá část
        getiB().add(0);
        getiB().add(1);
        getiB().add(4);
        getiB().add(5);
        getiB().add(4);
        getiB().add(1);

        //Pozadí
        getiB().add(0);
        getiB().add(2);
        getiB().add(4);
        getiB().add(4);
        getiB().add(2);
        getiB().add(6);

        //Popředí
        getiB().add(1);
        getiB().add(3);
        getiB().add(5);
        getiB().add(7);
        getiB().add(3);
        getiB().add(5);

        //Pravá část
        getiB().add(2);
        getiB().add(3);
        getiB().add(6);

        getiB().add(6);
        getiB().add(7);
        getiB().add(3);


        //Vrchní část
        getiB().add(4);
        getiB().add(5);
        getiB().add(6);

        getiB().add(7);
        getiB().add(5);
        getiB().add(6);

        //Topologie
        getpB().add(new Part(TopologyType.TRIANGLES, 0, 12));
    }
}
