package raster;

public class DepthBuffer implements Raster<Double> {

    private double[][] buffer;
    private int width, height;
    private double clear;
    public DepthBuffer(int width, int height)  {
        this.width = width;
        this.height = height;
        this.buffer = new double[width][height];
    }

    @Override
    public void clear() {

    }

    @Override
    public void setClearValue(Double value) {

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
        return null;
    }

    @Override
    public void setElement(int x, int y, Double value) {

    }
}
