#version 300 es

uniform mat4 uProjectionMatrix;
uniform mat4 uViewMatrix;
uniform mat4 uModelMatrix;

uniform vec3 uTranslationVector;
uniform vec3 uRotationVector;
uniform vec3 uScaleVector;
uniform vec3 uWorldPosition;

in vec3 vPosition;
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
/*
In camera space, the camera’s up vector is (0,1,0). To get it in world space, just multiply this by the matrix that goes from Camera Space to World Space, which is, of course, the inverse of the View matrix.

An easier way to express the same math is :

CameraRight_worldspace = {ViewMatrix[0][0], ViewMatrix[1][0], ViewMatrix[2][0]}
CameraUp_worldspace = {ViewMatrix[0][1], ViewMatrix[1][1], ViewMatrix[2][1]}
 

Once we have this, it’s very easy to compute the final vertex’ position :

?
1
2
3
4
vec3 vertexPosition_worldspace = 
    particleCenter_wordspace
    + CameraRight_worldspace * squareVertices.x * BillboardSize.x
    + CameraUp_worldspace * squareVertices.y * BillboardSize.y;

*/

void main() {
  //vec3 camera_up = vec3(uViewMatrix[0][0], uViewMatrix[1][0], uViewMatrix[2][0]);
  //vec3 camera_right = vec3(uViewMatrix[1][0], uViewMatrix[1][1], uViewMatrix[2][1]);
  vec3 look = normalize(vec3(0.0f, 0.0f, 0.0f) - uWorldPosition);
  vec3 right = cross(/*camera up, y*/vec3(0.0f, 1.0f, 0.0f), look);
  vec3 up = cross(look, right);
  mat4 billboard = mat4(right[0], up[0], look[0], uWorldPosition.x,
                        right[1], up[1], look[1], uWorldPosition.y,
                        right[2], up[2], look[2], uWorldPosition.z,
                        0.0f, 0.0f, 0.0f, 1.0f);

  //vec4 x = rotate() * translate() * vec4(0.0f, 0.0f, 0.0f, 1.0f);

  gl_Position = uProjectionMatrix * billboard * vec4(vPosition, 1.0f);
  vColor = vec4(0.8f, 0.3f, 0.5f, 1.0f);
  vTexCoord = aTexCoord;
}

