package net.coderbot.iris.layer;

import net.coderbot.iris.mixin.renderlayer.RenderLayerAccessor;
import net.minecraft.client.render.RenderLayer;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class IrisRenderLayerWrapper extends RenderLayer {
	private final UseProgramRenderPhase useProgram;
	private final RenderLayer wrapped;

	public IrisRenderLayerWrapper(String name, RenderLayer wrapped, UseProgramRenderPhase useProgram) {
		super(name, wrapped.getVertexFormat(), wrapped.getDrawMode(), wrapped.getExpectedBufferSize(),
				wrapped.hasCrumbling(), isTranslucent(wrapped), wrapped::startDrawing, wrapped::endDrawing);

		this.useProgram = useProgram;
		this.wrapped = wrapped;
	}

	@Override
	public void startDrawing() {
		super.startDrawing();

		useProgram.startDrawing();
	}

	@Override
	public void endDrawing() {
		useProgram.endDrawing();

		super.endDrawing();
	}

	public RenderLayer unwrap() {
		return this.wrapped;
	}

	@Override
	public Optional<RenderLayer> getAffectedOutline() {
		return this.wrapped.getAffectedOutline();
	}

	@Override
	public boolean isOutline() {
		return this.wrapped.isOutline();
	}

	@Override
	public boolean equals(@Nullable Object object) {
		if (object == null) {
			return false;
		}

		if (object.getClass() != this.getClass()) {
			return false;
		}

		IrisRenderLayerWrapper other = (IrisRenderLayerWrapper) object;

		return Objects.equals(this.wrapped, other.wrapped) && Objects.equals(this.useProgram, other.useProgram);
	}

	@Override
	public int hashCode() {
		// Add one so that we don't have the exact same hash as the wrapped object.
		// This means that we won't have a guaranteed collision if we're inserted to a map alongside the unwrapped object.
		return this.wrapped.hashCode() + 1;
	}

	@Override
	public String toString() {
		return "iris:" + this.wrapped.toString();
	}

	private static boolean isTranslucent(RenderLayer layer) {
		return ((RenderLayerAccessor) layer).isTranslucent();
	}
}
