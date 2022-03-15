package model;

import transforms.Col;
import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec2D;

public class Vertex {
    private Point3D position;
    private Col color;
    private Vec2D textCoords;
    private double one;


    // TODO: normála
    // TODO: souřadnice do textury

    public Vertex(double x, double y, double z, Col color) {
        this.position = new Point3D(x, y, z);
        this.color = color;
        // TODO: implementovat vhodně nastavení souřadnic do textury
        this.textCoords = new Vec2D(color.getR(), color.getB());
        this.one = 1;
    }

    public Vertex (Point3D p, Col col, Vec2D textCoords){
        this.position = p;
        this.color = col;
        this.textCoords = textCoords;
    }

    public Vertex (Vertex ver){
        this.position = ver.getPosition();
        this.color = ver.getColor();
        this.textCoords = ver.getTextCoords();
    }

    public Point3D getPosition() {
        return position;
    }

    public Col getColor() {
        return color;
    }

    public Vec2D getTextCoords() {
        return textCoords;
    }

    // TODO: mul
    public Vertex mul(double d){
        return new Vertex(position.mul(d), color.mul(d), textCoords.mul(d));
    }

    // TODO: add
    public Vertex add(Vertex v){
        return new Vertex(
                position.add(v.getPosition()),
                color.add(v.getColor()),
                textCoords.add(v.getTextCoords())
        );
    }
    // TODO: interface?

    @Override
    public String toString() {
        return "Vertex{" +
                "position=" + position +
                '}';
    }

    public Vertex transform(Mat4 matrix) {
        Vertex returnVer = new Vertex(position.mul(matrix),this.color,textCoords);
        return returnVer;
    }
}
