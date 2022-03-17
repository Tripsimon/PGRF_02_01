package render;
import model.Vertex;
import raster.Raster;
import raster.ZBufferVisibility;
import solids.Line;
import transforms.Point3D;
import transforms.Vec3D;

import java.awt.*;

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
        Vec3D a = doMath(v1);
        Vec3D b = doMath(v2);

        zBuffer.getiBuffer().getGraphics().drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
    }

    private Vec3D doMath(Vertex v) {
        Vec3D response = v.getPosition().ignoreW()
                .mul(new Vec3D(1, -1, 1))
                .add(new Vec3D(1, 1, 0))
                .mul(new Vec3D((width - 1) / 2., (height - 1) / 2., 1));
        return response;
    }
}

