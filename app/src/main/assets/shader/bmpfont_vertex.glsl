#version 300 es
// precision highp float; //no need
//uniform vec3 uTranslationVector;
//uniform vec3 uScaleVector;

#define _1_16TH (1.0f / 16.0f)
#define _1_9TH (1.0f / 9.0f)

#define CHAR_SCALE (0.25f)
#define CHAR_WIDTH (_1_16TH * CHAR_SCALE)
#define CHAR_HEIGHT (_1_9TH * CHAR_SCALE)
#define CHAR_SPACE (CHAR_WIDTH * 2.0f)

uniform vec2 uInstanceUV[80]; // max 50 chars

uniform float uSheetRows;
uniform float uSheetCols;

in vec4 vPosition;
in vec2 aTexCoord;

out vec2 vTexCoord;
out vec4 vColor;

mat4 translate() {
    return mat4(1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                -1.0f + CHAR_WIDTH + float(gl_InstanceID) * CHAR_SPACE, 1.0f - CHAR_SPACE, 0.0f, 1.0f);
}
mat4 scale() {
    return mat4(CHAR_WIDTH, 0.0f, 0.0f, 0.0f,
                0.0f, CHAR_HEIGHT, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f, 
                0.0f, 0.0f, 0.0f, 1.0f);
}


void main() {
  vec2 uv = uInstanceUV[gl_InstanceID];
  
  vTexCoord = vec2(aTexCoord.x / uSheetCols + uv.s, 
                   1.0f - aTexCoord.y / uSheetRows + 1.0f - uv.t);
  
  vColor = vec4(1.0f, 1.0f, 1.0f, 1.0f);

  gl_Position = translate() * scale() * vPosition;
}
