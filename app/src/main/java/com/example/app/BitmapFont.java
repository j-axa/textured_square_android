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
 * Created by josaxa on 2014-01-29.
 */
public class BitmapFont {

    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;
    private final FloatBuffer textureCoordinates;

    private final int shaderProgram;
    private final int textureDataHandle;

    /*
    * Set up a quad whose vertices (clockwise) are (-1, -1), (-1, 1), (1, 1), (1, -1).
      Assign texture coordinates to the vertices with (0, 0), (0, 1), (1, 1), (1, 0), respectively.
    * */

    static float[] baseTextureCoords = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f };
    static float squareCoords[] = {
            -1f, -1f, 0.0f,
            -1f,  1f, 0.0f,
             1f,  1f, 0.0f,
             1f, -1f, 0.0f };
    static short drawOrder[] = {0, 1, 2, 0, 2, 3};

    public static final int COORDS_PER_VERTEX = 3;
    public static final int VERTEX_STRIDE = COORDS_PER_VERTEX * 4; // 4 bytes per vertex





    private final int[] _sheetDim = new int[2];

    private final int[] _charDim = new int[2];

    private final String _fontMap;

    public BitmapFont(final int sheetWidth, final int sheetHeight, final int charWidth, final int charHeight,
                      final Bitmap texture, final String fontMap, final String vertexShaderCode, final String fragmentShaderCode) {
        _sheetDim[0] = sheetWidth;
        _sheetDim[1] = sheetHeight;
        _charDim[0] = charWidth;
        _charDim[1] = charHeight;
        _fontMap = fontMap;

        // todilo: load texture, create buffers for billboard vertices
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
        textureCoordinates = ByteBuffer.allocateDirect(baseTextureCoords.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        textureCoordinates.put(baseTextureCoords);
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
    }

    public int getSheetRows() {
        return _sheetDim[1] / _charDim[1];
    }

    public int getSheetCols() {
        return _sheetDim[0] / _charDim[0];
    }

    //              |
    // OpenGL stuff |
    //              V

    public float[] getCharUV(char c) {
        final int cols = getSheetCols();
        final int rows = getSheetRows();
        final int i = _fontMap.indexOf(c);
        final float u = (i % cols) / (float)cols;
        final float v = (i / rows) / (float)rows;
        return new float[] { u, v };
    }

    public int getShader() {
        return shaderProgram;
    }

    public int getTexture() {
        return textureDataHandle;
    }

    public FloatBuffer getVertexBuffer() {
        return vertexBuffer;
    }

    public ShortBuffer getDrawListBuffer() {
        return drawListBuffer;
    }

    public FloatBuffer getTextureCoordinateBuffer() {
        return textureCoordinates;
    }

    public int getDrawListBufferLength() {
        return drawOrder.length;
    }
}

