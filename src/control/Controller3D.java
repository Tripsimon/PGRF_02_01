package control;

import model.Vertex;
import raster.ImageBuffer;
import raster.ZBufferVisibility;
import render.TriangleRasterizer;
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

    List<Point> points;
    boolean modeCleared = false;

    public Controller3D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);
        redraw();
    }

    public void initObjects(ImageBuffer raster) {
        raster.setClearValue(new Col(0x101010));
        zBufferVisibility = new ZBufferVisibility(this.panel.getRaster());
        triangleRasterizer = new TriangleRasterizer(zBufferVisibility);
    }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {
          /*
            public void mousePressed(MouseEvent ev) {

            }

            public void mouseReleased(MouseEvent ev) {

            }
            */

        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {

           /*
            public void mouseDragged(MouseEvent ev) {

            }

            */
        });

        panel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent key) {
                switch (key.getKeyCode()) {
                    case KeyEvent.VK_BACK_SPACE:
                        hardClear();
                        break;
                    case KeyEvent.VK_M:
                        modeCleared = !modeCleared;
                        break;
                }
                redraw();
            }
        });

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.resize();
                initObjects(panel.getRaster());
            }
        });
    }

    private void redraw() {
        panel.clear();
        width = panel.getRaster().getWidth();
        height = panel.getRaster().getHeight();

        triangleRasterizer.rasterize(
                new Vertex(1,1,0),
                new Vertex(-1,0,0),
                new Vertex(0,-1,0)
                );

        panel.repaint();
    }

    private void hardClear() {
        panel.clear();
        initObjects(panel.getRaster());
    }

}
