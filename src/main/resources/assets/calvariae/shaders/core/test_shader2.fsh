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

    fragColor = vec4(Colour, 1.0);
}
