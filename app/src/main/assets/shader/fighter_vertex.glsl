#version 300 es

uniform mat4 uProjectionMatrix;
uniform mat4 uViewMatrix;

uniform vec3 uTranslationVector;
uniform vec3 uRotationVector;
uniform vec3 uScaleVector;

in vec3 vPosition;
in vec2 aTexCoord;

out vec4 vColor;
out vec2 vTexCoord;

mat4 rotate(vec3 rotation) {
  vec3 rads = radians(rotation);
  vec3 s = sin(rads);
  vec3 c = cos(rads);
  mat4 rx = mat4(1.0f, 0.0f, 0.0f, 0.0f, 
                 0.0f, c.x, -s.x, 0.0f, 
                 0.0f, s.x, c.x, 0.0f, 
                 0.0f, 0.0f, 0.0f, 1.0f);
  mat4 ry = mat4(c.y, 0.0f, s.y, 0.0f, 
                 0.0f, 1.0f, 0.0f, 0.0f,
                 -s.y, 0.0f, c.y, 0.0f,
                 0.0f, 0.0f, 0.0f, 1.0f);
  mat4 rz = mat4(c.z, s.z, 0.0f, 0.0f,
                 -s.z, c.z, 0.0f, 0.0f, 
                 0.0f, 0.0f, 1.0f, 0.0f, 
                 0.0f, 0.0f, 0.0f, 1.0f);
  return rz * ry * rx;
}

mat4 translate(vec3 t) {
    return mat4(1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                t.x, t.y, t.z, 1.0f);
}

mat4 scale(vec3 s) {
    return mat4(s.x, 0.0f, 0.0f, 0.0f,
                0.0f, s.y, 0.0f, 0.0f,
                0.0f, 0.0f, s.z, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f);
}

void main() {
  mat4 model = translate(uTranslationVector) * rotate(uRotationVector) * scale(uScaleVector);
  gl_Position = uProjectionMatrix * uViewMatrix * model * vec4(vPosition, 1.0f);

  vColor = vec4(1.0f, 1.0f, 1.0f, 1.0f); 
  vTexCoord = aTexCoord;
}

