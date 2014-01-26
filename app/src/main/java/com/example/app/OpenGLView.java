package com.example.app;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by josaxa on 2014-01-22.
 */
public final class OpenGLView extends GLSurfaceView {
    public OpenGLView(Context context) {
        super(context);

        // setEGLContextClientVersion(...) must be called before setRenderer(...)
        setEGLContextClientVersion(3);

        setRenderer(new OpenGLRenderer(context));
    }
}
