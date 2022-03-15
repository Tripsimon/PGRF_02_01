package render;

import model.Part;
import model.Vertex;
import solids.Solid;
import transforms.Mat4;

import java.util.List;

public class Renderer {
    private TriangleRasterizer triangleRasterizer;

    private Mat4 camera;

    public Renderer(TriangleRasterizer triangleRasterizer) {
        this.triangleRasterizer = triangleRasterizer;
    }

    public void renderScene(List<Solid> scene) {
        for (Solid solid : scene)
            render(solid);
    }

    public void render(Solid solid) {
        // TODO: transformace

        for (Part part:solid.getpB()) {
            switch (part.getType()) {
                case POINTS:
                    // TODO
                    break;
                case LINES:
                    // TODO
                    break;
                case LINES_STRIP:
                    // TODO
                    break;
                case TRIANGLES:
                    // TODO
                    int start = part.getStart();

                    for (int i = 0; i < part.getCount(); i++) {
                        int indexV1 = start;
                        int indexV2 = start + 1;
                        int indexV3 = start + 2;

                        start += 3;

                        Vertex v1 = solid.getvB().get(solid.getiB().get(indexV1));
                        Vertex v2 = solid.getvB().get(solid.getiB().get(indexV2));
                        Vertex v3 = solid.getvB().get(solid.getiB().get(indexV3));

                        triangleRasterizer.rasterize(v1, v2, v3);
                    }

                    break;
                case TRINAGLES_STRIP:
                    // TODO
                    break;
            }
        }
    }
}
