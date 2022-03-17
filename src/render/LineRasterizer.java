package render;

import model.Vertex;
import raster.ZBufferVisibility;
import transforms.Vec3D;

//Extendování (dědění) abstraktní třídy LineRasterizer
public class LineRasterizer {
    private ZBufferVisibility zBuffer;
    private int width, height;
    private Vertex vertex1, vertex2;

    //Kontruktor třídy
    public LineRasterizer(ZBufferVisibility zBuffer) {

        this.zBuffer = zBuffer;
        this.width = zBuffer.getiBuffer().getWidth();
        this.height = zBuffer.getiBuffer().getHeight();
    }


    // Metoda pro rasterizování zadané linie
    public void rasterize(Vertex v1, Vertex v2) {
        vertex1 = v1;
        vertex2 = v2;

        //Uprava dat
        Vec3D a = doMath(vertex1);
        Vec3D b = doMath(vertex2);

        //Vykreslení linií


        //Ořezání
        /*
        if ((
                (a.getX() > width - 1 || a.getX() < 0) &&
                        (b.getX() > width - 1 || b.getX() < 0)
        ) || (
                (a.getY() > height - 1 || a.getY() < 0) &&
                        (b.getY() > height - 1 || b.getY() < 0)
        )) {
            return;
        }
        */


        //Seřazení

        Vec3D vecHelp;

        if (a.getY() >= b.getY()) {
            vecHelp = b;
            b = a;
            a = vecHelp;
        }

        if (
                a.getX() > 0 &&
                        a.getX() < width &&
                        a.getY() > 0 &&
                        a.getY() < height &&

                        b.getX() > 0 &&
                        b.getX() < width &&
                        b.getY() > 0 &&
                        b.getY() < height

        ) {
            zBuffer.getiBuffer().getGraphics().drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
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

