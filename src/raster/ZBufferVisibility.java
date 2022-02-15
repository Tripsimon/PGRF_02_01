package raster;

import transforms.Col;

public class ZBufferVisibility {
    private ImageBuffer iBuffer;
    private DepthBuffer dBuffer;

    public ZBufferVisibility(ImageBuffer iBuffer) {
        this.iBuffer = iBuffer;
        // TODO: init depth bufferu
        this.dBuffer = new DepthBuffer();
    }

    public void drawPixelWithTest(int x, int y, double z, Col color) {
        // TODO: implementace algoritmu z-buffer
    }
}
