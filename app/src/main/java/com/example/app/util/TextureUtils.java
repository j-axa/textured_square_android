package com.example.app.util;

import android.annotation.TargetApi;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.os.Build;

import com.example.app.OpenGLRenderer;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by josaxa on 2014-01-24.
 */
public final class TextureUtils {
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static final int createTexture(final Bitmap bitmap)
    {
        final int[] textureHandle = new int[1];
        GLES30.glGenTextures(1, textureHandle, 0);
        if (textureHandle[0] != 0) {
            GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureHandle[0]);

            // Set filtering
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_NEAREST);
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_NEAREST);

            // Load the bitmap into the bound texture.
            GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, bitmap, 0);
        }
        if (textureHandle[0] == 0) {
            throw new RuntimeException("Error loading texture.");
        }
        return textureHandle[0];
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static final Bitmap loadTextureData(final AssetManager assets, final String fileName) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        try (final InputStream s = assets.open(fileName)) {
            final Bitmap bmp = BitmapFactory.decodeStream(s);
            return bmp;
        }
        catch(IOException e) {
            throw new RuntimeException("Error loading texture.", e);
        }
    }
}
