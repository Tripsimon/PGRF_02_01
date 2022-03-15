package control;

import model.Shader;
import model.ShaderFullColor;
import model.ShaderInterpolation;
import model.Vertex;
import raster.ImageBuffer;
import raster.ZBufferVisibility;
import render.Renderer;
import render.TriangleRasterizer;
import solids.Arrow;
import solids.Solid;
import solids.Triangle;
import transforms.*;
import view.Panel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Controller3D implements Controller {

    private final Panel panel;

    private int width, height;
    private boolean pressed = false;
    private int ox, oy;

    private Camera camera;
    private ZBufferVisibility zBufferVisibility;

    private Renderer renderer;
    private TriangleRasterizer triangleRasterizer;

    private List<Solid> solidList;
    private Triangle test;

    public Controller3D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);


        solidList = new ArrayList<>();
        test = new Triangle();
        solidList.add(test);



        redraw();
    }

    public void initObjects(ImageBuffer raster) {
        raster.setClearValue(new Col(0x101010));
        zBufferVisibility = new ZBufferVisibility(panel.getRaster());

        //Kamera
        camera = new Camera()
                .withPosition(new Vec3D(15,0,0))
                .withAzimuth(Math.PI)
                .withZenith(0)
                .withFirstPerson(true);

        //Shader
        Shader shaderFullColor = v -> {
            return new Col(0, 1.0, 0);
        };

        Shader shaderInterpolation = v -> {
            return v.getColor();
        };

        //Rasterizer
        triangleRasterizer = new TriangleRasterizer(zBufferVisibility, shaderInterpolation);

        //Renderer
        renderer = new Renderer(triangleRasterizer);
    }

    @Override
    public void initListeners(Panel panel) {

    }

    private void redraw() {
        panel.clear();
        width = panel.getRaster().getWidth();
        height = panel.getRaster().getHeight();

        Mat4 space = new Mat4PerspRH(Math.PI / 2, height / (double) width, 0.1, 100);
        Mat4 model = new Mat4Scale(1, 1, 1).mul(new Mat4Transl(new Vec3D(0, 0, 0)).mul(new Mat4RotXYZ(0, 0, 0)));

        renderer.render(solidList.get(0));
        
        panel.repaint();
    }

    private void hardClear() {
        panel.clear();
        initObjects(panel.getRaster());
    }

}
