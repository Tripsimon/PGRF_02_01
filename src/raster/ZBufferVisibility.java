package raster;

import transforms.Col;

public class ZBufferVisibility {
    private ImageBuffer iBuffer;
    private DepthBuffer dBuffer;

    public ZBufferVisibility(ImageBuffer iBuffer) {
        this.iBuffer = iBuffer;
        //this.dBuffer = new DepthBuffer();
        //TODO: init depth bufferu
    }

    public  void drawPixelWithTest(int x, int y, double z, Col color){
        iBuffer.setElement(x,y,color);
    }

    public ImageBuffer getiBuffer() {
        return iBuffer;
    }

    public DepthBuffer getdBuffer() {
        return dBuffer;
    }
}
