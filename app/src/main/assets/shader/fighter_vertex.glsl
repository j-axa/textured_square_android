#version 300 es

uniform sampler2D uTexture ;

uniform mat4 uProjectionMatrix;
uniform mat4 uViewMatrix;

uniform vec3 uTranslationVector;
uniform vec3 uRotationVector;
uniform vec3 uScaleVector;

in vec3 aPosition;
in vec2 aUv;
in vec3 aNormal;

out vec2 vUv;
out vec3 vNormal;
out vec3 vLightDirection;
//out vec3 vColor;

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
                0.0f , s.y , 0.0f , 0.0f,
                0.0f , 0.0f , s.z , 0.0f,
                0.0f , 0.0f , 0.0f , 1.0f ) ;
}

void main() {
  mat4 model = translate ( uTranslationVector ) * rotate ( uRotationVector );// * scale ( uScaleVector ) ;
  mat4 mvp = uProjectionMatrix * uViewMatrix * model;
  
  /*
// Output position of the vertex, in clip space : MVP * position
gl_Position =  MVP * vec4(vertexPosition_modelspace,1);
  */
  gl_Position = mvp * vec4(aPosition, 1.0f);
   /*
// Position of the vertex, in worldspace : M * position
Position_worldspace = (M * vec4(vertexPosition_modelspace,1)).xyz;
 */
  vec3 position_world = (model * vec4(aPosition, 1.0f)).xyz;
  /*
// Vector that goes from the vertex to the camera, in camera space.
// In camera space, the camera is at the origin (0,0,0).
vec3 vertexPosition_cameraspace = ( V * M * vec4(vertexPosition_modelspace,1)).xyz;
EyeDirection_cameraspace = vec3(0,0,0) - vertexPosition_cameraspace;
  */
  vec3 position_camera = (uViewMatrix * model * vec4(aPosition, 1.0f)).xyz;
  vec3 eye_direction_camera = vec3(0.0f, 0.0f, 0.0f) - position_camera;
  /*
// Vector that goes from the vertex to the light, in camera space. M is ommited because it's identity.
vec3 LightPosition_cameraspace = ( V * vec4(LightPosition_worldspace,1)).xyz;
LightDirection_cameraspace = LightPosition_cameraspace + EyeDirection_cameraspace;
  */
  vec3 sun = vec3(5.0f, 5.0f, -5.0f);
  vec3 light_camera = (uViewMatrix * vec4(sun, 1.0f)).xyz;
  vLightDirection = light_camera + eye_direction_camera;
  /*
// Normal of the the vertex, in camera space
// Only correct if ModelMatrix does not scale the model ! Use its inverse transpose if not.
Normal_cameraspace = ( V * M * vec4(vertexNormal_modelspace,0)).xyz; 
  */
  vNormal = (uViewMatrix * model * vec4(aNormal, 0.0f)).xyz;

  vUv = aUv;

  /*
  You do need to transform the normals, by the upper 3 rows/cols of the model-view-projection matrix. 
  (If you are performing any scaling though, you need to use the inverse transpose of this matrix. See this article).
  */
/*  mat3 normalMatrix = mat3(mvp);
  normalMatrix = inverse(normalMatrix);
  normalMatrix = transpose(normalMatrix);
  vNormal = -normalize(aNormal * normalMatrix);

  vUv = aUv;
 // vColor = texture ( uTexture , vec2(aUv.s, 1.0f - aUv.t)).rgb  ;
  vPosition = vec3(mvp * vec4 ( aPosition , 1.0f ) );
  gl_Position = mvp * vec4 ( aPosition , 1.0f );
*/}

