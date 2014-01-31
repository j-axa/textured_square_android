#version 300 es

precision mediump float;

uniform sampler2D uTexture;

in vec2 vTexCoord;
in vec4 vColor;

out vec4 color;

void main() {
  vec4 c = texture(uTexture, vTexCoord);
  if (c.a < 1.0f) {
    discard;
  }
  color = c * vColor;
}
