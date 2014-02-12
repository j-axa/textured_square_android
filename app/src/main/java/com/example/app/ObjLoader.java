package com.example.app;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by josaxa on 2014-02-10.
 */
public class ObjLoader {
    public static ModelDefinition parseObj(final String obj) {
        final ArrayList<String[]> vs = new ArrayList<>();
        final ArrayList<String[]> vts = new ArrayList<>();
        final ArrayList<String[]> fs = new ArrayList<>();
        for(final String line : obj.split("[\r\n]+")) {
            final String[] parts = line.split(" ");

            // geometric vertices
            if("v".equals(parts[0])) {
                vs.add(parts);
            }

            // texture vertices
            else if ("vt".equals(parts[0])) {
                vts.add(parts);
            }

            // face
            else if ("f".equals(parts[0])) {
                fs.add(parts);
            }

            else {
                Log.d("ObjLoader", String.format("Not parsed: '%s'", line));
            }
        }
        final short[] drawOrder = new short[fs.size() * 9];
        final float[] vertices = new float[fs.size() * 9];
        final float[] uvs = new float[fs.size() * 6];

        for (short i = 0; i < drawOrder.length; ++i) {
            drawOrder[i] = i;
        }


        int ivs = 0;
/*
        for(final String[] v : vs) {

            final float vx = Float.parseFloat(v[1]);
            final float vy = Float.parseFloat(v[2]);
            final float vz = Float.parseFloat(v[3]);
            vertices[ivs++] = vx;
            vertices[ivs++] = vy;
            vertices[ivs++] = vz;

        }
        int idraw = 0;
*/
        int ivts = 0;
        for(String[] f : fs) {
            for(int fi = 1; fi < f.length; ++fi){
                final String[] face = f[fi].split("/");
                final int fv = Integer.parseInt(face[0]);
                final int fvt = Integer.parseInt(face[1]);

                final String[] v = vs.get(fv - 1);
                final float vx = Float.parseFloat(v[1]);
                final float vy = Float.parseFloat(v[2]);
                final float vz = Float.parseFloat(v[3]);
                vertices[ivs++] = vx;
                vertices[ivs++] = vy;
                vertices[ivs++] = vz;

                final String[] vt = vts.get(fvt - 1);
                final float vtu = Float.parseFloat(vt[1]);
                final float vtv = Float.parseFloat(vt[2]);
                uvs[ivts++] = vtu;
                uvs[ivts++] = vtv;
            }
        }
        ModelDefinition result = new ModelDefinition() {
            short[] _drawOrder;
            float[] _vertices;
            float[] _uvs;
            {
                _drawOrder = drawOrder;
                _vertices = vertices;
                _uvs = uvs;
            }
            @Override
            short[] getDrawOrder() {
                return _drawOrder;
            }

            @Override
            float[] getVertices() {
                return _vertices;
            }

            @Override
            float[] getNormals() {
               throw new UnsupportedOperationException();
            }

            @Override
            float[] getUvs() {
                return _uvs;
            }
        };
        return result;
    }
}
