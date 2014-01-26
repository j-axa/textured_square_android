#version 300 es

uniform mat4 uProjectionMatrix;
uniform mat4 uViewMatrix;


in vec4 vPosition;
in vec2 aTexCoord;


out vec4 vColor;
out vec2 vTexCoord;

void main() {
  gl_Position = uProjectionMatrix * uViewMatrix * vPosition;
  vColor = vec4(0.3f, 0.5f, 0.7f, 1.0f);
  vTexCoord = aTexCoord;
}
