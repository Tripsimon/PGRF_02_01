package model;

import transforms.Col;

public class ShaderFullColor implements Shader{
    @Override
    public Col shade(Vertex v) {
        return new Col(1., 0, 0);
    }
}
