package com.example.app;

import android.graphics.Bitmap;
import android.opengl.GLES30;

import com.example.app.util.ShaderUtils;
import com.example.app.util.TextureUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by josaxa on 2014-02-05.
 */
public class Model {
    static final int COORDS_PER_VERTEX = 3/*x, y, z*/;
    static final int VERTEX_STRIDE = COORDS_PER_VERTEX * 4 /*bytes per vertex*/;

    private final FloatBuffer vertexBuffer;
    private final FloatBuffer textureCoordinates;
    private final FloatBuffer vertexNormals;
    private final int shaderProgram;
    private final int textureDataHandle;
    private final int vertexCount;

    private final float[] rotationVector = new float[3];
    private final float[] translationVector = new float[3];
    private final float[] scaleVector = new float[3];


    public Model(final ModelDefinition modelDefinition, final Bitmap texture, final String vertexShaderCode, final String fragmentShaderCode) {
        final float[] vertices = modelDefinition.getVertices();
        vertexCount = vertices.length / COORDS_PER_VERTEX;
        vertexBuffer = allocateFloatBuffer(vertices);
        vertexNormals = allocateFloatBuffer(modelDefinition.getNormals());

        textureDataHandle = TextureUtils.createTexture(texture);
        textureCoordinates = allocateFloatBuffer(modelDefinition.getUvs());

        shaderProgram = ShaderUtils.compileShader(vertexShaderCode, fragmentShaderCode);
    }

    private static FloatBuffer allocateFloatBuffer(final float[] arr) {
        final FloatBuffer buffer = ByteBuffer
                .allocateDirect(arr.length * 4/*bytes per float*/)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        buffer.put(arr);
        buffer.position(0);
        return buffer;
    }

    public void update(final int time) {
        final float rotX = (float)((time / 5) % 360);
        final float rotY = (float)((time / 15) % 360);
        final float rotZ = 0.0f;//(float)((time / 35) % 360);
        rotationVector[0] = rotX;
        rotationVector[1] = rotY;
        rotationVector[2] = rotZ;

        translationVector[0] = 0f;
        translationVector[1] = 0f;
        translationVector[2] = 0f;

        scaleVector[0] = 2f;
        scaleVector[1] = 2f;
        scaleVector[2] = 2f;
    }

    public void draw(final float[] viewMatrix, final float[] projectionMatrix) {
        GLES30.glUseProgram(shaderProgram);

        bindMat4("uProjectionMatrix", projectionMatrix);
        bindMat4("uViewMatrix", viewMatrix);
        bindVec3("uTranslationVector", translationVector);
        bindVec3("uScaleVector", scaleVector);
        bindVec3("uRotationVector", rotationVector);

        bindTexture("uTexture", textureDataHandle);
        final int uvArrayHandle = bindVertexArray("aUv", textureCoordinates, 2 /*s, t*/, 0);
        final int normalArrayHandle = bindVertexArray("aNormals", vertexNormals, 3 /*i, j, k*/, 0);
        final int vertexArrayHandle = bindVertexArray("aPosition", vertexBuffer, COORDS_PER_VERTEX, VERTEX_STRIDE);

        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, vertexCount);

        GLES30.glDisableVertexAttribArray(vertexArrayHandle);
        GLES30.glDisableVertexAttribArray(normalArrayHandle);
        GLES30.glDisableVertexAttribArray(uvArrayHandle);
    }

    private void bindMat4(final String name, final float[] matrix) {
        final int matrixHandle = GLES30.glGetUniformLocation(shaderProgram, name);
        GLES30.glUniformMatrix4fv(matrixHandle, 1, false, matrix, 0);
    }

    private void bindVec3(final String name, final float[] vector) {
        final int handle = GLES30.glGetUniformLocation(shaderProgram, name);
        GLES30.glUniform3fv(handle, 1, vector, 0);
    }

    private void bindTexture(final String name, final int textureHandle) {
        final int mTextureUniformHandle = GLES30.glGetUniformLocation(shaderProgram, name);

        // Set the active texture unit to texture unit 0.
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);

        // Bind the texture to this unit.
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureHandle);

        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES30.glUniform1i(mTextureUniformHandle, 0);
    }

    private int bindVertexArray(final String name, final FloatBuffer buffer, final int size, final int stride) {
        final int handle = GLES30.glGetAttribLocation(shaderProgram, name);
        GLES30.glEnableVertexAttribArray(handle);

        buffer.position(0);
        GLES30.glVertexAttribPointer(handle, size, GLES30.GL_FLOAT, false, stride, buffer);
        return handle;
    }

    private void bindUvs(final String name, final FloatBuffer uvs) {
        final int handle = GLES30.glGetAttribLocation(shaderProgram, name);

        uvs.position(0);
        GLES30.glVertexAttribPointer(handle, 2 /* s, t */, GLES30.GL_FLOAT, false, 0, uvs);

        GLES30.glEnableVertexAttribArray(handle);
    }

    private void bindNormals(final String name, final FloatBuffer normals) {
        final int handle = GLES30.glGetUniformLocation(shaderProgram, name);
        vertexNormals.position(0);
        GLES30.glVertexAttribPointer(handle, 3 /*i, j, k*/, GLES30.GL_FLOAT, false, 0, normals);
    }

}

