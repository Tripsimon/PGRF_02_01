package solids;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Spike extends Solid {
    public Spike() {
        //Vertexy
        getvB().add(new Vertex(0, 0, 0, new Col(255,255,0)));
        getvB().add(new Vertex(0.5, 0, 0, new Col(255,0,0)));
        getvB().add(new Vertex(0, 0.5, 0, new Col(0,255,0)));
        getvB().add(new Vertex(0.5, 0.5, 0, new Col(0,0,255)));
        getvB().add(new Vertex(0.25, 0.25, 1, new Col(255,255,255)));

        //Indexy
        //Spodní Část
        getiB().add(0);
        getiB().add(1);
        getiB().add(2);
        getiB().add(3);
        getiB().add(1);
        getiB().add(2);


        //Stěny
        getiB().add(0);
        getiB().add(1);
        getiB().add(4);
        getiB().add(1);
        getiB().add(2);
        getiB().add(4);
        getiB().add(2);
        getiB().add(3);
        getiB().add(4);
        getiB().add(3);
        getiB().add(0);
        getiB().add(4);

        //Topologie
        getpB().add(new Part(TopologyType.TRIANGLES, 0, 6));
    }
}
