package com.nemonotfound.bloomingblossom;

import com.nemonotfound.bloomingblossom.datagen.BloomingBlossomModelProvider;
import com.nemonotfound.bloomingblossom.datagen.CherryBlossomWorldGenerator;
import com.nemonotfound.bloomingblossom.world.BloomingBlossomConfiguredFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class BloomingBlossomModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(BloomingBlossomModelProvider::new);
		pack.addProvider(CherryBlossomWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, BloomingBlossomConfiguredFeatures::bootstrap);
	}
}
