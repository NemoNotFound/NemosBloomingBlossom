package com.nemonotfound.blooming.blossom.world.gen.treedecorator;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import static com.nemonotfound.blooming.blossom.NemosBloomingBlossom.MOD_ID;
import static com.nemonotfound.blooming.blossom.NemosBloomingBlossom.log;

public class ModTreeDecoratorType {

    public static final TreeDecoratorType<CherryTreeDecorator> CHERRY_TREE_DECORATOR = register("cherry_tree_decorator", CherryTreeDecorator.CODEC);
    public static final TreeDecoratorType<FallenLeavesTreeDecorator> FALLEN_LEAVES_TREE_DECORATOR = register("fallen_leaves_tree_decorator", FallenLeavesTreeDecorator.CODEC);

    public static void registerTreeDecoratorTypes() {
        log.info("Registering tree decorator types");
    }

    public static <P extends TreeDecorator> TreeDecoratorType<P> register(String path, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, path), new TreeDecoratorType<>(codec));
    }
}
