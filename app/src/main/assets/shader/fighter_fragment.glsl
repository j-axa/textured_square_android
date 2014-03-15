#version 300 es

precision mediump float ;

uniform sampler2D uTexture ;

in vec2 vUv ;
in vec3 vNormal ;
in vec3 vLightDirection ;
//in vec3 vColor;

out vec4 fragmentColor ;

vec4 lightColor = vec4(1.0f, 1.0f, 1.0f, 1.0f);
vec4 ambientColor = vec4(0.1f, 0.1f, 0.1f, 1.0f);

void main ( ) {
    vec2 uv = vec2 ( vUv.s , 1.0f - vUv.t ) ;
    vec4 materialColor = texture ( uTexture , uv ) ;
    //fragmentColor = vec4(vNormalEye, 1.0f);


/*
// Normal of the computed fragment, in camera space
 vec3 n = normalize( Normal_cameraspace );
 // Direction of the light (from the fragment to the light)
 vec3 l = normalize( LightDirection_cameraspace );
*/
    vec3 n = normalize(vNormal);
    vec3 l = normalize(vLightDirection);
/*
// Cosine of the angle between the normal and the light direction,
// clamped above 0
//  - light is at the vertical of the triangle -> 1
//  - light is perpendicular to the triangle -> 0
//  - light is behind the triangle -> 0
float cosTheta = clamp( dot( n,l ), 0,1 );
 
color = MaterialDiffuseColor * LightColor * cosTheta;

*/
    float cosTheta = clamp(dot(n, l), 0.0f, 1.0f);
    fragmentColor = ambientColor + materialColor * lightColor * cosTheta;


    /************************************************************************************************
    In your fragment shader, you need to calculate the distance from your light source to your fragment and normalize. 
    Find the dot product of the normal and the light vector and multiply this by the light color.
    ************************************************************************************************/
/*    vec3 light = normalize(sun - vPosition);
    light = max(dot(vNormal, light), 0.0) * vec3(1.0, 1.0, 1.0);
    fragmentColor = materialColor * vec4(light, 1.0);


    /************************************************************************************************
    Ambient lighting:

    Ambient lighting is really indirect diffuse lighting, 
    but it can also be thought of as a low-level light which pervades the entire scene. 
    If we think of it that way, then it becomes very easy to calculate:
    
        final color = material color * ambient light color
    ************************************************************************************************/

    //vec4 ambientColor = materialColor * vec4(0.1f, 0.1f, 0.1f, 1.0f);

    /************************************************************************************************
    Diffuse lighting – point light source:

    A surface which is facing the light straight-on should be illuminated at full strength, 
    while a surface which is slanted should get less illumination.
    Let’s call this the lambert factor, and it will have a range of between 0 and 1.

        light vector = light position - object position
        cosine = dot product(object normal, normalize(light vector))
        lambert factor = max(cosine, 0)
    ************************************************************************************************/
    
    //vec3 lightWorld = vec3(0.0f, 8.0f, 0.0f);
    //vec3 lightEye = vec3(vModelView * vec4(lightWorld, 1.0f));
    //vec3 light = lightEye - vPositionEye;
    //float cosine = dot(vNormalEye, normalize(light));
    //float lambert = max(cosine, 0.0f);
    
    /************************************************************************************************
    Real light attenuation from a point light source follows the inverse square law, which can also be stated as:

        luminosity = 1 / (distance * distance)

    Ambient + Diffuse lighting:

        final color = (material color * ambient light color) * (light color * lambert factor * luminosity)
    ************************************************************************************************/
    
    //fragmentColor = materialColor;//ambientColor * (lambert * vec4(1.0f, 1.0f, 1.0f, 1.0f));


/*
    // fixed point light properties
    vec3 light_position_world  = vec3 (0.0, 16.0, 0.0);
    vec3 Ls = vec3 (1.0, 1.0, 1.0); // white specular colour
    vec3 Ld = vec3 (0.7, 0.7, 0.7); // dull white diffuse light colour
    vec3 La = vec3 (0.2, 0.2, 0.2); // grey ambient colour
      
    // surface reflectance
    vec3 Ks = vec3 (0.8, 0.8, 0.8); // fully reflect specular light
    vec3 Kd = vec3 (0.5, 0.5, 0.5); // orange diffuse surface reflectance
    vec3 Ka = vec3 (0.5, 0.5, 0.5); // half reflect ambient light
    float specular_exponent = 100.0; // specular 'power'


    // ambient intensity
    vec3 Ia = La * Ka;

    // diffuse intensity
    // raise light position to eye space
    vec3 light_position_eye = vec3 (uViewMatrix * vec4 (light_position_world, 1.0));
    vec3 distance_to_light_eye = light_position_eye - vPositionEye;
    vec3 direction_to_light_eye = normalize (distance_to_light_eye);
    float dot_prod = dot (direction_to_light_eye, vNormalEye);
    dot_prod = max (dot_prod, 0.0);
    vec3 Id = Ld * Kd * dot_prod; // final diffuse intensity
    
    // specular intensity
    vec3 reflection_eye = reflect (-direction_to_light_eye, vNormalEye);
    vec3 surface_to_viewer_eye = normalize (-vPositionEye);
    float dot_prod_specular = dot (reflection_eye, surface_to_viewer_eye);
    dot_prod_specular = max (dot_prod_specular, 0.0);
    float specular_factor = pow (dot_prod_specular, specular_exponent);
    vec3 Is = Ls * Ks * specular_factor; // final specular intensity

    // final colour
    fragmentColor = materialColor * vec4 (Is + Id + Ia, 1.0);*/
    
}
