package com.example.app;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.example.app.util.TextureUtils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by josaxa on 2014-01-22.
 */
public class OpenGLRenderer implements GLSurfaceView.Renderer {

    private final Bitmap squareTexture;
    private final AssetManager assets;
    private Square square;

    public OpenGLRenderer(final Context context) {
        assets = context.getAssets();
        squareTexture = TextureUtils.loadTextureData(assets, "texture/placeholder512.png");

        Matrix.setLookAtM(viewMatrix, 0,
                          0f, 0f, -3f, // eye XYZ
                          0f, 0f, 0f,  // center XYZ
                          0f, 1f, 0f); // up XYZ
    }

    private final float[] projectionMatrix = new float[4 * 4];
    private final float[] viewMatrix = new float[4 * 4];
    private final float[] modelViewProjectionMatrix = new float[4 * 4];

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES30.glClearColor(0.3f, 0.3f, 0.7f, 1.0f);

        square = new Square(squareTexture, assets);
        squareTexture.recycle();
    }

    public void onDrawFrame(GL10 unused) {



        /*Matrix.multiplyMM(modelViewProjectionMatrix, 0,
                          viewMatrix, 0,
                          projectionMatrix, 0);*/


        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        square.draw(viewMatrix, projectionMatrix);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES30.glViewport(0, 0, width, height);

        final float aspectRatio = (float)width / height;
        Matrix.frustumM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1, 1, 3, 7);
    }

    public static void checkGlError(final String glOperation) {
        int error;
        while ((error = GLES30.glGetError()) != GLES30.GL_NO_ERROR) {
            Log.e("OpenGLRenderer", glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }
}
