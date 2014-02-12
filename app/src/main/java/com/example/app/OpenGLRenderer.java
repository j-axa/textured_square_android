package com.example.app;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.example.app.util.ShaderUtils;
import com.example.app.util.TextureUtils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by josaxa on 2014-01-22.
 */
public class OpenGLRenderer implements GLSurfaceView.Renderer {

/*    private final Bitmap squareTexture;
    private final String squareVertexShaderCode;
    private final String squareFragmentShaderCode;
    private Square square;
*/
    private final Bitmap fontSheetTexture ;
    private final String fontSheetVertexShaderCode ;
    private final String fontSheetFragmentShaderCode;
    private BitmapFont font;
    private BitmapText fpsText;
    private int frameCount;
    private long lastFpsUpdate;

    private final Bitmap fighterTexture;
    private final String fighterVertexShaderCode;
    private final String fighterFragmentShaderCode;
    private ModelDefinition fighter;
    private Model fighterModel;

    public OpenGLRenderer(final Context context) {
        AssetManager assets = context.getAssets();
        /*squareTexture = TextureUtils.loadTextureData(assets, "texture/placeholder512.png");
        squareVertexShaderCode = ShaderUtils.loadShader(assets, "shader/simple_vertex.glsl");
        squareFragmentShaderCode = ShaderUtils.loadShader(assets, "shader/simple_fragment.glsl");
*/
        fontSheetTexture = TextureUtils.loadTextureData(assets, "texture/bmpfont1.png");
        fontSheetVertexShaderCode = ShaderUtils.loadShader(assets, "shader/bmpfont_vertex.glsl");
        fontSheetFragmentShaderCode = ShaderUtils.loadShader(assets, "shader/bmpfont_fragment.glsl");

        fighterTexture = TextureUtils.loadTextureData(assets, "texture/fighter.png");
        fighterVertexShaderCode = ShaderUtils.loadShader(assets, "shader/fighter_vertex.glsl");
        fighterFragmentShaderCode = ShaderUtils.loadShader(assets, "shader/fighter_fragment.glsl");

        final String fighterObj = ShaderUtils.loadShader(assets, "model/fighter.obj");
        fighter = ObjLoader.parseObj(fighterObj);

        Matrix.setLookAtM(viewMatrix, 0,
                          0f, 0f, -10f, // eye XYZ
                          0f, 0f, 0f,  // center XYZ
                          0f, 1f, 0f); // up XYZ
    }

    private final float[] projectionMatrix = new float[4 * 4];
    private final float[] viewMatrix = new float[4 * 4];
    //private final float[] modelViewProjectionMatrix = new float[4 * 4];

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES30.glClearColor(0.3f, 0.3f, 0.7f, 1.0f);

        // depth testing necessary to not draw further away faces on top of nearer ones that should obscure the further faces
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);
        GLES30.glDepthFunc(GLES30.GL_LEQUAL);

        // cull backfaces, apparently my winding is clockwise, since this gives the correct result :)
        GLES30.glEnable(GLES30.GL_CULL_FACE);
        GLES30.glCullFace(GLES30.GL_BACK);
        GLES30.glFrontFace(GLES30.GL_CW);

        fighterModel = new Model(fighter, fighterTexture, fighterVertexShaderCode, fighterFragmentShaderCode);
        //square = new Square(squareTexture, squareVertexShaderCode, squareFragmentShaderCode);
        final String fontMap = " !*+,-./0123\"456789:;<=#>?@ABCDEFG$HIJKLMNOPQ%RSTUVWXYZ[&\\]^_`'(){|}~";
        font = new BitmapFont(576, 32, 16, 16, fontSheetTexture, fontMap, fontSheetVertexShaderCode, fontSheetFragmentShaderCode);
        fpsText = new BitmapText(font, "");
    }

    public void onDrawFrame(GL10 unused) {
        // must glclear with depth_buffer_bit with depth_test enabled
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);
        final long time = System.nanoTime();
        final int itime = (int)(time / 1000);
        //square.draw(viewMatrix, projectionMatrix);
        fighterModel.draw(viewMatrix, projectionMatrix, itime);

        frameCount++;
        if(time - lastFpsUpdate >= 1000000000) {
            fpsText = new BitmapText(font, String.format("FPS: %d", frameCount));
            frameCount = 0;
            lastFpsUpdate = System.nanoTime();
        }
        fpsText.draw();
    }


    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES30.glViewport(0, 0, width, height);

        final float aspectRatio = (float)width / height;
        //Matrix.orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1, 1, 1, 100);
        Matrix.frustumM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1, 1, 1, 100);
    }

    public static void checkGlError(final String glOperation) {
        int error;
        while ((error = GLES30.glGetError()) != GLES30.GL_NO_ERROR) {
            Log.e("OpenGLRenderer", glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }
}

