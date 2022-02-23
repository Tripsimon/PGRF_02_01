package render;

import raster.ZBufferVisibility;
import transforms.Col;
import transforms.Point3D;
import transforms.Vec3D;

import java.awt.*;

public class TriangleRasterizer {
    private ZBufferVisibility zBuffer;
    private int width, heigth;

    public TriangleRasterizer(ZBufferVisibility zBuffer) {
        this.zBuffer = zBuffer;
        this.width = zBuffer.getiBuffer().getWidth();
        this.heigth = zBuffer.getiBuffer().getHeight();
    }

    public void rasterize(Point3D p1, Point3D p2, Point3D p3) {
        Vec3D a = p1.ignoreW()
                .mul(new Vec3D(1, -1, 1))
                .add(new Vec3D(1, 1, 0))
                .mul(new Vec3D((width - 1) / 2., (heigth - 1) / 2., 1));

        Vec3D b = p2.ignoreW()
                .mul(new Vec3D(1, -1, 1))
                .add(new Vec3D(1, 1, 0))
                .mul(new Vec3D((width - 1) / 2., (heigth - 1) / 2., 1));

        Vec3D c = p3.ignoreW()
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
            // Interpolační koeficient. Odečtu minimum (y - Ay), dělím rozsahem (Cy - Ay)
            double s2 = (y-a.getY())/(c.getY()-a.getY());
            // x2 = Ax*(1-s2)+Cx*s2
            int x2 = (int)( a.getX()*(1-s2)+c.getX()*s2);

            for(int x = x1; x < x2; x++)
            {
                // Todo: interpolace Z
                zBuffer.drawPixelWithTest(x,y,0.5, new Col(0xff00));
            }
        }


    }
}
