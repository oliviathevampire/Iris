package net.coderbot.iris.mixin.texunits;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GlStateManager.class)
@Environment(EnvType.CLIENT)
public class MixinGlStateManager {
	// TODO(21w10a): Fix up overlay color texture unit
	/*@ModifyConstant(method = "setupOverlayColor(II)V", constant = @Constant(intValue = GL15.GL_TEXTURE1), require = 1)
	private static int iris$fixOverlayTextureUnit(int texUnit) {
		return TextureUnit.OVERLAY.getUnitId();
	}

	@ModifyConstant(method = "teardownOverlayColor()V", constant = @Constant(intValue = GL15.GL_TEXTURE1), require = 1)
	private static int iris$fixOverlayTextureUnitTeardown(int texUnit) {
		return TextureUnit.OVERLAY.getUnitId();
	}*/
}
