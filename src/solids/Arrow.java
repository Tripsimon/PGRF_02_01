package solids;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Arrow extends Solid {

    public Arrow() {
        // TODO: Vhodně zvolit barvy
        getvB().add(new Vertex(0,  0, 0, new Col(1., 0, 0)));
        getvB().add(new Vertex(.8, 0, 0, new Col(1., 0, 0)));
        getvB().add(new Vertex(.8, 1, 0, new Col(1., 0, 0)));
        getvB().add(new Vertex(1,  0, 0, new Col(1., 0, 0)));
        getvB().add(new Vertex(.8,-1, 0, new Col(1., 0, 0)));

        getiB().add(0);
        getiB().add(1);

        getiB().add(2);
        getiB().add(3);
        getiB().add(4);

        getpB().add(new Part(TopologyType.LINES, 0, 1));
        getpB().add(new Part(TopologyType.TRIANGLES, 2, 1));
    }
}
