package com.example.app;

public abstract class ModelDefinition {
    abstract short[] getDrawOrder();
    abstract float[] getVertices();
    abstract float[] getNormals();
    abstract float[] getUvs();
}
