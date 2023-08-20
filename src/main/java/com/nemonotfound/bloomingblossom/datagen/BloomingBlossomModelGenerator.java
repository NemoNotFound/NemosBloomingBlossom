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
        blockStateModelGenerator.registerCubeAllModelTexturePool(BloomingBlossomBlocks.EXTENDED_CHERRY_LEAVES);
        blockStateModelGenerator.registerFlowerPotPlant(BloomingBlossomBlocks.EXTENDED_CHERRY_SAPLING,
                BloomingBlossomBlocks.EXTENDED_CHERRY_SAPLING_POT, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
