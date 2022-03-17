package control;

import model.Shader;
import model.ShaderFullColor;
import model.ShaderInterpolation;
import model.Vertex;
import raster.ImageBuffer;
import raster.ZBufferVisibility;
import render.LineRasterizer;
import render.Renderer;
import render.TriangleRasterizer;
import solids.*;
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
    private LineRasterizer lineRasterizer;

    private List<Solid> solidList;

    //Držák pozice myšy (Pro otáčení
    private int mouseLastX = 0;
    private int mouseLastY = 0;

    public Controller3D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);


        solidList = new ArrayList<>();
        Triangle test = new Triangle(
                new Vertex(-0.5, 0, 0.5, new Col(0., 0, 1)),
                new Vertex(0.5, 0, 0.5, new Col(1., 0, 0)),
                new Vertex(0, 0.5, 0.5, new Col(0., 1, 0))

        );

        Triangle test2 = new Triangle(
                new Vertex(-0.5, 0, 0.5, new Col(0., 0, 1)),
                new Vertex(0.5, 0, 0.5, new Col(1., 0, 0)),
                new Vertex(0, -0.5, 0.5, new Col(0., 1, 0))

        );

        Axis osa = new Axis();
        Cube krychle = new Cube();
        Spike pyramida = new Spike();

        for (int i = 0; i < pyramida.getvB().size(); i++) {
            pyramida.getvB().get(i).setPosition(pyramida.getvB().get(i).transform(new Mat4Transl(2, 0, 0)).getPosition());
        }

        Block kvadr = new Block();

        for (int i = 0; i < kvadr.getvB().size(); i++) {
            kvadr.getvB().get(i).setPosition(kvadr.getvB().get(i).transform(new Mat4Transl(0, 2, 0)).getPosition());
        }

        solidList.add(krychle);
        solidList.add(pyramida);
        solidList.add(kvadr);
        solidList.add(osa);

        //solidList.add(test2);
        //solidList.add(testLine);


        redraw();
    }

    public void initObjects(ImageBuffer raster) {
        raster.setClearValue(new Col(0x101010));
        zBufferVisibility = new ZBufferVisibility(panel.getRaster());

        //Kamera
        camera = new Camera()
                .withPosition(new Vec3D(15, 1, 1))
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
        lineRasterizer = new LineRasterizer(zBufferVisibility);


        //Renderer
        renderer = new Renderer(triangleRasterizer, lineRasterizer);
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

                // - Otáčení kamery
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
                mouseLastX = e.getX();
                mouseLastY = e.getY();
                // - /Otáčení kamery

                // - Překreslení
                redraw();
                // - /Překreslení

                //Zaznamenání poslední pozice

            }


        });

        panel.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) { //Adapter pro odposlouchávání
                double step = 0.3; //Definice kroku

                // - Pohyb WASD
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
                // - /Pohyb WASD

                // - ZOOM
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    camera = camera.forward(1);

                }


                if (e.getKeyCode() == KeyEvent.VK_E) {
                    camera = camera.backward(step);

                }
                // - /ZOOM


                // - Změna Projekcí
                if (e.getKeyCode() == KeyEvent.VK_X) {

                    renderer.changeProjection();
                }
                // - /Změna Projekcí

                // - Změna Wireframe modu
                if (e.getKeyCode() == KeyEvent.VK_C) {

                    renderer.changeWireframe();
                }
                // - /Změna Wireframe modu

                // - Změna Gradient modu
                if (e.getKeyCode() == KeyEvent.VK_V) {

                    renderer.changeGradient();
                }
                // - /Změna Gradient modu

                // - Translace
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                    for (int i = 0; i <solidList.get(0).getvB().size() ; i++) {
                        solidList.get(0).getvB().get(i).setPosition(solidList.get(0).getvB().get(i).transform(new Mat4Transl(-0.2, 0, 0)).getPosition());
                    }

                }

                if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                    for (int i = 0; i <solidList.get(0).getvB().size() ; i++) {
                        solidList.get(0).getvB().get(i).setPosition(solidList.get(0).getvB().get(i).transform(new Mat4Transl(0.2, 0, 0)).getPosition());
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                    for (int i = 0; i <solidList.get(0).getvB().size() ; i++) {
                        solidList.get(0).getvB().get(i).setPosition(solidList.get(0).getvB().get(i).transform(new Mat4Transl(0, -0.2, 0)).getPosition());
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                    for (int i = 0; i <solidList.get(0).getvB().size() ; i++) {
                        solidList.get(0).getvB().get(i).setPosition(solidList.get(0).getvB().get(i).transform(new Mat4Transl(0, 0.2, 0)).getPosition());
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                    for (int i = 0; i <solidList.get(0).getvB().size() ; i++) {
                        solidList.get(0).getvB().get(i).setPosition(solidList.get(0).getvB().get(i).transform(new Mat4RotX(0.03)).getPosition());
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
                    for (int i = 0; i <solidList.get(0).getvB().size() ; i++) {
                        solidList.get(0).getvB().get(i).setPosition(solidList.get(0).getvB().get(i).transform(new Mat4RotY(0.03)).getPosition());
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                    for (int i = 0; i <solidList.get(0).getvB().size() ; i++) {
                        solidList.get(0).getvB().get(i).setPosition(solidList.get(0).getvB().get(i).transform(new Mat4RotZ(0.03)).getPosition());
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
                    for (int k = 0; k < 360; k++) {


                    for (int i = 0; i <solidList.get(0).getvB().size() ; i++) {
                        solidList.get(0).getvB().get(i).setPosition(solidList.get(0).getvB().get(i).transform(new Mat4RotX(0.03)).getPosition());
                        solidList.get(0).getvB().get(i).setPosition(solidList.get(0).getvB().get(i).transform(new Mat4RotY(0.03)).getPosition());
                        solidList.get(0).getvB().get(i).setPosition(solidList.get(0).getvB().get(i).transform(new Mat4RotZ(0.03)).getPosition());
                    }

                    }
                }
                redraw();

                // /- Translace

                // - Překreslení
                redraw();
                // - /Překreslení
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
