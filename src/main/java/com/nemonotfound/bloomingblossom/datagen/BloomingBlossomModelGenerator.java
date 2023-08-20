package com.nemonotfound.bloomingblossom.datagen;

import com.nemonotfound.bloomingblossom.blocks.BloomingBlossomBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class BloomingBlossomModelGenerator extends FabricModelProvider {

    public BloomingBlossomModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator
                .registerTintableCross(BloomingBlossomBlocks.EXTENDED_CHERRY_SAPLING,
                        BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerCubeAllModelTexturePool(BloomingBlossomBlocks.EXTENDED_CHERRY_LEAVES);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
