package com.nemonotfound.blooming.blossom.datagen;

import com.nemonotfound.blooming.blossom.world.gen.treedecorator.CherryTreeDecorator;
import com.nemonotfound.blooming.blossom.world.gen.treedecorator.FallenLeavesTreeDecorator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BloomingBlossomConfiguredFeatureProvider extends FabricDynamicRegistryProvider {

    private static final List<ResourceKey<ConfiguredFeature<?, ?>>> FALLEN_LEAVES_FEATURES = List.of(
            TreeFeatures.OAK,
            TreeFeatures.DARK_OAK,
            TreeFeatures.PALE_OAK,
            TreeFeatures.PALE_OAK_CREAKING,
            TreeFeatures.BIRCH,
            TreeFeatures.FANCY_OAK,
            TreeFeatures.SUPER_BIRCH_BEES_0002,
            TreeFeatures.SUPER_BIRCH_BEES,
            TreeFeatures.OAK_BEES_0002_LEAF_LITTER,
            TreeFeatures.OAK_BEES_002,
            TreeFeatures.OAK_BEES_005,
            TreeFeatures.BIRCH_BEES_0002,
            TreeFeatures.BIRCH_BEES_0002_LEAF_LITTER,
            TreeFeatures.BIRCH_BEES_002,
            TreeFeatures.BIRCH_BEES_005,
            TreeFeatures.FANCY_OAK_BEES_0002_LEAF_LITTER,
            TreeFeatures.FANCY_OAK_BEES_002,
            TreeFeatures.FANCY_OAK_BEES_005,
            TreeFeatures.FANCY_OAK_BEES,
            TreeFeatures.OAK_LEAF_LITTER,
            TreeFeatures.DARK_OAK_LEAF_LITTER,
            TreeFeatures.BIRCH_LEAF_LITTER,
            TreeFeatures.FANCY_OAK_LEAF_LITTER
    );

    private static final List<ResourceKey<ConfiguredFeature<?, ?>>> CHERRY_FEATURES = List.of(
            TreeFeatures.CHERRY,
            TreeFeatures.CHERRY_BEES_005
    );

    private static final ResourceKey<ConfiguredFeature<?, ?>> OAK_BEES_0002 = key("oak_bees_0002");
    private static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_OAK_BEES_0002 = key("fancy_oak_bees_0002");

    public BloomingBlossomConfiguredFeatureProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, @NonNull Entries entries) {
        HolderLookup.RegistryLookup<ConfiguredFeature<?, ?>> configuredFeatures = registries.lookupOrThrow(Registries.CONFIGURED_FEATURE);

        for (ResourceKey<ConfiguredFeature<?, ?>> key : FALLEN_LEAVES_FEATURES) {
            entries.add(key, withAdditionalDecorators(configuredFeatures.getOrThrow(key).value(), FallenLeavesTreeDecorator.INSTANCE));
        }

        for (ResourceKey<ConfiguredFeature<?, ?>> key : CHERRY_FEATURES) {
            entries.add(key, withAdditionalDecorators(configuredFeatures.getOrThrow(key).value(), CherryTreeDecorator.INSTANCE));
        }

        entries.add(OAK_BEES_0002, withAdditionalDecorators(configuredFeatures.getOrThrow(TreeFeatures.OAK).value(),
                new BeehiveDecorator(0.002F), FallenLeavesTreeDecorator.INSTANCE));
        entries.add(FANCY_OAK_BEES_0002, withAdditionalDecorators(configuredFeatures.getOrThrow(TreeFeatures.FANCY_OAK).value(),
                new BeehiveDecorator(0.002F), FallenLeavesTreeDecorator.INSTANCE));
    }

    @Override
    public @NonNull String getName() {
        return "Nemo's Blooming Blossom configured features";
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> key(String path) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.withDefaultNamespace(path));
    }

    private static ConfiguredFeature<?, ?> withAdditionalDecorators(ConfiguredFeature<?, ?> configuredFeature, TreeDecorator... decorators) {
        if (!(configuredFeature.config() instanceof TreeConfiguration treeConfiguration)) {
            throw new IllegalStateException("Expected a tree configured feature, got " + configuredFeature);
        }

        return new ConfiguredFeature<>(Feature.TREE, copyWithAdditionalDecorators(treeConfiguration, List.of(decorators)));
    }

    private static TreeConfiguration copyWithAdditionalDecorators(TreeConfiguration treeConfiguration, List<TreeDecorator> additionalDecorators) {
        List<TreeDecorator> decorators = new ArrayList<>(treeConfiguration.decorators);
        decorators.addAll(additionalDecorators);

        TreeConfiguration.TreeConfigurationBuilder builder = new TreeConfiguration.TreeConfigurationBuilder(
                treeConfiguration.trunkProvider,
                treeConfiguration.trunkPlacer,
                treeConfiguration.foliageProvider,
                treeConfiguration.foliagePlacer,
                treeConfiguration.rootPlacer,
                treeConfiguration.minimumSize,
                treeConfiguration.belowTrunkProvider
        ).decorators(decorators);

        if (treeConfiguration.ignoreVines) {
            builder.ignoreVines();
        }

        return builder.build();
    }
}
