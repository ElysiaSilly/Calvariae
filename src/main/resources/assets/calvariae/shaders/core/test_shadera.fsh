#version 150

#moj_import <minecraft:matrix.glsl>

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;

uniform vec2 ScreenSize;
uniform vec4 FogColor;
uniform float GameTime;
uniform int EndPortalLayers;
uniform sampler2D MainDepthSampler;
uniform float nearPlaneDistance;
uniform float farPlaneDistance;
uniform sampler2D MainSampler;

in vec2 texCoord;
in vec3 Position;
in vec4 texProj0;
out vec4 fragColor;

vec3 colorA = vec3(0.0,0.0,1.0);
vec3 colorB = vec3(1.0,0.0,0.0);

/*
float linearize_depth(float d) {
    float z_n = 2.0 * d - 1.0;
    float z_eye = 2.0 * nearPlaneDistance * farPlaneDistance / (farPlaneDistance + nearPlaneDistance - z_n * (farPlaneDistance - nearPlaneDistance));
    return (z_eye - nearPlaneDistance) / (farPlaneDistance - nearPlaneDistance);
}
*/

void main() {

    //float posX = Position.x * 0.06;
    //float posY = Position.y * 0.06;
    //float posZ = Position.z * 0.06;

    //vec3 color = vec3((Position.x / 2), 0.0, 0.0);//posX, posY, posZ);


    //float time = fract(GameTime * 1200);

    //vec2 st = gl_FragCoord.xy/ScreenSize.xy;

    //float left = step(0.1, st.x);
    //float bottom = step(0.1, st.y);

    //float color = (left * bottom);

    //vec3 pct = vec3(st.x);

    //vec3 colorC = mix(colorA, colorB, abs(sin(GameTime / 0.001)));
    //vec3 colorD = mix(colorB, colorA, abs(sin(GameTime / 0.001)));

    //color = mix(colorC, colorD, pct);

    //color = mix(color, vec3(1.0,0.0,0.0), plot(st,pct.r));
    //color = mix(color, vec3(0.0,1.0,0.0), plot(st,pct.g));
    //color = mix(color, vec3(0.0,0.0,1.0), plot(st,pct.b));

    //fragColor = vec4(color, 1.0);

    //vec2 screenUV = gl_FragCoord.xy / ScreenSize;

    //float sceneDepth = linearize_depth(texture(MainDepthSampler, screenUV).r);
    //float pixelDepth = linearize_depth(gl_FragCoord.z);
    //float difference = abs(pixelDepth - sceneDepth);

    float color = texture(MainDepthSampler, texCoord).r;

    fragColor = vec4(color, color, color, 1.0 ); //vec4(pixelDepth, pixelDepth, pixelDepth, 1.0);

    //fragColor = vec4(abs(sin(GameTime / 0.001)),0.0,1.0,1.0);
}
