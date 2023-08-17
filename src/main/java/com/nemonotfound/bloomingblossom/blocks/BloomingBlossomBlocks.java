package com.nemonotfound.bloomingblossom.blocks;

import com.nemonotfound.bloomingblossom.BloomingBlossomMod;
import com.nemonotfound.bloomingblossom.world.tree.ExtendedCherrySaplingGenerator;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BloomingBlossomBlocks {

    public static final Block EXTENDED_CHERRY_SAPLING = registerBlock("extended_cherry_sapling",
            new SaplingBlock(new ExtendedCherrySaplingGenerator(), FabricBlockSettings.copyOf(Blocks.CHERRY_SAPLING)));

    private static Block registerBlock(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(BloomingBlossomMod.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        return Registry.register(Registries.BLOCK, new Identifier(BloomingBlossomMod.MOD_ID, name), block);
    }

    public static void registerItemBlock() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(BloomingBlossomBlocks::addItemToItemGroup);
    }

    private static void addItemToItemGroup(FabricItemGroupEntries entries){
        entries.add(EXTENDED_CHERRY_SAPLING);
    }
}
