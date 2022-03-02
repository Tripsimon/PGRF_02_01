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
    private int width, heigth;
    private Shader shader;

    public TriangleRasterizer(ZBufferVisibility zBuffer, Shader shader) {
        this.zBuffer = zBuffer;
        this.width = zBuffer.getiBuffer().getWidth();
        this.heigth = zBuffer.getiBuffer().getHeight();
        this.shader = shader;
    }

    public void rasterize(Vertex v1, Vertex v2, Vertex v3) {
        Vec3D a = v1.getPosition().ignoreW()
                .mul(new Vec3D(1, -1, 1))
                .add(new Vec3D(1, 1, 0))
                .mul(new Vec3D((width - 1) / 2., (heigth - 1) / 2., 1));

        Vec3D b = v2.getPosition().ignoreW()
                .mul(new Vec3D(1, -1, 1))
                .add(new Vec3D(1, 1, 0))
                .mul(new Vec3D((width - 1) / 2., (heigth - 1) / 2., 1));

        Vec3D c = v3.getPosition().ignoreW()
                .mul(new Vec3D(1, -1, 1))
                .add(new Vec3D(1, 1, 0))
                .mul(new Vec3D((width - 1) / 2., (heigth - 1) / 2., 1));

        zBuffer.getiBuffer().getGraphics().setColor(new Color(0xffff00));
        zBuffer.getiBuffer().getGraphics().drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
        zBuffer.getiBuffer().getGraphics().drawLine((int)b.getX(), (int)b.getY(), (int)c.getX(), (int)c.getY());
        zBuffer.getiBuffer().getGraphics().drawLine((int)c.getX(), (int)c.getY(), (int)a.getX(), (int)a.getY());

        // TODO: Seřadit vrcholy podle y
        // Od A po B, interpolace
        for (int y = (int) a.getY(); y < b.getY(); y++){
            // Interpolační koeficient. Odečtu minimum (y - Ay), dělím rozsahem (By - Ay)
            double s1 = (y-a.getY())/(b.getY()-a.getY());
            // x1 = Ax*(1-s1)+Bx*s1. Slide 129
            int x1 = (int)(a.getX()*(1-s1)+b.getX()*s1);
            Vertex v12 = v1.mul(1 - s1).add(v2.mul(s1));

            // Interpolační koeficient. Odečtu minimum (y - Ay), dělím rozsahem (Cy - Ay)
            double s2 = (y-a.getY())/(c.getY()-a.getY());
            // x2 = Ax*(1-s2)+Cx*s2
            int x2 = (int)( a.getX()*(1-s2)+c.getX()*s2);
            Vertex v13 = v1.mul(1 - s2).add(v3.mul(s2));

            for(int x = x1; x < x2; x++)
            {
                double t = (x - x1) / (double)(x2 - x1);
                Vertex v = v12.mul(1 - t).add(v13.mul(t));

                // Todo: interpolace Z
                zBuffer.drawPixelWithTest(x,y,0.5, shader.shade(v));
            }
        }


    }
}
