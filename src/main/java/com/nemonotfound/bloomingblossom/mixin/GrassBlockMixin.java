package com.nemonotfound.bloomingblossom.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.GrassBlock;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GrassBlock.class)
public class GrassBlockMixin {

    @ModifyExpressionValue(
            method = "grow",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I", ordinal = 5)
    )
    private int nextInt(int original, @Local(ordinal = 1) BlockPos blockPos, @Local ServerWorld world) {
        if (world.getBiome(blockPos).isIn(BiomeTags.of("is_cherry_grove"))) {
            return original > 2 ? 0 : original;
        } else {
            return original;
        }
    }
}
