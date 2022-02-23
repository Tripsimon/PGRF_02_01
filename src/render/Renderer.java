package render;

import model.Part;
import model.Vertex;
import solids.Solid;

import java.util.List;

public class Renderer {
    private TriangleRasterizer triangleRasterizer;

    public Renderer(TriangleRasterizer triangleRasterizer) {
        this.triangleRasterizer = triangleRasterizer;
    }

    public void render(Solid solid){
        for (Part part:solid.getPb())
        {
            switch (part.getType()){
                case POINTS:
                    break;
                case LINES:
                    break;
                case LINES_STRIP:
                    break;
                case TRIANGLES:
                    int start = part.getStart();
                    for (int i = 0; i < part.getNum() ; i++) {

                        int indexV1 = start;
                        int indexV2 = start+1;
                        int indexV3 = start+2;

                        Vertex v1 = solid.getVb().get(solid.getIb().get(indexV1));
                        Vertex v2 = solid.getVb().get(solid.getIb().get(indexV2));
                        Vertex v3 = solid.getVb().get(solid.getIb().get(indexV3));

                        triangleRasterizer.rasterize(v1,v2,v3);
                    }
                    break;
            }
        }
    }

    public void renderScene(List<Solid> scene){
        for (Solid solid: scene) {
            render(solid);
        }
    }
}
