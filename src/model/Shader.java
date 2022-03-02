package model;

import transforms.Col;

@FunctionalInterface
public interface Shader {
    Col shade(Vertex v);
}
