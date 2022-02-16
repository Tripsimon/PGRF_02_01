package render;

import raster.ZBufferVisibility;

public class TriangleRasterizer {

    private ZBufferVisibility zBuffer;

    public TriangleRasterizer(ZBufferVisibility zBuffer) {
        this.zBuffer = zBuffer;
    }
}
