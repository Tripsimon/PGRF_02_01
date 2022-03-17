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


    public void rasterize(Vertex v1, Vertex v2, Vertex v3, boolean wireFrame, boolean gradient) {

        vertex1 = v1;
        vertex2 = v2;
        vertex3 = v3;

        //Uprava dat
        Vec3D a = doMath(vertex1);
        Vec3D b = doMath(vertex2);
        Vec3D c = doMath(vertex3);

        //Vykreslení linií


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

        if (a.getY() >= b.getY()) {
            vecHelp = b;
            b = a;
            a = vecHelp;
        }

        if (a.getY() >= c.getY()) {
            vecHelp = c;
            c = a;
            a = vecHelp;
        }

        if (b.getY() >= c.getY()) {
            vecHelp = c;
            c = b;
            b = vecHelp;
        }

        if (wireFrame) {
            if (true //Možnost přepnutí na jednoduché ořezání
                    /*
                    a.getX() > 0 &&
                            a.getX() < width &&
                            a.getY() > 0 &&
                            a.getY() < height &&

                            b.getX() > 0 &&
                            b.getX() < width &&
                            b.getY() > 0 &&
                            b.getY() < height &&

                            c.getX() > 0 &&
                            c.getX() < width &&
                            c.getY() > 0 &&
                            c.getY() < height
                            */

            ) {
                zBuffer.getiBuffer().getGraphics().drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
                zBuffer.getiBuffer().getGraphics().drawLine((int) b.getX(), (int) b.getY(), (int) c.getX(), (int) c.getY());
                zBuffer.getiBuffer().getGraphics().drawLine((int) c.getX(), (int) c.getY(), (int) a.getX(), (int) a.getY());
            }
        } else {

            // Od A po B, interpolace
            for (int y = (int) a.getY(); y < b.getY(); y++) {

                double s1 = (y - a.getY()) / (b.getY() - a.getY());
                double s2 = (y - a.getY()) / (c.getY() - a.getY());
                int x1 = (int) (a.getX() * (1 - s1) + b.getX() * s1);
                int x2 = (int) (a.getX() * (1 - s2) + c.getX() * s2);

                if (x1 > x2) {
                    int help = x1;
                    x1 = x2;
                    x2 = help;
                }

                Vec3D point12 = a.mul(1 - s1).add(a.mul(s1));
                Vec3D point13 = a.mul(1 - s1).add(c.mul(s1));

                Vertex v12 = v1.mul(1 - s1).add(v2.mul(s1));
                Vertex v13 = v1.mul(1 - s2).add(v3.mul(s2));


                for (int x = x1; x < x2; x++) {

                    double t = (x - x1) / (double) (x2 - x1);
                    Vec3D result = point12.mul(1 - t).add(point13.mul(t));
                    Vertex v = v12.mul(1 - t).add(v13.mul(t));

                    if (x > 0 && x < width && y > 0 && y < height) {
                        if (gradient) {
                            zBuffer.drawPixelWithTest(x, y, result.getZ(), shader.shade(v));
                        } else {
                            zBuffer.drawPixelWithTest(x, y, result.getZ(), v1.getColor());
                        }
                    }
                }


            }


            // Od B po C, interpolace
            for (int y = (int) b.getY(); y < c.getY(); y++) {

                double s1 = (y - b.getY()) / (c.getY() - b.getY());
                double s2 = (y - a.getY()) / (c.getY() - a.getY());

                int x1 = (int) (b.getX() * (1 - s1) + c.getX() * s1);
                int x2 = (int) (a.getX() * (1 - s2) + c.getX() * s2);

                if (x1 > x2) {
                    int help = x1;
                    x1 = x2;
                    x2 = help;
                }

                Vec3D point23 = b.mul(1 - s1).add(c.mul(s1));
                Vec3D point13 = a.mul(1 - s1).add(c.mul(s1));

                Vertex v23 = v2.mul(1 - s1).add(v2.mul(s1));
                Vertex v13 = v1.mul(1 - s2).add(v3.mul(s2));
                for (int x = x1; x < x2; x++) {

                    double t = (x - x1) / (double) (x2 - x1);
                    Vec3D result = point23.mul(1 - t).add(point13.mul(t));
                    Vertex v = v23.mul(1 - t).add(v13.mul(t));
                    if (x > 0 && x < width && y > 0 && y < height) {
                        if (gradient) {
                            zBuffer.drawPixelWithTest(x, y, result.getZ(), shader.shade(v));
                        } else {
                            zBuffer.drawPixelWithTest(x, y, result.getZ(), v1.getColor());
                        }
                    }
                }
            }
            zBuffer.getiBuffer().getGraphics().drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
            zBuffer.getiBuffer().getGraphics().drawLine((int) b.getX(), (int) b.getY(), (int) c.getX(), (int) c.getY());
            zBuffer.getiBuffer().getGraphics().drawLine((int) c.getX(), (int) c.getY(), (int) a.getX(), (int) a.getY());
        }

    }

    private Vec3D doMath(Vertex v) {
        Vec3D response = v.getPosition().ignoreW()
                .mul(new Vec3D(1, -1, 1))
                .add(new Vec3D(1, 1, 0))
                .mul(new Vec3D((width - 1) / 2., (height - 1) / 2., 1));
        return response;
    }

    public ZBufferVisibility getzBuffer() {
        return zBuffer;
    }
}
