def extract_mesh(class_name, mesh, path):
    def rdc(fun, lst):
        it = iter(lst)
        acc = next(it)
        for x in it:
            acc = fun(acc, x)
        return acc
    \
    def java_arr(name, suffix, lst):
        def _join(a, b, i):
            i[0] += 1
            fmt = "%s,\n%s" if i[0] % 3 == 0 else "%s,%s"
            return fmt % (a, b)
        \
        cnt = [0]
        return "private static final %s[]={\n%s\n};" % (name, rdc(lambda a, b: _join(a, b, cnt), map(lambda x: "%r%s" % (x, suffix), lst)))
    \
    def get_data(i_draw, i):
        def normal(v):
            return v.normal.to_tuple()
        \
        def coord(v):
            return v.co.to_tuple()
        \
        vert = mesh.data.vertices[i_draw]
        uv = mesh.data.uv_layers.active.data[i].uv
        \
        return (i_draw, coord(vert), normal(vert), uv.to_tuple())
    \
    BEGIN_CLASS = "public final class %s extends Model {" % class_name
    END = "}"
    \
    data = [get_data(i_draw, i) for face in mesh.data.polygons for i_draw, i in zip(face.vertices, face.loop_indices)]
    data = list(zip(*data))
    \
    f = open("%s\\%s.java" % (path, class_name), "w")
    \
    def p(s):
        print(s, file = f)
    \
    p(BEGIN_CLASS)
    \
    p(java_arr("short drawOrder", "", data[0]))
    \
    for name, data in zip(["float vertices", "float normals", "float uvs"], data[1:]):
        p(java_arr(name, "f", rdc(lambda a, b: a + b, data)))
    \
    def impl(n, t, v):
        return "@Override\n%s[] %s(){return %s.%s;}\n" % (t, n, class_name, v)
    \
    p(impl("getVertices", "float", "vertices"))
    p(impl("getNormals", "float", "normals"))
    p(impl("getUvs", "float", "uvs"))
    p(impl("getDrawOrder", "short", "drawOrder"))
    p("public %s(final Bitmap texture, final String vertexShaderCode, final String fragmentShaderCode) {\nsuper(texture, vertexShaderCode, fragmentShaderCode);\n}" %s class_name)
    p(END)
    \
    f.close()
