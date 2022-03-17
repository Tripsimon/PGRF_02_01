package solids;


import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Block extends Solid {
    public Block() {
        getvB().add(new Vertex(0, 0, 0, new Col(0,255,255)));
        getvB().add(new Vertex(0.5, 0, 0, new Col(0,255,255)));
        getvB().add(new Vertex(0, 1, 0, new Col(0,255,255)));
        getvB().add(new Vertex(0.5, 1, 0, new Col(0,255,255)));

        getvB().add(new Vertex(0, 0, 0.5, new Col(0,255,255)));
        getvB().add(new Vertex(0.5, 0, 0.5, new Col(0,255,255)));
        getvB().add(new Vertex(0, 1, 0.5, new Col(0,255,255)));
        getvB().add(new Vertex(0.5, 1, 0.5, new Col(0,255,255)));

        //spodek
        getiB().add(0);
        getiB().add(1);
        getiB().add(2);
        getiB().add(3);
        getiB().add(1);
        getiB().add(2);


        //levá
        getiB().add(0);
        getiB().add(1);
        getiB().add(4);
        getiB().add(5);
        getiB().add(4);
        getiB().add(1);

        //zadní
        getiB().add(0);
        getiB().add(2);
        getiB().add(4);
        getiB().add(4);
        getiB().add(2);
        getiB().add(6);

        //Předek
        getiB().add(1);
        getiB().add(3);
        getiB().add(5);
        getiB().add(7);
        getiB().add(3);
        getiB().add(5);

        //Pravá
        getiB().add(2);
        getiB().add(3);
        getiB().add(6);

        getiB().add(6);
        getiB().add(7);
        getiB().add(3);


        //vršek
        getiB().add(4);
        getiB().add(5);
        getiB().add(6);

        getiB().add(7);
        getiB().add(5);
        getiB().add(6);


        getpB().add(new Part(TopologyType.TRIANGLES, 0, 12));
    }
}
