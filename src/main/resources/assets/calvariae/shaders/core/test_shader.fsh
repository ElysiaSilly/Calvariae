#version 150

#moj_import <minecraft:matrix.glsl>

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;
uniform sampler2D DiffuseSampler;
uniform sampler2D MainDepthSampler;
uniform sampler2D MainSampler;
uniform sampler2D InSampler;
uniform sampler2D DepthBuffer;

uniform vec4 ColorModulator;
uniform vec2 ScreenSize;
uniform vec3 Colour;

uniform float GameTime;

in vec2 texCoord0;
in vec4 vertexColor;
in vec4 texProj0;

out vec4 fragColor;

void main() {

    //vec4 color = texture(Sampler1, texCoord0) * vertexColor;
    //if (color.a < 0.5) { discard; }
    //vec4 color = vec4(0.0, 1.0, 0.0, 1.0);
    //float depth = texture(DiffuseSampler, texCoord0);
    //vec3 color = textureProj(Sampler0, texProj0).rgb;
    //if (color.z < 0.5) { color.z = 0.0; }
    //fragColor = vec4(color, 1.0); //* ColorModulator;

    vec3 color = vec3(1.0, 1.0, 1.0);

    vec2 uv = texCoord0.xy; //gl_FragCoord.xy / ScreenSize.xy;
    // Normalized coordinates : ranges from 0.0 to 1.0

    uv = uv - 0.5;
    // shifts 0.0 to be centered, but now coordinates range from -0.5 to 0.5
    uv = uv * 2;
    // coordinates now range from -1 to 1

    //uv.x *= ScreenSize.x / ScreenSize.y;
    // fixes aspect ratio

    float d = length(uv);

    //d -= time;
    d = sin(d*8. + (GameTime * 600))/8.;
    d = abs(d);
    d = smoothstep(0.1, 0.11, d);

    //if( d > 0.5 ) { color = vec3(0.0, 0.0, 0.0); };

    //fragColor = vec4(uv.x, uv.y, 1.0, 1.0);
    fragColor = vec4(d, d, d, 1.0);
}
