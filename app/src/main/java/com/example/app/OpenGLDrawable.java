package com.example.app;

/**
 * Created by josaxa on 2014-03-14.
 */
public interface OpenGLDrawable {
    void draw(final float[] viewMatrix, final float[] projectionMatrix);
    void update(final long time, final long lastTime, final long frameCount);
}