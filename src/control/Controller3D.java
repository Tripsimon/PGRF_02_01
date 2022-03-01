package control;

import model.Vertex;
import raster.ImageBuffer;
import raster.ZBufferVisibility;
import render.Renderer;
import render.TriangleRasterizer;
import solids.Arrow;
import transforms.Col;
import transforms.Point3D;
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

    private ZBufferVisibility zBufferVisibility;
    private TriangleRasterizer triangleRasterizer;

    public Controller3D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);
        redraw();
    }

    public void initObjects(ImageBuffer raster) {
        raster.setClearValue(new Col(0x101010));
        zBufferVisibility = new ZBufferVisibility(panel.getRaster());
        triangleRasterizer = new TriangleRasterizer(zBufferVisibility);
    }

    @Override
    public void initListeners(Panel panel) {

    }

    private void redraw() {
        panel.clear();
        width = panel.getRaster().getWidth();
        height = panel.getRaster().getHeight();


        Arrow arrow = new Arrow();
        Renderer renderer = new Renderer(triangleRasterizer);
        renderer.render(arrow);
/*
        triangleRasterizer.rasterize(
                new Vertex(1,1, 0.5),
                new Vertex(-1,0, 0.5),
                new Vertex(0,-1, 0.5)
        );

        triangleRasterizer.rasterize(
                new Vertex(1,1, 0.7),
                new Vertex(-1,0, 0.7),
                new Vertex(0,-1, 0.7)
        );
*/

        panel.repaint();
    }

    private void hardClear() {
        panel.clear();
        initObjects(panel.getRaster());
    }

}
