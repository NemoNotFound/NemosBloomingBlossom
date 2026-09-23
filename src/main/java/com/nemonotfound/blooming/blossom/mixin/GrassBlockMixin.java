package com.nemonotfound.blooming.blossom.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.GrassBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GrassBlock.class)
public class GrassBlockMixin {

    @ModifyExpressionValue(
            method = "placeBonemealEffect",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextFloat()F", ordinal = 1)
    )
    private static float increaseFlowerChance(float original, @Local(argsOnly = true, name = "testPos") BlockPos testPos, @Local(argsOnly = true, name = "level") ServerLevel level) {
        if (level.getBiome(testPos).is(Biomes.CHERRY_GROVE)) {
            return original >= 0.375F ? 0.0F : original;
        } else {
            return original;
        }
    }
}
