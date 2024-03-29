package solids;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Triangle extends Solid {

    public Triangle(Vertex v1,Vertex v2, Vertex v3) {


    /*
        getvB().add(new Vertex(1,1, 0.5, new Col(1., 0, 0)));
        getvB().add(new Vertex(-1,0, 0.5, new Col(0., 1, 0)));
        getvB().add(new Vertex(0,-1, 0.5, new Col(0., 0, 1)));

        getvB().add(new Vertex(0,0, 1, new Col(1., 0, 0)));
        getvB().add(new Vertex(0.5,0.5, 0, new Col(0., 1, 0)));
        getvB().add(new Vertex(0,0.5, 1, new Col(0., 0, 1)));
*/
        getvB().add(v1);
        getvB().add(v2);
        getvB().add(v3);

        getiB().add(0);
        getiB().add(1);
        getiB().add(2);

        getpB().add(new Part(TopologyType.TRIANGLES, 0, 1));
    }
}
