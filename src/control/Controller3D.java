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

    //Držák pozice myšy (Pro otáčení
    private int mouseLastX = 0;
    private int mouseLastY = 0;

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
                .withPosition(new Vec3D(15, 0, 0))
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

        panel.addMouseMotionListener(new MouseAdapter() {
            //Pohnutí myši

            @Override
            public void mousePressed(MouseEvent e) { //Zachycení pozice myšy
                mouseLastX = e.getX();
                mouseLastY = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) { //Odposlouchávání pro pohnutí myší (Znázornění vygenerování)

                //Otáčení kamery podle změny pozice myšy
                if ((e.getX() - mouseLastX) == -1) {
                    camera = camera.addAzimuth(Math.PI / 5000);
                }

                if ((e.getX() - mouseLastX) == 1) {
                    camera = camera.addAzimuth(-(Math.PI / 5000));
                }

                if ((e.getY() - mouseLastY) == -1) {
                    camera = camera.addZenith(Math.PI / 5000);
                }

                if ((e.getY() - mouseLastY) == 1) {
                    camera = camera.addZenith(-(Math.PI / 5000));
                }

                //Repaint plátna
                redraw();

                //Zaznamenání poslední pozice
                mouseLastX = e.getX();
                mouseLastY = e.getY();
            }


        });

        panel.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) { //Adapter pro odposlouchávání
                double step = 0.3; //Definice kroku

                //Pohyb WASD
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    camera = camera.left(step);
                }

                if (e.getKeyCode() == KeyEvent.VK_D) {
                    camera = camera.right(step);
                }

                if (e.getKeyCode() == KeyEvent.VK_W) {
                    camera = camera.up(step);

                }

                if (e.getKeyCode() == KeyEvent.VK_S) {
                    camera = camera.down(step);

                }

                redraw();
            }


        });
    }

    private void redraw() {
        panel.clear();
        width = panel.getRaster().getWidth();
        height = panel.getRaster().getHeight();

        Mat4 space = new Mat4PerspRH(Math.PI / 2, height / (double) width, 0.1, 100);
        Mat4 model = new Mat4Scale(1, 1, 1).mul(new Mat4Transl(new Vec3D(0, 0, 0)).mul(new Mat4RotXYZ(0, 0, 0)));

        renderer.setModel(model);
        renderer.setCamera(camera.getViewMatrix());
        renderer.setSpace(space);


        renderer.renderScene(solidList);

        panel.repaint();
    }

    private void hardClear() {
        panel.clear();
        initObjects(panel.getRaster());
    }

}
