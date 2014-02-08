#version 300 es

precision mediump float ;

uniform sampler2D uTexture ;

in vec2 vTexCoord ;
in vec4 vColor ;

out vec4 color ;

void main ( ) {
    vec2 uv = vec2 ( vTexCoord.s , 1.0f - vTexCoord.t ) ;
    color = texture ( uTexture , uv ) * vColor ;
}
