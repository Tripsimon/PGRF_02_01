package solids;

import model.Part;
import model.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Solid {
    // PartBuffer
    private List<Part> pB = new ArrayList<>();
    // VertexBuffer
    private List<Vertex> vB = new ArrayList<>();
    // IndexBuffer
    private List<Integer> iB = new ArrayList<>();

    public List<Part> getpB() {
        return pB;
    }

    public List<Vertex> getvB() {
        return vB;
    }

    public List<Integer> getiB() {
        return iB;
    }
}
