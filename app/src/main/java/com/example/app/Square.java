package com.example.app;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.util.Log;

import com.example.app.util.ShaderUtils;
import com.example.app.util.TextureUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by josaxa on 2014-01-23.
 */
public class Square {

    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;
    private final FloatBuffer textureCoordinates;
    private final int shaderProgram;
    private final int textureDataHandle;

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static float squareCoords[] = {
            -0.5f,  0.5f, 0.0f,   // top left
            -0.5f, -0.5f, 0.0f,   // bottom left
            0.5f, -0.5f, 0.0f,   // bottom right
            0.5f,  0.5f, 0.0f }; // top right

    final float[] squareTextureCoords = {
            1.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 1.0f,
            0.0f, 0.0f };

    private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices

    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex


    public Square(final Bitmap texture, final AssetManager assets) {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        //
        // load texture and create texturecoordinates
        //
        textureDataHandle = TextureUtils.createTexture(texture);
        textureCoordinates = ByteBuffer.allocateDirect(squareTextureCoords.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        textureCoordinates.put(squareTextureCoords);
        textureCoordinates.position(0);

        //
        // create shader program
        //
        String vertexShaderCode = ShaderUtils.loadShader(assets, "shader/simple_vertex.glsl");
        String fragmentShaderCode = ShaderUtils.loadShader(assets, "shader/simple_fragment.glsl");

        final int vertexShader = ShaderUtils.createShader(GLES30.GL_VERTEX_SHADER, vertexShaderCode);
        final int fragmentShader = ShaderUtils.createShader(GLES30.GL_FRAGMENT_SHADER, fragmentShaderCode);

        shaderProgram = GLES30.glCreateProgram();
        GLES30.glAttachShader(shaderProgram, vertexShader);
        //OpenGLRenderer.checkGlError("glAttachShader");

        GLES30.glAttachShader(shaderProgram, fragmentShader);
        //OpenGLRenderer.checkGlError("glAttachShader");

        GLES30.glLinkProgram(shaderProgram);
        //OpenGLRenderer.checkGlError("glLinkProgram");
    }

    public void draw(float[] viewMatrix, float[] projectionMatrix) {

        //boolean isProgram = GLES30.glIsProgram(shaderProgram);
        //Log.d("Square.draw", "glIsProgram(shaderProgram) == " + isProgram);

        //Log.d("Square.draw", "glGetProgramInfoLog: " + GLES30.glGetProgramInfoLog(shaderProgram));

        // Add program to OpenGL ES environment
        GLES30.glUseProgram(shaderProgram);
        //OpenGLRenderer.checkGlError("glUseProgram");

        final int mTextureUniformHandle = GLES30.glGetUniformLocation(shaderProgram, "uTexture");

        // Set the active texture unit to texture unit 0.
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);

        // Bind the texture to this unit.
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureDataHandle);

        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES30.glUniform1i(mTextureUniformHandle, 0);

        // get handle to vertex shader's vPosition member
        int mPositionHandle = GLES30.glGetAttribLocation(shaderProgram, "vPosition");
        //OpenGLRenderer.checkGlError("glGetAttribLocation");

        // Enable a handle to the triangle vertices
        GLES30.glEnableVertexAttribArray(mPositionHandle);
        //OpenGLRenderer.checkGlError("glEnableVertexAttribArray");

        // Prepare the triangle coordinate data
        GLES30.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES30.GL_FLOAT, false,
                vertexStride, vertexBuffer);
        //OpenGLRenderer.checkGlError("glVertexAttribPointer");
/*
        // get handle to fragment shader's vColor member
        int mColorHandle = GLES30.glGetUniformLocation(shaderProgram, "vColor");
        OpenGLRenderer.checkGlError("glGetUniformLocation");

        float color[] = {0.6f, 0.2f, 0.4f, 1.0f};

        // Set color for drawing the triangle
        GLES30.glUniform4fv(mColorHandle, 1, color, 0);
        OpenGLRenderer.checkGlError("glUniform4fv");
*/
        final int mTextureCoordinateHandle = GLES30.glGetAttribLocation(shaderProgram, "aTexCoord");

        textureCoordinates.position(0);
        GLES30.glVertexAttribPointer(mTextureCoordinateHandle,
                2 /** Size of the texture coordinate data in elements. */,
                GLES30.GL_FLOAT, false,
                0, textureCoordinates);

        GLES30.glEnableVertexAttribArray(mTextureCoordinateHandle);

        // get handle to shape's transformation matrix
        int projectionMatrixHandle = GLES30.glGetUniformLocation(shaderProgram, "uProjectionMatrix");
        //OpenGLRenderer.checkGlError("glGetUniformLocation");

        // Apply the projection and view transformation
        GLES30.glUniformMatrix4fv(projectionMatrixHandle, 1, false, projectionMatrix, 0);
        //OpenGLRenderer.checkGlError("glUniformMatrix4fv");

        // get handle to shape's transformation matrix
        int viewMatrixHandle = GLES30.glGetUniformLocation(shaderProgram, "uViewMatrix");
        //OpenGLRenderer.checkGlError("glGetUniformLocation");

        // Apply the projection and view transformation
        GLES30.glUniformMatrix4fv(viewMatrixHandle, 1, false, viewMatrix, 0);
        //OpenGLRenderer.checkGlError("glUniformMatrix4fv");


        //MyGLRenderer.checkGlError("glUniformMatrix4fv");
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, drawOrder.length, GLES30.GL_UNSIGNED_SHORT, drawListBuffer);
        //OpenGLRenderer.checkGlError("glDrawElements");

        // Disable vertex array
        GLES30.glDisableVertexAttribArray(mPositionHandle);
    }
}