package render;

import model.Shader;
import model.Vertex;
import raster.ZBufferVisibility;
import transforms.Col;
import transforms.Point3D;
import transforms.Vec3D;

import java.awt.*;

public class TriangleRasterizer {

    private ZBufferVisibility zBuffer;
    private int width, height;
    private Shader shader;
    private Vertex vertex1, vertex2, vertex3;

    public TriangleRasterizer(ZBufferVisibility zBuffer, Shader shader) {
        this.zBuffer = zBuffer;
        this.width = zBuffer.getiBuffer().getWidth();
        this.height = zBuffer.getiBuffer().getHeight();
        this.shader = shader;
    }


    public void rasterize(Vertex v1, Vertex v2, Vertex v3) {

        vertex1 = v1;
        vertex2 = v2;
        vertex3 = v3;

        //Uprava dat
        Vec3D a = doMath(vertex1);
        Vec3D b = doMath(vertex2);
        Vec3D c = doMath(vertex3);

        //Vykreslení linií
        zBuffer.getiBuffer().getGraphics().setColor(new Color(0xFFFF00));
        zBuffer.getiBuffer().getGraphics().drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
        zBuffer.getiBuffer().getGraphics().drawLine((int) b.getX(), (int) b.getY(), (int) c.getX(), (int) c.getY());
        zBuffer.getiBuffer().getGraphics().drawLine((int) c.getX(), (int) c.getY(), (int) a.getX(), (int) a.getY());

        //Ořezání
        if ((
                (a.getX() > width - 1 || a.getX() < 0) &&
                        (b.getX() > width - 1 || b.getX() < 0) &&
                        (c.getX() > width - 1 || c.getX() < 0)
        ) || (
                (a.getY() > height - 1 || a.getY() < 0) &&
                        (b.getY() > height - 1 || b.getY() < 0) &&
                        (c.getY() > height - 1 || c.getY() < 0)
        )) {
            return;
        }

        //Seřazení

        Vec3D vecHelp;
        Vertex vertexHelp;

        System.out.println("A: " + a.getY());
        System.out.println("B: " + b.getY());
        System.out.println("C: " + c.getY());

        if (a.getY() >= b.getY()) {
            vecHelp = b;
            b = a;
            a = vecHelp;

            /*
            vertexHelp = vertex2;
            vertex2 = vertex1;
            vertex1 = vertexHelp;


             */
        }

        if (a.getY() >= c.getY()) {
            vecHelp = c;
            c = a;
            a = vecHelp;

            /*
            vertexHelp = vertex3;
            vertex3 = vertex1;
            vertex1 = vertexHelp;

             */
        }

        if (b.getY() >= c.getY()) {
            vecHelp = c;
            c = b;
            b = vecHelp;

            /*
            vertexHelp = vertex3;
            vertex3 = vertex2;
            vertex2 = vertexHelp;

             */
        }
        System.out.println("A: " + a.getY());
        System.out.println("B: " + b.getY());
        System.out.println("C: " + c.getY());



        // Od A po B, interpolace
        for (int y = (int) a.getY(); y < b.getY(); y++) {
            double s1 = (y - a.getY()) / (b.getY() - a.getY());
            double s2 = (y - a.getY()) / (c.getY() - a.getY());
            // x1 = Ax*(1-s1)+Bx*s1. Slide 129
            int x1 = (int) (a.getX() * (1 - s1) + b.getX() * s1);
            int x2 = (int) (a.getX() * (1 - s2) + c.getX() * s2);




            Vertex v12 = v1.mul(1 - s1).add(v2.mul(s1));
            Vertex v13 = v1.mul(1 - s2).add(v3.mul(s2));

            for (int x = x1; x < x2; x++) {
                double t = (x - x1) / (double) (x2 - x1);
                Vertex v = v12.mul(1 - t).add(v13.mul(t));
                zBuffer.drawPixelWithTest(x, y, 0.1, shader.shade(v));
            }


        }


        // Od B po C, interpolace
        for (int y = (int) b.getY(); y < c.getY(); y++) {
            double s1 = (y - b.getY()) / (c.getY() - b.getY());
            double s2 = (y - a.getY()) / (c.getY() - a.getY());
            int x1 = (int) (b.getX() * (1 - s1) + c.getX() * s1);
            int x2 = (int) (a.getX() * (1 - s2) + c.getX() * s2);

            //Vec3D vec23 = v2.mul(1 - s1).add(v2.mul(s1));
            Vertex v23 = v2.mul(1 - s1).add(v2.mul(s1)); //bc
            Vertex v13 = v1.mul(1 - s2).add(v3.mul(s2)); //ac
            for (int x = x1; x < x2; x++) {
                double t = (x - x1) / (double) (x2 - x1);
                //Vec3D point  = v23.mul(1-t).add(v13.mul(t));
                Vertex v = v23.mul(1 - t).add(v13.mul(t));
                zBuffer.drawPixelWithTest(x, y, 1.2, shader.shade(v));
            }


        }



    }

    private Vec3D doMath(Vertex v) {
        Vec3D response = v.getPosition().ignoreW()
                .mul(new Vec3D(1, -1, 1))
                .add(new Vec3D(1, 1, 0))
                .mul(new Vec3D((width - 1) / 2., (height - 1) / 2., 1));
        return response;
    }
}
