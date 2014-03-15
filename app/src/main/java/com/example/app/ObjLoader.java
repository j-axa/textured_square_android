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
        final ArrayList<String[]> vns = new ArrayList<>();
        final ArrayList<String[]> fs = new ArrayList<>();
        for (final String line : obj.split("[\r\n]+")) {
            final String[] parts = line.split(" ");

            // geometric vertices
            if("v".equals(parts[0])) {
                vs.add(parts);
            }

            // texture vertices
            else if ("vt".equals(parts[0])) {
                vts.add(parts);
            }

            // vertex normals
            else if ("vn".equals(parts[0])) {
                vns.add(parts);
            }

            // face
            else if ("f".equals(parts[0])) {
                fs.add(parts);
            }

            else {
                Log.d("ObjLoader", String.format("Not parsed: '%s'", line));
            }
        }
        final int faceCount = fs.size() * 3 /*faces per row in .obj file*/;
        final float[] vertices = new float[faceCount * 3 /*components per vertex*/];
        final float[] normals = new float[faceCount * 3 /*components per normal*/];
        final float[] uvs = new float[faceCount * 2 /*components per uv*/];

        int ivs = 0;
        int ivns = 0;
        int ivts = 0;
        for(String[] f : fs) {
            for(int fi = 1; fi < f.length; ++fi){
                final String[] face = f[fi].split("/");
                final int fv = Integer.parseInt(face[0]);
                final int fvt = Integer.parseInt(face[1]);
                final int fvn = Integer.parseInt(face[2]);

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

                final String[] vn = vns.get(fvn - 1);
                final float vni = Float.parseFloat(vn[1]);
                final float vnj = Float.parseFloat(vn[2]);
                final float vnk = Float.parseFloat(vn[3]);
                normals[ivns++] = vni;
                normals[ivns++] = vnj;
                normals[ivns++] = vnk;
            }
        }
        ModelDefinition result = new ModelDefinition() {
            float[] _vertices;
            float[] _uvs;
            float[] _normals;
            {
                _vertices = vertices;
                _uvs = uvs;
                _normals = normals;
            }

            @Override
            float[] getVertices() {
                return _vertices;
            }

            @Override
            float[] getNormals() {
                return _normals;
            }

            @Override
            float[] getUvs() {
                return _uvs;
            }
        };
        return result;
    }
}
