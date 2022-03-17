package model;

import transforms.*;

import java.util.Optional;

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

    public Vertex mul(double d){
        return new Vertex(position.mul(d), color.mul(d), textCoords.mul(d));
    }

    public Optional<Vec3D> dehomogenize(){
        return  position.dehomog();
    }


    public void setPosition(Point3D p){
        this.position = p;
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
