package net.coderbot.iris.shaderpack;

import java.util.Set;

public class PackDirectives {
	private int noiseTextureResolution;
	private int shadowMapResolution;
	private float sunPathRotation;
	private float shadowDistance;
	private boolean areCloudsEnabled;
	private boolean separateAo;

	private final PackRenderTargetDirectives renderTargetDirectives;
	private final PackShadowDirectives shadowDirectives;

	private PackDirectives(Set<Integer> supportedRenderTargets) {
		noiseTextureResolution = 256;
		sunPathRotation = 0.0F;
		shadowMapResolution = 1024;
		shadowDistance = 190F;
		renderTargetDirectives = new PackRenderTargetDirectives(supportedRenderTargets);
		shadowDirectives = new PackShadowDirectives();
	}

	PackDirectives(Set<Integer> supportedRenderTargets, ShaderProperties properties) {
		this(supportedRenderTargets);
		areCloudsEnabled = properties.areCloudsEnabled();
		separateAo = properties.getSeparateAo().orElse(false);
	}

	PackDirectives(Set<Integer> supportedRenderTargets, PackDirectives directives) {
		this(supportedRenderTargets);
		areCloudsEnabled = directives.areCloudsEnabled();
		separateAo = directives.separateAo;
	}

	public int getNoiseTextureResolution() {
		return noiseTextureResolution;
	}

	public float getSunPathRotation() {
		return sunPathRotation;
	}

	public boolean areCloudsEnabled() {
		return areCloudsEnabled;
	}

	public boolean shouldUseSeparateAo() {
		return separateAo;
	}

	public int getShadowMapResolution() {
		return shadowMapResolution;
	}

	public float getShadowDistance() {
		return shadowDistance;
	}

	public PackRenderTargetDirectives getRenderTargetDirectives() {
		return renderTargetDirectives;
	}

	public PackShadowDirectives getShadowDirectives() {
		return shadowDirectives;
	}

	public void acceptDirectivesFrom(DirectiveHolder directives) {
		renderTargetDirectives.acceptDirectives(directives);
		shadowDirectives.acceptDirectives(directives);

		directives.acceptConstIntDirective("noiseTextureResolution",
				noiseTextureResolution -> this.noiseTextureResolution = noiseTextureResolution);

		directives.acceptConstFloatDirective("sunPathRotation",
				sunPathRotation -> this.sunPathRotation = sunPathRotation);

		directives.acceptConstIntDirective("shadowMapResolution",
				shadowMapResolution -> this.shadowMapResolution = shadowMapResolution);

		directives.acceptConstFloatDirective("shadowDistance",
				shadowDistance -> this.shadowDistance = shadowDistance);
	}
}
