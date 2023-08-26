package com.nemonotfound.bloomingblossom;

import com.nemonotfound.bloomingblossom.world.tree.ExtendedCherryTreeDecorator;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BloomingBlossomMod implements ModInitializer {

    public static final String MOD_ID = "blooming-blossom";
    public static final Logger log = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        log.info("Thank you for using Blooming Blossom!");
        ExtendedCherryTreeDecorator.register();
    }
}