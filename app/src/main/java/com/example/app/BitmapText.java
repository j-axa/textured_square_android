package com.example.app;

import android.annotation.TargetApi;
import android.opengl.GLES30;
import android.os.Build;

import java.nio.FloatBuffer;

public class BitmapText{

    private final BitmapFont font;
    private final float[] uvs = new float[160];
    private final int length;

    public BitmapText(final BitmapFont font, final String s) {
        this.font = font;

        length = s.length();
        if(length > 80) {
            throw new RuntimeException("max 80 chars allowed");
        }

        for(int i = 0, j = 0; i < length; ++i, j = i * 2) {
            char c = s.charAt(i);
            float[] uv = font.getCharUV(c);
            uvs[j    ] = uv[0];
            uvs[j + 1] = uv[1];
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void draw() {
        int shaderProgram = font.getShader();
        int textureDataHandle = font.getTexture();

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

        FloatBuffer textureCoordinates = font.getTextureCoordinateBuffer();

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
        GLES30.glVertexAttribPointer(mPositionHandle, BitmapFont.COORDS_PER_VERTEX,
                GLES30.GL_FLOAT, false,
                BitmapFont.VERTEX_STRIDE, font.getVertexBuffer());

        // offsets
        final int instanceUVHandle = GLES30.glGetUniformLocation(shaderProgram, "uInstanceUV");
        GLES30.glUniform2fv(instanceUVHandle, 50, uvs, 0);

        final int sheetRowsHandle = GLES30.glGetUniformLocation(shaderProgram, "uSheetRows");
        GLES30.glUniform1f(sheetRowsHandle, font.getSheetRows());

        final int sheetColsHandle = GLES30.glGetUniformLocation(shaderProgram, "uSheetCols");
        GLES30.glUniform1f(sheetColsHandle, font.getSheetCols());

        GLES30.glDrawElementsInstanced(GLES30.GL_TRIANGLES, font.getDrawListBufferLength(),
                                       GLES30.GL_UNSIGNED_SHORT, font.getDrawListBuffer(), length);

        // Disable vertex array
        GLES30.glDisableVertexAttribArray(mPositionHandle);
    }
}
