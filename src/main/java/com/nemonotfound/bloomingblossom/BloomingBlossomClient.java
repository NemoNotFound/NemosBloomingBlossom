package com.nemonotfound.bloomingblossom;

import com.nemonotfound.bloomingblossom.blocks.BloomingBlossomBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class BloomingBlossomClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockRenderLayerMap.INSTANCE.putBlock(BloomingBlossomBlocks.EXTENDED_CHERRY_SAPLING, RenderLayer.getCutout());
	}
}