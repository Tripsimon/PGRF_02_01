package solids;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Line extends Solid {

    public Line(Vertex A, Vertex B){
        getvB().add(A);
        getvB().add(B);


        getiB().add(0);
        getiB().add(1);

        getpB().add(new Part(TopologyType.LINES, 0, 1));
    }
}
