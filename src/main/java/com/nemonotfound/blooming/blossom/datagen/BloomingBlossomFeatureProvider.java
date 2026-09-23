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
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BloomingBlossomFeatureProvider extends FabricDynamicRegistryProvider {

    private static final List<ResourceKey<Feature>> FALLEN_LEAVES_FEATURES = List.of(
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

    private static final List<ResourceKey<Feature>> CHERRY_FEATURES = List.of(
            TreeFeatures.CHERRY,
            TreeFeatures.CHERRY_BEES_005
    );

    private static final ResourceKey<Feature> OAK_BEES_0002 = key("oak_bees_0002");
    private static final ResourceKey<Feature> FANCY_OAK_BEES_0002 = key("fancy_oak_bees_0002");

    public BloomingBlossomFeatureProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, @NonNull Entries entries) {
        HolderLookup.RegistryLookup<Feature> features = registries.lookupOrThrow(Registries.FEATURE);

        for (ResourceKey<Feature> key : FALLEN_LEAVES_FEATURES) {
            entries.add(key, withAdditionalDecorators(features.getOrThrow(key).value(), FallenLeavesTreeDecorator.INSTANCE));
        }

        for (ResourceKey<Feature> key : CHERRY_FEATURES) {
            entries.add(key, withAdditionalDecorators(features.getOrThrow(key).value(), CherryTreeDecorator.INSTANCE));
        }

        entries.add(OAK_BEES_0002, withAdditionalDecorators(features.getOrThrow(TreeFeatures.OAK).value(),
                new BeehiveDecorator(0.002F), FallenLeavesTreeDecorator.INSTANCE));
        entries.add(FANCY_OAK_BEES_0002, withAdditionalDecorators(features.getOrThrow(TreeFeatures.FANCY_OAK).value(),
                new BeehiveDecorator(0.002F), FallenLeavesTreeDecorator.INSTANCE));
    }

    @Override
    public @NonNull String getName() {
        return "Nemo's Blooming Blossom Features";
    }

    private static ResourceKey<Feature> key(String path) {
        return ResourceKey.create(Registries.FEATURE, Identifier.withDefaultNamespace(path));
    }

    private static TreeFeature withAdditionalDecorators(Feature feature, TreeDecorator... decorators) {
        if (!(feature instanceof TreeFeature treeFeature)) {
            throw new IllegalStateException("Expected a tree feature, got " + feature);
        }

        return copyWithAdditionalDecorators(treeFeature, List.of(decorators));
    }

    private static TreeFeature copyWithAdditionalDecorators(TreeFeature treeFeature, List<TreeDecorator> additionalDecorators) {
        List<TreeDecorator> decorators = new ArrayList<>(treeFeature.decorators());
        decorators.addAll(additionalDecorators);

        TreeFeature.Builder builder = new TreeFeature.Builder(
                treeFeature.trunkProvider(),
                treeFeature.trunkPlacer(),
                treeFeature.foliageProvider(),
                treeFeature.foliagePlacer(),
                treeFeature.rootPlacer(),
                treeFeature.minimumSize(),
                treeFeature.belowTrunkProvider()
        ).decorators(decorators);

        if (treeFeature.ignoreVines()) {
            builder.ignoreVines();
        }

        return builder.build();
    }
}
