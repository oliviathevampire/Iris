package net.coderbot.iris.uniforms;

import com.google.common.collect.ImmutableList;
import net.coderbot.iris.gl.uniform.LocationalUniformHolder;
import net.coderbot.iris.gl.uniform.UniformHolder;
import net.coderbot.iris.gl.uniform.UniformType;
import net.coderbot.iris.gl.uniform.UniformUpdateFrequency;
import net.coderbot.iris.texunits.TextureUnit;

import static net.coderbot.iris.gl.uniform.UniformUpdateFrequency.ONCE;

public class SamplerUniforms {
	public static final int COLOR_TEX_0 = 0;
	public static final int COLOR_TEX_1 = 1;
	public static final int COLOR_TEX_2 = 2;
	public static final int COLOR_TEX_3 = 3;
	public static final int COLOR_TEX_4 = 7;
	public static final int COLOR_TEX_5 = 8;
	public static final int COLOR_TEX_6 = 9;
	public static final int COLOR_TEX_7 = 10;
	public static final int COLOR_TEX_8 = 16;
	public static final int COLOR_TEX_9 = 17;
	public static final int COLOR_TEX_10 = 18;
	public static final int COLOR_TEX_11 = 19;
	public static final int COLOR_TEX_12 = 20;
	public static final int COLOR_TEX_13 = 21;
	public static final int COLOR_TEX_14 = 22;
	public static final int COLOR_TEX_15 = 23;

	public static final int COLOR_IMG_0 = 0;
	public static final int COLOR_IMG_1 = 1;
	public static final int COLOR_IMG_2 = 2;
	public static final int COLOR_IMG_3 = 3;
	public static final int COLOR_IMG_4 = 7;
	public static final int COLOR_IMG_5 = 8;

	public static final int SHADOW_TEX_0 = 4;
	public static final int SHADOW_TEX_1 = 5;

	public static final int SHADOW_COLOR_0 = 13;
	public static final int SHADOW_COLOR_1 = 14;

	public static final int DEPTH_TEX_0 = 6;
	public static final int DEPTH_TEX_1 = 11;
	public static final int DEPTH_TEX_2 = 12;

	public static final int NOISE_TEX = 15;

	public static final int DEFAULT_COLOR = COLOR_TEX_0;
	public static final int DEFAULT_DEPTH = DEPTH_TEX_0;

	private SamplerUniforms() {
		// no construction allowed
	}

	// Needs to use a LocationalUniformHolder as we need a way to figure out if `watershadow` is used or not.
	public static void addCommonSamplerUniforms(LocationalUniformHolder uniforms) {
		// Generic always-accessible samplers
		addSampler(uniforms, COLOR_TEX_0, "colortex0");
		addSampler(uniforms, COLOR_TEX_1, "colortex1");
		addSampler(uniforms, COLOR_TEX_2, "colortex2");
		addSampler(uniforms, COLOR_TEX_3, "colortex3");
		addSampler(uniforms, COLOR_TEX_4, "gaux1", "colortex4");
		addSampler(uniforms, COLOR_TEX_5, "gaux2", "colortex5");
		addSampler(uniforms, COLOR_TEX_6, "gaux3", "colortex6");
		addSampler(uniforms, COLOR_TEX_7, "gaux4", "colortex7");
		addSampler(uniforms, COLOR_TEX_8, "colortex8");
		addSampler(uniforms, COLOR_TEX_9, "colortex9");
		addSampler(uniforms, COLOR_TEX_10, "colortex10");
		addSampler(uniforms, COLOR_TEX_11, "colortex11");
		addSampler(uniforms, COLOR_TEX_12, "colortex12");
		addSampler(uniforms, COLOR_TEX_13, "colortex13");
		addSampler(uniforms, COLOR_TEX_14, "colortex14");
		addSampler(uniforms, COLOR_TEX_15, "colortex15");

		// Shadow
		addSampler(uniforms, SHADOW_TEX_0, "watershadow", "shadowtex0");

		// Note: This will make it so that "watershadow" is printed twice to the log, oh well
		// Check if the "watershadow" uniform is active. If so, the "shadow" texture will have a separate texture unit
		boolean waterShadowEnabled = uniforms.location("watershadow", UniformType.INT).isPresent();

		addSampler(uniforms, waterShadowEnabled ? SHADOW_TEX_1 : SHADOW_TEX_0, "shadow");

		addSampler(uniforms, SHADOW_TEX_1, "shadowtex1");
		addSampler(uniforms, SHADOW_COLOR_0, "shadowcolor", "shadowcolor0");
		addSampler(uniforms, SHADOW_COLOR_1, "shadowcolor1");

		// Noise
		addSampler(uniforms, NOISE_TEX, "noisetex");
	}

