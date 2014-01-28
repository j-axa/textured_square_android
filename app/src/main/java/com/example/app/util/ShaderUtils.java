package com.example.app.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetManager;
import android.opengl.GLES30;
import android.os.Build;
import android.util.Log;

import com.example.app.OpenGLRenderer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

/**
 * Created by josaxa on 2014-01-23.
 */
public final class ShaderUtils {
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static final String loadShader(final AssetManager assets, final String fileName) {
        try (final InputStream s = assets.open(fileName)) {
            final int length = s.available();
            final byte[] buffer = new byte[length];
            s.read(buffer);
            return new String(buffer);
        }
        catch(IOException e) {
            return null;
        }
    }

    public static int createShader(final int gl_shader_type, final String shaderCode) {
        final int shader = GLES30.glCreateShader(gl_shader_type);

        // add the source code to the shader and compile it
        GLES30.glShaderSource(shader, shaderCode);
        //OpenGLRenderer.checkGlError("glShaderSource");

        GLES30.glCompileShader(shader);
        //OpenGLRenderer.checkGlError("glCompileShader");
        //Log.d("ShaderUtils.createShader", "glGetShaderInfoLog: " +GLES30.glGetShaderInfoLog(shader) );

        return shader;
    }
}
