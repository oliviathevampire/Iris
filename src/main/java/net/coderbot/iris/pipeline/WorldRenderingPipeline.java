package net.coderbot.iris.pipeline;

import net.coderbot.iris.layer.GbufferProgram;
import net.coderbot.iris.mixin.WorldRendererAccessor;
import net.coderbot.iris.pipeline.newshader.WorldRenderingPhase;
import net.minecraft.client.render.Camera;

import java.util.List;

public interface WorldRenderingPipeline {
	void beginWorldRendering();

	void renderShadows(WorldRendererAccessor worldRenderer, Camera camera);

	void addDebugText(List<String> messages);

	void beginShadowRender();

	void endShadowRender();

	void beginTranslucents();

	void pushProgram(GbufferProgram program);

	void popProgram(GbufferProgram program);

	void finalizeWorldRendering();

	default void setPhase(WorldRenderingPhase phase) {
		// no-op
	}

	boolean shouldDisableVanillaEntityShadows();

	boolean shouldDisableDirectionalShading();

	boolean shouldRenderClouds();

	float getSunPathRotation();

	int getShadowMapResolution();

	float getShadowDistance();
}
