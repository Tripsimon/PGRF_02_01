package render;
import model.Vertex;
import raster.Raster;
import raster.ZBufferVisibility;
import solids.Line;
import transforms.Col;
import transforms.Point3D;
import transforms.Vec3D;

import java.awt.*;
import java.util.Arrays;

//Extendování (dědění) abstraktní třídy LineRasterizer
public class LineRasterizer{
    private ZBufferVisibility zBuffer;
    private int width, height;
    //Kontruktor třídy
    public LineRasterizer(ZBufferVisibility zBuffer) {

        this.zBuffer = zBuffer;
        this.width = zBuffer.getiBuffer().getWidth();
        this.height = zBuffer.getiBuffer().getHeight();
    }


    // Metoda pro rasterizování zadané linie
    public void rasterize(Vertex v1, Vertex v2) {
        /*
        Vec3D a = doMath(v1);
        Vec3D b = doMath(v2);

        zBuffer.getiBuffer().getGraphics().drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
        */

        Vec3D bs = v1.dehomogenize().get().mul(new Vec3D(1, -1, 1)).add(new Vec3D(1, 1, 0)).mul(new Vec3D((width - 1) / 2., (height - 1) / 2., 1));
        Vec3D as = v2.dehomogenize().get().mul(new Vec3D(1, -1, 1)).add(new Vec3D(1, 1, 0)).mul(new Vec3D((width - 1) / 2., (height - 1) / 2., 1));

        //zahozeni hran, ktere nebudou videt
        if ((
                (as.getX() > width - 1 || as.getX() < 0) &&
                        (bs.getX() > width - 1 || bs.getX() < 0)
        ) || (
                (as.getY() > height - 1 || as.getY() < 0) &&
                        (bs.getY() > height - 1 || bs.getY() < 0)
        )) {
            return;
        }

        int x1 = (int) as.getX();
        int x2 = (int) bs.getX();
        int y1 = (int) as.getY();
        int y2 = (int) bs.getY();
        int x, y, dx, dy, incx, incy, o;

        if (x2 >= x1) {
            dx = x2 - x1;
            incx = 1;
        } else {
            dx = x1 - x2;
            incx = -1;
        }
        if (y2 >= y1) {
            dy = y2 - y1;
            incy = 1;
        } else {
            dy = y1 - y2;
            incy = -1;
        }
        x = x1;
        y = y1;
        if (dx >= dy) {
            dy <<= 1;
            o = dy - dx;
            dx <<= 1;
            while (x != x2) {
                double s = (y - as.getY()) / (bs.getY() - as.getY());
                Vec3D ab = as.mul(1 - s).add(bs.mul(s));
                Vertex vAB = v1.mul(1 - s).add(v2.mul(s));
                zBuffer.drawPixelWithTest(x, y, ab.getZ(), new Col(255,255,255));
                if (o >= 0) {
                    y += incy;
                    o -= dx;
                }
                o += dy;
                x += incx;
            }
        } else {
            dx <<= 1;
            o = dx - dy;
            dy <<= 1;
            while (y != y2) {
                double s = (y - as.getY()) / (bs.getY() - as.getY());
                Vec3D ab = as.mul(1 - s).add(bs.mul(s));
                Vertex vAB = v2.mul(1 - s).add(v1.mul(s));
                zBuffer.drawPixelWithTest(x, y, ab.getZ(), new Col(255,255,255));
                if (o >= 0) {
                    x += incx;
                    o -= dy;
                }
                o += dx;
                y += incy;
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

