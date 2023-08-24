package com.nemonotfound.bloomingblossom.mixin;

import com.nemonotfound.bloomingblossom.world.tree.ExtendedCherryTreeDecorator;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(TreeConfiguredFeatures.class)
public class BloomingBlossomMixin {

    @Inject(method = "cherry", at = @At("RETURN"), cancellable = true)
    private static void cherry(CallbackInfoReturnable<TreeFeatureConfig.Builder> returnable) {
        TreeFeatureConfig.Builder builder = returnable.getReturnValue().decorators(List.of(new ExtendedCherryTreeDecorator()));
        returnable.setReturnValue(builder);
    }
}
