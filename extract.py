def extract_mesh(class_name, mesh, path):
    def rdc(fun, lst):
        it = iter(lst)
        acc = next(it)
        for x in it:
            acc = fun(acc, x)
        return acc
    \
    def java_arr(name, suffix, lst, break_each):
        def _join(a, b, i):
            i[0] += 1
            fmt = "%s,\n%s" if i[0] % break_each == 0 else "%s,%s"
            return fmt % (a, b)
        \
        cnt = [0]
        return "private static final %s[]={\n%s\n};" % (name, rdc(lambda a, b: _join(a, b, cnt), map(lambda x: "%s%s" % (x, suffix), lst)))
    \
    BEGIN_CLASS = "public final class %s extends Model {" % class_name
    END = "}"
    \
    faces = [face for face in mesh.data.polygons]
    \
    # go the long way around and render each vertice by the vertex loop index
    draw_order = [i for f in faces for i in f.loop_indices]
    \
    # fetch each triangle separately (am i?)
    vertices = [c for f in faces for i in f.vertices for c in mesh.data.vertices[i].co]
    \
    uvs = [ "{:.6f}".format(x) for uv in mesh.data.uv_layers[0].data for x in uv.uv.to_tuple() ]
    \
    f = open("%s\\%s.java" % (path, class_name), "w")
    \
    def p(s):
        print(s, file = f)
    \
    p(BEGIN_CLASS)
    \
    p(java_arr("short drawOrder", "", draw_order, 15))
    \
    p(java_arr("float vertices", "f", vertices, 3))
    \
    p(java_arr("float uvs", "f", uvs, 6))
    \
    def impl(n, t, v):
        return "@Override\n%s[] %s(){return %s.%s;}\n" % (t, n, class_name, v)
    \
    p(impl("getVertices", "float", "vertices"))
    p(impl("getUvs", "float", "uvs"))
    p(impl("getDrawOrder", "short", "drawOrder"))
    p("public %s(final Bitmap texture, final String vertexShaderCode, final String fragmentShaderCode) {\nsuper(texture, vertexShaderCode, fragmentShaderCode);\n}" % class_name)
    p(END)
    \
    f.close()
