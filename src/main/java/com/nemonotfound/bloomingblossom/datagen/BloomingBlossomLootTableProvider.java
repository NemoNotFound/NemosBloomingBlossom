package com.nemonotfound.bloomingblossom.datagen;

import com.nemonotfound.bloomingblossom.blocks.BloomingBlossomBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class BloomingBlossomLootTableProvider extends FabricBlockLootTableProvider {

    public BloomingBlossomLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(BloomingBlossomBlocks.EXTENDED_CHERRY_LEAVES, leavesDrops(BloomingBlossomBlocks.EXTENDED_CHERRY_LEAVES, BloomingBlossomBlocks.EXTENDED_CHERRY_SAPLING, 0.05f));
    }
}
