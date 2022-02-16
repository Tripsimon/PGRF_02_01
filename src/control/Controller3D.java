package control;

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
            public void mousePressed(MouseEvent ev) {
                if (ev.getButton() == MouseEvent.BUTTON1) {
                    pressed = true;
                    ox = ev.getX();
                    oy = ev.getY();
                    panel.getRaster().setElement(ox, oy,new Col(0xff0000));
                    points.add(new Point(ox, oy));
                    redraw();
                }
            }

            public void mouseReleased(MouseEvent ev) {
                if (ev.getButton() == MouseEvent.BUTTON1) {
                    panel.getRaster().setElement(ox, oy,new Col(0xffff));
                    pressed = false;
                    redraw();
                }
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent ev) {
                if (pressed) {
                    ox = ev.getX();
                    oy = ev.getY();
                    panel.getRaster().setElement(ox, oy,new Col(0xffff00));
                    redraw();
                }
            }
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
                new Point3D(1,1,0),
                new Point3D(-1,0,0),
                new Point3D(0,-1,0)
                );

        panel.repaint();
    }

    private void hardClear() {
        panel.clear();
        initObjects(panel.getRaster());
    }

}
