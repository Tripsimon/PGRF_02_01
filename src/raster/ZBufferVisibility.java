package raster;

import transforms.Col;

public class ZBufferVisibility {
    private ImageBuffer iBuffer;
    private DepthBuffer dBuffer;

    public ZBufferVisibility(ImageBuffer iBuffer) {
        this.iBuffer = iBuffer;
         this.dBuffer = new DepthBuffer(iBuffer.getWidth(),iBuffer.getHeight());
    }

    public void drawPixelWithTest(int x, int y, double z, Col color) {

        if (dBuffer.testElement(x,y,z)){
            dBuffer.setElement(x, y, z);
            iBuffer.setElement(x, y, color);
        }

    }

    public ImageBuffer getiBuffer() {
        return iBuffer;
    }
}
