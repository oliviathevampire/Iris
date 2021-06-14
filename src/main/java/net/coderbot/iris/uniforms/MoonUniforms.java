package net.coderbot.iris.uniforms;

import net.coderbot.iris.gl.uniform.UniformHolder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;

import java.util.Objects;

import static net.coderbot.iris.gl.uniform.UniformUpdateFrequency.PER_TICK;

public final class MoonUniforms {
	private MoonUniforms() {
	}

	/**
	 * Makes world time uniforms available to the given program
	 *
	 * @param uniforms the program to make the uniforms available to
	 */
	public static void addMoonUniforms(UniformHolder uniforms) {
		uniforms
				.uniform1i(PER_TICK, "moonPhase", () -> getWorld().getMoonPhase())
				.uniform1f(PER_TICK, "moonSize", () -> getWorld().getMoonSize());
	}

	private static ClientWorld getWorld() {
		return Objects.requireNonNull(MinecraftClient.getInstance().world);
	}
}
