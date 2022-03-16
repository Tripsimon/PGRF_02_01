package raster;

public class DepthBuffer implements Raster<Double> {
    private final double[][] buffer;
    private final int width, height;
    private double clearValue;

    public DepthBuffer(int width, int height) {
        this.buffer = new double[width][height];
        this.width = width;
        this.height = height;
        clearValue = 10.;
    }



    @Override
    public void clear() {

    }

    @Override
    public void setClearValue(Double value) {
        this.clearValue = value;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public Double getElement(int x, int y) {
        System.out.println(x);
        System.out.println(getWidth());
        if (getWidth() > x && x > -1 && getHeight() > y && y > -1) {
            return buffer[x][y];
        }
        return null;
    }

    public void setElement(int x, int y, Double value) {
        if (getWidth() > x && getHeight() > y) {
            buffer[x][y] = value;
        }
    }

    public boolean testElement(int x, int y, double z){
        Double zTest = getElement(x, y);
        return (zTest != null) && (zTest > z && z >= 0);
    }
}
