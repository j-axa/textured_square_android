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
    static final int COORDS_PER_VERTEX = 3;
    static final int VERTEX_STRIDE = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;
    private final FloatBuffer textureCoordinates;
    private final int shaderProgram;
    private final int textureDataHandle;
    private final ModelDefinition definition;
    private final int vertexCount;

    public Model(final ModelDefinition modelDefinition, final Bitmap texture, final String vertexShaderCode, final String fragmentShaderCode) {
        definition = modelDefinition;
        final float[] vertices = definition.getVertices();
        vertexCount = vertices.length / COORDS_PER_VERTEX;
        vertexBuffer = allocateFloatBuffer(vertices);
        drawListBuffer = allocateShortBuffer(definition.getDrawOrder());

        textureDataHandle = TextureUtils.createTexture(texture);
        textureCoordinates = allocateFloatBuffer(definition.getUvs());

        shaderProgram = compileShader(vertexShaderCode, fragmentShaderCode);
    }

    private static ShortBuffer allocateShortBuffer(final short[] arr) {
        final ShortBuffer buffer = ByteBuffer
                .allocateDirect(arr.length * 2/*bytes per short*/)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer();
        buffer.put(arr);
        buffer.position(0);
        return buffer;
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

    private static int compileShader(String vertexShaderCode, String fragmentShaderCode) {
        final int vertexShader = ShaderUtils.createShader(GLES30.GL_VERTEX_SHADER, vertexShaderCode);
        final int fragmentShader = ShaderUtils.createShader(GLES30.GL_FRAGMENT_SHADER, fragmentShaderCode);
        final int shader = GLES30.glCreateProgram();
        GLES30.glAttachShader(shader, vertexShader);
        GLES30.glAttachShader(shader, fragmentShader);
        GLES30.glLinkProgram(shader);
        return shader;
    }

    public void draw(final float[] viewMatrix, final float[] projectionMatrix, final int time) {
        GLES30.glUseProgram(shaderProgram);

        bindTexture();

        bindUvs();

        final int mPositionHandle = bindPosition();

        bindMatrix4fv("uProjectionMatrix", projectionMatrix);
        bindMatrix4fv("uViewMatrix", viewMatrix);

        bindInt("uTime", time);


        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, vertexCount);
        //GLES30.glDrawElements(GLES30.GL_TRIANGLES, vertexCount, GLES30.GL_UNSIGNED_SHORT, drawListBuffer);

        // Disable vertex array
        GLES30.glDisableVertexAttribArray(mPositionHandle);
    }

    private void bindInt(final String name, final int i) {
        final int handle = GLES30.glGetUniformLocation(shaderProgram, name);
        GLES30.glUniform1i(handle, i);
    }

    private void bindMatrix4fv(final String name, final float[] matrix) {
        final int matrixHandle = GLES30.glGetUniformLocation(shaderProgram, name);
        GLES30.glUniformMatrix4fv(matrixHandle, 1, false, matrix, 0);
    }

    private void bindUvs() {
        final int mTextureCoordinateHandle = GLES30.glGetAttribLocation(shaderProgram, "aTexCoord");

        textureCoordinates.position(0);
        GLES30.glVertexAttribPointer(mTextureCoordinateHandle,
                2 /** Size of the texture coordinate data in elements. */,
                GLES30.GL_FLOAT, false,
                0, textureCoordinates);

        GLES30.glEnableVertexAttribArray(mTextureCoordinateHandle);
    }

    private void bindTexture() {
        final int mTextureUniformHandle = GLES30.glGetUniformLocation(shaderProgram, "uTexture");

        // Set the active texture unit to texture unit 0.
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);

        // Bind the texture to this unit.
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureDataHandle);

        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES30.glUniform1i(mTextureUniformHandle, 0);
    }

    private int bindPosition() {
        final int mPositionHandle = GLES30.glGetAttribLocation(shaderProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES30.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES30.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES30.GL_FLOAT, false,
                VERTEX_STRIDE, vertexBuffer);
        return mPositionHandle;
    }
}

