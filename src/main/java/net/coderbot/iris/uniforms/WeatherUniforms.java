package net.coderbot.iris.uniforms;

import static net.coderbot.iris.gl.uniform.UniformUpdateFrequency.PER_TICK;

import net.coderbot.iris.gl.uniform.UniformHolder;
import net.coderbot.iris.uniforms.transforms.SmoothedFloat;

import net.minecraft.client.MinecraftClient;

/**
 * @see <a href="https://github.com/IrisShaders/ShaderDoc/blob/master/uniforms.md#weather">Uniforms: Weather</a>
 */
public class WeatherUniforms {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	private WeatherUniforms() {
	}

	public static void addWeatherUniforms(UniformHolder uniforms, FrameUpdateNotifier frameUpdateNotifier) {
		uniforms
			.uniform1f(PER_TICK, "rainStrength", WeatherUniforms::getRainStrength)
			// TODO: Parse the value of const float wetnessHalflife from the shaderpacks' fragment configuration
			.uniform1f(PER_TICK, "wetness", getWetness(frameUpdateNotifier));
        }

	private static float getRainStrength() {
		if (client.world == null) {
			return 0f;
		}
		return client.world.getRainGradient(CapturedRenderingState.INSTANCE.getTickDelta());
	}

	private static SmoothedFloat getWetness(FrameUpdateNotifier frameUpdateNotifier) {
		if (client.world == null) {
			return new SmoothedFloat(0F, () -> 0F, frameUpdateNotifier);
		}
		long systemTime = System.currentTimeMillis();
		long lastSystemTime = systemTime;
		long diffSystemTime = systemTime - lastSystemTime;
		float timeSomething = diffSystemTime * 0.01F;
		float idk = Math.exp(Math.log(0.5D) * timeSomething / getRainStrength()) > 0F ? 600F : 200F;
		return new SmoothedFloat(idk, () -> 0 * idk + getRainStrength() * (1.0F - idk), frameUpdateNotifier);
	}
}
