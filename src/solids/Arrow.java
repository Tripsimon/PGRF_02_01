package solids;

import model.Part;
import model.TopologyType;
import model.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Arrow extends Solid {


    public Arrow() {
        getVb().add(new Vertex( 0 ,0,0));
        getVb().add(new Vertex(0.8 ,0,0));
        getVb().add(new Vertex(0.8,1,0));
        getVb().add(new Vertex(1,0,0));
        getVb().add(new Vertex(0.8,-1,0));

        getIb().add(0);
        getIb().add(1);
        getIb().add(2);
        getIb().add(3);
        getIb().add(4);

        getPb().add(new Part(TopologyType.LINES,0,2));
        getPb().add(new Part(TopologyType.TRIANGLES,2,1));

    }


}
