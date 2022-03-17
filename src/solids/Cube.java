package solids;

import model.Vertex;

public class Cube extends Solid {
    public Cube

    {
        getvB().add(new Vertex(1, 1, 0.5, new Col(1., 0, 0)));
        getvB().add(new Vertex(-1, 0, 0.5, new Col(0., 1, 0)));
        getvB().add(new Vertex(0, -1, 0.5, new Col(0., 0, 1)));

        getvB().add(new Vertex(0, 0, 1, new Col(1., 0, 0)));
        getvB().add(new Vertex(0.5, 0.5, 0, new Col(0., 1, 0)));
        getvB().add(new Vertex(0, 0.5, 1, new Col(0., 0, 1)));
    }
}
