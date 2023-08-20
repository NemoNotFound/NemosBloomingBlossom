package com.nemonotfound.bloomingblossom.blocks;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class BloomingBlossomFlammableRegistry {

    public static void registerFlammableBlocks() {
        FlammableBlockRegistry.getDefaultInstance().add(BloomingBlossomBlocks.EXTENDED_CHERRY_LEAVES, 30, 60);
    }
}
