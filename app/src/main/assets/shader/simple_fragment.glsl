#version 300 es

precision mediump float;

uniform sampler2D uTexture;

in vec2 vTexCoord;
in vec4 vColor;

out vec4 color;

void main() {
  color = texture(uTexture, vTexCoord) * vColor;
}
