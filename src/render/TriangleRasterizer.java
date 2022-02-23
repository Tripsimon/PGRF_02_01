package render;

import model.Vertex;
import raster.ZBufferVisibility;
import transforms.Col;
import transforms.Point3D;
import transforms.Vec3D;

public class TriangleRasterizer {

    private ZBufferVisibility zBuffer;
    private int width,height;

    public TriangleRasterizer(ZBufferVisibility zBuffer) {
        this.zBuffer = zBuffer;
        width = zBuffer.getiBuffer().getWidth();
        height = zBuffer.getiBuffer().getHeight();
    }

    public  void rasterize(Vertex p1, Vertex p2, Vertex p3){
            Vec3D A = doMath(p1.getPosition());
            Vec3D B = doMath(p2.getPosition());
            Vec3D C = doMath(p3.getPosition());
            Vec3D help;
            zBuffer.getiBuffer().getGraphics().drawLine((int)A.getX(),(int)A.getY(),(int)B.getX(),(int)B.getY());
        zBuffer.getiBuffer().getGraphics().drawLine((int)A.getX(),(int)A.getY(),(int)C.getX(),(int)C.getY());
        zBuffer.getiBuffer().getGraphics().drawLine((int)B.getX(),(int)B.getY(),(int)C.getX(),(int)C.getY());

/*
        if (B.getY() > C.getY()){
                help = B;
                B = C;
                C = help;
            }

        if (A.getY() > B.getY()){
            help = A;
            A = B;
            B = help;
        }

        if (B.getY() > C.getY()){
            help = B;
            B = C;
            C = help;
        }
        */


        for(int l = (int)A.getY(); l < C.getY();l++){
            double s1 = (l-A.getY())/(B.getY()/A.getY());
            double s2 = (l-A.getY())/(C.getY()/A.getY());
            int x1 = (int)(A.getX()*(1-s1)+B.getX()*s1);
            int x2 = (int)(A.getX()*(1-s2)+C.getX()*s2);

            for (int k = x1; k<x2;k++) {
                zBuffer.drawPixelWithTest(k,l,0.5,new Col(0xff00));
            }

        }

    }

    private Vec3D doMath(Point3D p){
        Vec3D a = p.ignoreW()
                .mul(new Vec3D(1,-1,0))
                .add(new Vec3D(1,1,0))
                .mul(new Vec3D((width-1)/2.,(height-1)/2.,1));
        return a;
    }
}
