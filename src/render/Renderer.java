package render;

import model.Part;
import model.Vertex;
import solids.Solid;
import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Mat4OrthoRH;

import java.util.List;

public class Renderer {
    private TriangleRasterizer triangleRasterizer;
    private LineRasterizer lineRasterizer;

    private Mat4 model;
    private Mat4 camera;
    private Mat4 space;
    private Mat4 ortProjection = new Mat4OrthoRH(1, 1, 0, 10);
    private Mat4 geoProjection = new Mat4Identity();

    private boolean geoCheck = true;
    private Mat4 projection = new Mat4Identity();
    private boolean wireframeCheck = true;
    private boolean gradientCheck = false;


    private Mat4 viewToApply;

    public Renderer(TriangleRasterizer triangleRasterizer, LineRasterizer lineRasterizer) {
        this.triangleRasterizer = triangleRasterizer;
        this.lineRasterizer = lineRasterizer;
    }

    public void renderScene(List<Solid> scene) {
        viewToApply = model.mul(camera).mul(space).mul(projection);

        for (Solid solid : scene)
            render(solid);
    }

    public void render(Solid solid) {
        // TODO: transformace

        for (Part part : solid.getpB()) {
            switch (part.getType()) {
                case POINTS:
                    // TODO
                    break;
                case LINES:

                    System.out.println(solid.getiB().get(0));
                    System.out.println(solid.getiB().get(1));
                    System.out.println(solid.getiB().get(2));
                    System.out.println(solid.getiB().get(3));

                    Vertex l1 = solid.getvB().get(solid.getiB().get(0)).transform(viewToApply);
                    Vertex l2 = solid.getvB().get(solid.getiB().get(1)).transform(viewToApply);
                    Vertex l3 = solid.getvB().get(solid.getiB().get(2)).transform(viewToApply);
                    Vertex l4 = solid.getvB().get(solid.getiB().get(3)).transform(viewToApply);

                    lineRasterizer.rasterize(l1, l2);
                    lineRasterizer.rasterize(l1, l3);
                    lineRasterizer.rasterize(l1, l4);

                    /*
                    for (int i = 0; i < part.getCount();i++) {

                        int indexL1 = startL;
                        int indexL2 = startL + 1;

                        Vertex l1 = solid.getvB().get(solid.getiB().get(0)).transform(viewToApply);
                        Vertex l2 = solid.getvB().get(solid.getiB().get(1)).transform(viewToApply);
                        Vertex l3 = solid.getvB().get(solid.getiB().get(2)).transform(viewToApply);
                        Vertex l4 = solid.getvB().get(solid.getiB().get(3)).transform(viewToApply);


                        lineRasterizer.rasterize(l1, l2);
                        lineRasterizer.rasterize(l1, l3);
                        lineRasterizer.rasterize(l1, l4);

                        startL += 2;
                    }
                    */

                    break;
                case LINES_STRIP:
                    // TODO
                    break;
                case TRIANGLES:

                    int start = part.getStart();

                    for (int i = 0; i < part.getCount(); i++) {
                        int indexV1 = start;
                        int indexV2 = start + 1;
                        int indexV3 = start + 2;

                        start += 3;


                        Vertex v1 = solid.getvB().get(solid.getiB().get(indexV1)).transform(viewToApply);
                        Vertex v2 = solid.getvB().get(solid.getiB().get(indexV2)).transform(viewToApply);
                        Vertex v3 = solid.getvB().get(solid.getiB().get(indexV3)).transform(viewToApply);


                        triangleRasterizer.rasterize(v1, v2, v3,wireframeCheck,gradientCheck);
                    }

                    break;
                case TRINAGLES_STRIP:
                    // TODO
                    break;
            }
        }
    }

    public Mat4 getModel() {
        return model;
    }

    public void setModel(Mat4 model) {
        this.model = model;
    }

    public Mat4 getCamera() {
        return camera;
    }

    public void setCamera(Mat4 camera) {
        this.camera = camera;
    }

    public Mat4 getSpace() {
        return space;
    }

    public void setSpace(Mat4 space) {
        this.space = space;
    }

    public Mat4 getProjection() {
        return projection;
    }

    public void setProjection(Mat4 projection) {
        this.projection = projection;
    }

    public void changeProjection() {
        if (geoCheck) {
            projection = ortProjection;
            geoCheck = !geoCheck;
        } else {
            projection = geoProjection;
            geoCheck = !geoCheck;
        }
    }

    public void changeWireframe() {
        wireframeCheck = !wireframeCheck;
    }

    public void changeGradient() {
        gradientCheck = !gradientCheck;
    }
}
