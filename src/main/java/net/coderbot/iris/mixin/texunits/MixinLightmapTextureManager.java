package net.coderbot.iris.mixin.texunits;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Modifies {@link LightmapTextureManager} to use a configurable texture unit, instead of being hardcoded to texture
 * unit #2.
 */
@Mixin(LightmapTextureManager.class)
@Environment(EnvType.CLIENT)
public class MixinLightmapTextureManager {
	/*TODO(21w10a): Replace texunit hooks
	@ModifyConstant(method = "disable()V", constant = @Constant(intValue = GL15.GL_TEXTURE2), require = 1)
	private int iris$fixLightmapTextureUnit$disable(int texUnit) {
		return TextureUnit.LIGHTMAP.getUnitId();
	}

	@ModifyConstant(method = "enable()V", constant = @Constant(intValue = GL15.GL_TEXTURE2), require = 1)
	private int iris$fixLightmapTextureUnit$enable(int texUnit) {
		return TextureUnit.LIGHTMAP.getUnitId();
	}*/
}