	public static boolean hasShadowSamplers(LocationalUniformHolder uniformHolder) {
		// TODO: Keep this up to date with the actual definitions.
		ImmutableList<String> shadowSamplers = ImmutableList.of("shadowtex0", "shadowtex1", "shadow", "watershadow",
				"shadowcolor", "shadowcolor0", "shadowcolor1");

		for (String samplerName : shadowSamplers) {
			if (uniformHolder.location(samplerName, UniformType.INT).isPresent()) {
				return true;
			}
		}

		return false;
	}

	public static void addWorldSamplerUniforms(UniformHolder uniforms) {
		uniforms
				.uniform1i(ONCE, "tex", TextureUnit.TERRAIN::getSamplerId)
				.uniform1i(ONCE, "texture", TextureUnit.TERRAIN::getSamplerId)
				.uniform1i(ONCE, "lightmap", TextureUnit.LIGHTMAP::getSamplerId)
				.uniform1i(ONCE, "normals", () -> 2)
				.uniform1i(ONCE, "specular", () -> 3);
	}

	public static void addDepthSamplerUniforms(UniformHolder uniforms) {
		addSampler(uniforms, DEFAULT_DEPTH, "depthtex0");
		addSampler(uniforms, DEPTH_TEX_1, "depthtex1");
		addSampler(uniforms, DEPTH_TEX_2, "depthtex2");
	}

	public static void addCompositeSamplerUniforms(UniformHolder uniforms) {
		// Generic samplers
		addSampler(uniforms, COLOR_TEX_0, "gcolor", "colortex0");
		addSampler(uniforms, COLOR_TEX_1, "gdepth", "colortex1");
		addSampler(uniforms, COLOR_TEX_2, "gnormal", "colortex2");
		addSampler(uniforms, COLOR_TEX_3, "composite", "colortex3");
		addSampler(uniforms, COLOR_TEX_4, "colortex4");
		addSampler(uniforms, COLOR_TEX_5, "colortex5");
		addSampler(uniforms, COLOR_TEX_6, "colortex6");
		addSampler(uniforms, COLOR_TEX_7, "colortex6");
		addSampler(uniforms, COLOR_TEX_8, "colortex8");
		addSampler(uniforms, COLOR_TEX_9, "colortex9");
		addSampler(uniforms, COLOR_TEX_10, "colortex10");
		addSampler(uniforms, COLOR_TEX_11, "colortex11");
		addSampler(uniforms, COLOR_TEX_12, "colortex12");
		addSampler(uniforms, COLOR_TEX_13, "colortex13");
		addSampler(uniforms, COLOR_TEX_14, "colortex14");
		addSampler(uniforms, COLOR_TEX_15, "colortex15");

		// Depth
		addSampler(uniforms, DEFAULT_DEPTH, "gdepthtex", "depthtex0");
		addSampler(uniforms, DEPTH_TEX_1, "depthtex1");
		addSampler(uniforms, DEPTH_TEX_2, "depthtex2");
		addSampler(uniforms, DEPTH_TEX_2, "depthtex2");
	}

	private static void addSampler(UniformHolder uniforms, int textureUnit, String... names) {
		for (String name : names) {
			uniforms.uniform1i(UniformUpdateFrequency.ONCE, name, () -> textureUnit);
		}
	}
}
