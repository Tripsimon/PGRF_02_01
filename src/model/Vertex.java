package model;

import transforms.Col;
import transforms.Point3D;

public class Vertex {
    private Point3D position;
    private Col color;

    // TODO: normála
    // TODO: souřadnice do textury

    public Vertex(double x, double y, double z) {
        this.position = new Point3D(x, y, z);
    }

    public Point3D getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "position=" + position +
                '}';
    }

    // TODO: mul
    // TODO: add
    // TODO: interface?
}
