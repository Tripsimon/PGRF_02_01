package solids;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Cube extends Solid {
    public Cube() {
        getvB().add(new Vertex(0, 0, 0, new Col(255,0,0)));
        getvB().add(new Vertex(0.5, 0, 0, new Col(255,0,0)));
        getvB().add(new Vertex(0, 0.5, 0, new Col(255,0,0)));
        getvB().add(new Vertex(0.5, 0.5, 0, new Col(255,0,0)));

        getvB().add(new Vertex(0, 0, 0.5, new Col(255,0,0)));
        getvB().add(new Vertex(0.5, 0, 0.5, new Col(255,0,0)));
        getvB().add(new Vertex(0, 0.5, 0.5, new Col(255,0,0)));
        getvB().add(new Vertex(0.5, 0.5, 0.5, new Col(255,0,0)));

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
