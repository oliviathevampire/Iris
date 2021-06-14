package net.coderbot.iris.mixin.texunits;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(OverlayTexture.class)
@Environment(EnvType.CLIENT)
public class MixinOverlayTexture {
	/*TODO(21w10a): Replace texunit hooks
	@ModifyConstant(method = "<init>()V", constant = @Constant(intValue = GL15.GL_TEXTURE1), require = 1)
	private int iris$fixOverlayTextureUnit(int texUnit) {
		return TextureUnit.OVERLAY.getUnitId();
	}*/
}
