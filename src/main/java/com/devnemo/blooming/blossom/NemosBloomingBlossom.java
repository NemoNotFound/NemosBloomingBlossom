package com.devnemo.blooming.blossom;

import com.devnemo.blooming.blossom.world.gen.treedecorator.ModTreeDecoratorType;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NemosBloomingBlossom implements ModInitializer {

    public static final String MOD_ID = "nemos-blooming-blossom";
    public static final Logger log = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        log.info("Thank you for using Nemo's Blooming Blossom!");

        ModTreeDecoratorType.registerTreeDecoratorTypes();
    }
}