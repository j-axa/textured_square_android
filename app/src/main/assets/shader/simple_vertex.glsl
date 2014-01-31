#version 300 es

uniform mat4 uProjectionMatrix;
uniform mat4 uViewMatrix;
uniform mat4 uModelMatrix;

uniform vec3 uTranslationVector;
uniform vec3 uRotationVector;
uniform vec3 uScaleVector;


in vec4 vPosition;
in vec2 aTexCoord;


out vec4 vColor;
out vec2 vTexCoord;

mat4 rotate() {
  vec3 rads = radians(uRotationVector);
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

mat4 translate() {
    return mat4(1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                uTranslationVector.x, uTranslationVector.y, uTranslationVector.z, 1.0f);
}

mat4 scale() {
    return mat4(uScaleVector.x, 0.0f, 0.0f, 0.0f,
                0.0f, uScaleVector.y, 0.0f, 0.0f,
                0.0f, 0.0f, uScaleVector.z, 0.0f, 
                0.0f, 0.0f, 0.0f, 1.0f);
}


void main() {
  gl_Position = uProjectionMatrix * uViewMatrix * uModelMatrix * translate() * rotate() * scale() * vPosition;
  vColor = vec4(0.3f, 0.5f, 0.7f, 1.0f);
  vTexCoord = aTexCoord;
}

