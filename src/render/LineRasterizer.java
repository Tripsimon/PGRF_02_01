package render;
import raster.Raster;
import raster.ZBufferVisibility;
import solids.Line;
import transforms.Point3D;

import java.awt.*;

//Extendování (dědění) abstraktní třídy LineRasterizer
public class LineRasterizer{
    private ZBufferVisibility zBuffer;
    //Kontruktor třídy
    public LineRasterizer(ZBufferVisibility zBuffer) {
        this.zBuffer = zBuffer;
    }


    // Metoda pro rasterizování zadané linie
    public void rasterize(Line line) {

    }
}

