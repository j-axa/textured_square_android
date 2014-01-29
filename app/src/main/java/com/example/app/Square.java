package com.example.app;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.opengl.Matrix;
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

    private float[] rotationVector = new float[3];
    private float[] translationVector = new float[3];
    private float[] scaleVector = new float[3];

    final float[] modelMatrix = new float[] {
            1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f
    };


    public Square(final Bitmap texture, final String vertexShaderCode, final String fragmentShaderCode) {
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
        // create shader
        //
        final int vertexShader = ShaderUtils.createShader(GLES30.GL_VERTEX_SHADER, vertexShaderCode);
        final int fragmentShader = ShaderUtils.createShader(GLES30.GL_FRAGMENT_SHADER, fragmentShaderCode);
        shaderProgram = GLES30.glCreateProgram();
        GLES30.glAttachShader(shaderProgram, vertexShader);
        GLES30.glAttachShader(shaderProgram, fragmentShader);
        GLES30.glLinkProgram(shaderProgram);

        translate(0, 5.0f, 0);
        scale(3.0f, 3.0f, 3.0f);
        rotate(0, 0, 45.0f);
    }

    public void draw(float[] viewMatrix, float[] projectionMatrix) {
        GLES30.glUseProgram(shaderProgram);

        //
        // texture
        //
        final int mTextureUniformHandle = GLES30.glGetUniformLocation(shaderProgram, "uTexture");

        // Set the active texture unit to texture unit 0.
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);

        // Bind the texture to this unit.
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureDataHandle);

        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES30.glUniform1i(mTextureUniformHandle, 0);

        final int mTextureCoordinateHandle = GLES30.glGetAttribLocation(shaderProgram, "aTexCoord");

        textureCoordinates.position(0);
        GLES30.glVertexAttribPointer(mTextureCoordinateHandle,
                2 /** Size of the texture coordinate data in elements. */,
                GLES30.GL_FLOAT, false,
                0, textureCoordinates);

        GLES30.glEnableVertexAttribArray(mTextureCoordinateHandle);

        //
        // geometry
        //
        // get handle to vertex shader's vPosition member
        final int mPositionHandle = GLES30.glGetAttribLocation(shaderProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES30.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES30.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES30.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        //
        // projection, view, scale, translate, rotate matrices
        //
        // projection
        final int projectionMatrixHandle = GLES30.glGetUniformLocation(shaderProgram, "uProjectionMatrix");
        GLES30.glUniformMatrix4fv(projectionMatrixHandle, 1, false, projectionMatrix, 0);

        // view
        final int viewMatrixHandle = GLES30.glGetUniformLocation(shaderProgram, "uViewMatrix");
        GLES30.glUniformMatrix4fv(viewMatrixHandle, 1, false, viewMatrix, 0);

        // model
        final int modelMatrixHandle = GLES30.glGetUniformLocation(shaderProgram, "uModelMatrix");
        GLES30.glUniformMatrix4fv(modelMatrixHandle, 1, false, modelMatrix, 0);

        // translation
        final int translationVectorHandle = GLES30.glGetUniformLocation(shaderProgram, "uTranslationVector");
        GLES30.glUniform3fv(translationVectorHandle, 1, translationVector, 0);

        // rotation
        final int rotationVectorHandle = GLES30.glGetUniformLocation(shaderProgram, "uRotationVector");
        GLES30.glUniform3fv(rotationVectorHandle, 1, rotationVector, 0);

        // scale
        final int scaleVectorHandle = GLES30.glGetUniformLocation(shaderProgram, "uScaleVector");
        GLES30.glUniform3fv(scaleVectorHandle, 1, scaleVector, 0);


        GLES30.glDrawElements(GLES30.GL_TRIANGLES, drawOrder.length, GLES30.GL_UNSIGNED_SHORT, drawListBuffer);

        // Disable vertex array
        GLES30.glDisableVertexAttribArray(mPositionHandle);
    }

    public void translate(final float x, final float y, final float z) {
        translationVector = new float[]{x, y, z};
    }

    public void rotate(final float xDeg, final float yDeg, final float zDeg) {
        rotationVector = new float[]{xDeg, yDeg, zDeg};
    }

    public void scale(final float x, final float y, final float z) {
        scaleVector = new float[]{x, y, z};
    }
}