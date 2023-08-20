package com.nemonotfound.bloomingblossom.datagen;

import com.nemonotfound.bloomingblossom.blocks.BloomingBlossomBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

import static net.minecraft.block.Blocks.CHERRY_SAPLING;

public class BloomingBlossomRecipeGenerator extends FabricRecipeProvider {

    public BloomingBlossomRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, BloomingBlossomBlocks.EXTENDED_CHERRY_SAPLING)
                .input(CHERRY_SAPLING)
                .criterion(FabricRecipeProvider.hasItem(BloomingBlossomBlocks.EXTENDED_CHERRY_SAPLING),
                        FabricRecipeProvider.conditionsFromItem(BloomingBlossomBlocks.EXTENDED_CHERRY_SAPLING))
                .criterion(FabricRecipeProvider.hasItem(CHERRY_SAPLING),
                        FabricRecipeProvider.conditionsFromItem(CHERRY_SAPLING))
                .offerTo(exporter);
    }
}
