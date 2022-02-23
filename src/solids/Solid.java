package solids;

import model.Part;
import model.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Solid {
    List<Vertex> vb = new ArrayList<>();
    List<Integer> ib = new ArrayList<>();
    List<Part> pb = new ArrayList<>();

    public List<Vertex> getVb() {
        return vb;
    }

    public List<Integer> getIb() {
        return ib;
    }

    public List<Part> getPb() {
        return pb;
    }
}

