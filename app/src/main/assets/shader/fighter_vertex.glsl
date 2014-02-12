#version 300 es

uniform mat4 uProjectionMatrix;
uniform mat4 uViewMatrix;
/*
uniform mat4 uModelMatrix;
/*
uniform vec3 uTranslationVector;
uniform vec3 uRotationVector;
uniform vec3 uScaleVector;
*/

uniform int uTime;

in vec3 vPosition;
in vec2 aTexCoord;


out vec4 vColor;
out vec2 vTexCoord;
/**/
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
//*/
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
  float x = float((uTime / 50000) % 359);
  vec3 t = vec3(0.0f);
  vec3 s = vec3(2.5f);
  vec3 r = vec3(x, x, 0.0f);
  gl_Position = uProjectionMatrix * uViewMatrix *translate(t)*  rotate(r) * scale(s) * vec4(vPosition, 1.0f); // /*inverse z*/ vec4 (1.0, 1.0, -1.0, 1.0)) ;
  
  vColor = vec4(1.0f, 1.0f, 1.0f, 1.0f); 
  vTexCoord = aTexCoord;
}

