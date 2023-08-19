package com.nemonotfound.bloomingblossom;

import com.nemonotfound.bloomingblossom.blocks.BloomingBlossomBlocks;
import com.nemonotfound.bloomingblossom.mixin.BloomingBlossomMixin;
import com.nemonotfound.bloomingblossom.world.tree.ExtendedCherryTreeDecorator;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BloomingBlossomMod implements ModInitializer {

	public static final String MOD_ID = "blooming-blossom";
    public static final Logger log = LoggerFactory.getLogger(MOD_ID);
	public static final TreeDecoratorType<ExtendedCherryTreeDecorator> EXTENDED_CHERRY_DECORATOR =
			BloomingBlossomMixin.callRegister(MOD_ID + ":extended_cherry_decorator", ExtendedCherryTreeDecorator.CODEC);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		log.info("Thank you for using Blooming Blossom!");
		BloomingBlossomBlocks.registerItemBlock();
	}
}