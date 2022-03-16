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
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                buffer[x][y] = clearValue;
            }
        }
    }

    @Override
    public void setClearValue(Double value) {
        this.clearValue = value;
    }

    @Override
    public int getWidth() {
        return buffer.length;
    }

    @Override
    public int getHeight() {
        if (getWidth() > 0) {
            return buffer[0].length;
        }
        return 0;
    }

    @Override
    public Double getElement(int x, int y) {
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

    public boolean testElement(int x, int y, double z) {
        Double currentDepth = getElement(x, y);
        boolean result = (currentDepth != null) && (currentDepth > z && z >= 0);
        return result;
    }

}
