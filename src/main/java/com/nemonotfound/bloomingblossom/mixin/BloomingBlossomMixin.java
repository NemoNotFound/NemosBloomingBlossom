package com.nemonotfound.bloomingblossom.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.nemonotfound.bloomingblossom.BloomingBlossomMod.log;

@Mixin(TreeConfiguredFeatures.class)
public abstract class BloomingBlossomMixin {

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void boot(Registerable<ConfiguredFeature<?, ?>> featureRegisterable, CallbackInfo callbackInfo) {
        log.info("IT WORKS!");
    }

    @Inject(method = "cherry", at = @At(value = "RETURN"), cancellable = true)
    private static void init(CallbackInfoReturnable<TreeFeatureConfig.Builder> returnValue) {
        log.info("Custom tree generation. Woooo");

        returnValue.setReturnValue(returnValue.getReturnValue()
                .decorators(ImmutableList.of(new AlterGroundTreeDecorator(BlockStateProvider.of(Blocks.PINK_PETALS)))));
    }
}