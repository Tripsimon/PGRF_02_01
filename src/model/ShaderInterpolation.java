package model;

import transforms.Col;

public class ShaderInterpolation implements Shader{
    @Override
    public Col shade(Vertex v) {
        return v.getColor();
    }
}
