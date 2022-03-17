package solids;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Axis extends Solid {
    public Axis(){
        /*
        getvB().add(new Vertex(0, 0, 0, new Col(255,0,0)));
        getvB().add(new Vertex(4, 0, 0, new Col(255,0,0)));
        getvB().add(new Vertex(0, 4, 0, new Col(255,0,0)));
        getvB().add(new Vertex(0, 0, 4, new Col(255,0,0)));

        getiB().add(0);
        getiB().add(1);
        getiB().add(2);
        getiB().add(3);

        getpB().add(new Part(TopologyType.LINES, 0, 3));
        */
        getvB().add(new Vertex(0, -0.02, 0, new Col(255,0,0)));
        getvB().add(new Vertex(0, 0.02, 0, new Col(255,0,0)));
        getvB().add(new Vertex(1.5, 0, 0, new Col(255,0,0)));

        getvB().add(new Vertex(0, 0, -0.02, new Col(0,255,0)));
        getvB().add(new Vertex(0,0, 0.02, new Col(0,255,0)));
        getvB().add(new Vertex(0, 1.5, 0, new Col(0,255,0)));

        getvB().add(new Vertex(-0.02, 0, 0, new Col(0,0,255)));
        getvB().add(new Vertex(0.02,0, 0, new Col(0,0,255)));
        getvB().add(new Vertex(0, 0, 1.5, new Col(0,0,255)));

        getiB().add(0);
        getiB().add(1);
        getiB().add(2);

        getiB().add(3);
        getiB().add(4);
        getiB().add(5);

        getiB().add(6);
        getiB().add(7);
        getiB().add(8);

        getpB().add(new Part(TopologyType.TRIANGLES, 0, 3));
    }
}
