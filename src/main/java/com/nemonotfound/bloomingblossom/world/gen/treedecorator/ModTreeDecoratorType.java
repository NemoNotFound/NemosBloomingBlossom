package com.nemonotfound.bloomingblossom.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import static com.nemonotfound.bloomingblossom.NemosBloomingBlossom.MOD_ID;
import static com.nemonotfound.bloomingblossom.NemosBloomingBlossom.log;

public class ModTreeDecoratorType {

    public static final TreeDecoratorType<CherryTreeDecorator> CHERRY_TREE_DECORATOR = register("cherry_tree_decorator", CherryTreeDecorator.CODEC);
    public static final TreeDecoratorType<FallenLeavesTreeDecorator> FALLEN_LEAVES_TREE_DECORATOR = register("fallen_leaves_tree_decorator", FallenLeavesTreeDecorator.CODEC);

    public static void registerTreeDecoratorTypes() {
        log.info("Registering tree decorator types");
    }

    public static <P extends TreeDecorator> TreeDecoratorType<P> register(String path, Codec<P> codec) {
        return Registry.register(Registries.TREE_DECORATOR_TYPE, Identifier.of(MOD_ID, path), new TreeDecoratorType<>(codec));
    }
}
