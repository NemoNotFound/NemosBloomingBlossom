package com.nemonotfound.bloomingblossom.world;

import com.google.common.collect.ImmutableList;
import com.nemonotfound.bloomingblossom.BloomingBlossomMod;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.trunk.CherryTrunkPlacer;

public class BloomingBlossomConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?,?>> EXTENDED_CHERRY = registerKey("extended_cherry");
    public static void bootstrap(Registerable<ConfiguredFeature<?,?>> context) {
        ConfiguredFeatures
                .register(context, EXTENDED_CHERRY, Feature.TREE, createTreeFeatureConfig());
    }

    public static RegistryKey<ConfiguredFeature<?,?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(BloomingBlossomMod.MOD_ID, name));
    }

    private static TreeFeatureConfig createTreeFeatureConfig() {
        WeightedListIntProvider weightedListIntProvider = new WeightedListIntProvider(DataPool.<IntProvider>builder()
                .add(ConstantIntProvider.create(1), 1)
                .add(ConstantIntProvider.create(2), 1)
                .add(ConstantIntProvider.create(3), 1)
                .build());

        CherryTrunkPlacer cherryTrunkPlacer = new CherryTrunkPlacer(7,
                1,
                0,
                weightedListIntProvider,
                UniformIntProvider.create(2, 4),
                UniformIntProvider.create(-4, -3),
                UniformIntProvider.create(-1, 0));

        CherryFoliagePlacer cherryFoliagePlacer = new CherryFoliagePlacer(ConstantIntProvider.create(4),
                ConstantIntProvider.create(0),
                ConstantIntProvider.create(5),
                0.25f,
                0.5f,
                0.16666667f,
                0.33333334f);

        return new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.CHERRY_LOG),
                cherryTrunkPlacer,
                BlockStateProvider.of(Blocks.CHERRY_LEAVES),
                cherryFoliagePlacer,
                new TwoLayersFeatureSize(1, 0, 2))
                .ignoreVines()
                .decorators(ImmutableList.of(new AlterGroundTreeDecorator(BlockStateProvider.of(Blocks.PINK_PETALS))))
                .build();
    }
}
