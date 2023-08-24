package com.nemonotfound.bloomingblossom.world.tree;

import com.mojang.serialization.Codec;
import com.nemonotfound.bloomingblossom.BloomingBlossomMod;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.Random;

import static com.nemonotfound.bloomingblossom.BloomingBlossomMod.log;
import static net.minecraft.state.property.Properties.FLOWER_AMOUNT;

public class ExtendedCherryTreeDecorator extends TreeDecorator {

    public static final ExtendedCherryTreeDecorator INSTANCE = new ExtendedCherryTreeDecorator();
    public static final Codec<ExtendedCherryTreeDecorator> CODEC = Codec.unit(() -> INSTANCE);
    private static final TreeDecoratorType<ExtendedCherryTreeDecorator> EXTENDED_CHERRY_DECORATOR = new TreeDecoratorType<>(CODEC);

    public ExtendedCherryTreeDecorator() {
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return EXTENDED_CHERRY_DECORATOR;
    }

    @Override
    public void generate(Generator generator) {
        BlockPos logPosition = generator.getLogPositions().get(0);
        generateRandomFLowers(generator, logPosition, 80, 0, 2);
        generateRandomFLowers(generator, logPosition, 50, 2, 4);
        generateRandomFLowers(generator, logPosition, 40, 4, 5);
    }

    public static void register() {
        Registry.register(Registries.TREE_DECORATOR_TYPE, new Identifier(BloomingBlossomMod.MOD_ID, "extended_cherry_decorator"), EXTENDED_CHERRY_DECORATOR);
    }

    private void generateRandomFLowers(Generator generator, BlockPos logPosition, int probability, int from, int to) {
        Random random = new Random();

        for (int i = from; i < to; i++) {
            for (int j = from; j < to; j++) {
                generateFlowersByProbability(random, probability, generator, logPosition, to - i + from, j + 1);
                generateFlowersByProbability(random, probability, generator, logPosition, to - i + from, -j - 1);
                generateFlowersByProbability(random, probability, generator, logPosition, -to + i - from, j + 1);
                generateFlowersByProbability(random, probability, generator, logPosition, -to + i - from, -j - 1);

                generateFlowersByProbability(random, probability, generator, logPosition, i + 1, 0);
                generateFlowersByProbability(random, probability, generator, logPosition, -i - 1, 0);
                generateFlowersByProbability(random, probability, generator, logPosition, 0, i + 1);
                generateFlowersByProbability(random, probability, generator, logPosition, 0, i - 1);
            }
        }
    }

    private void generateFlowersByProbability(Random random, int probability, Generator generator, BlockPos logPosition, int i, int j) {
        int randomNumber = random.nextInt(100 - 1 + 1) + 1;
        if (randomNumber < probability) {
            putFlowers(generator, logPosition.north(i).east(j).up(), random);
        }
    }

    private void putFlowers(Generator generator, BlockPos petalPosition, Random random) {
        for (int i = 0; i < 3; i++) {
            if (isPetalPlantable(generator, petalPosition.down(i))) {
                int randomNumber = random.nextInt(100 - 1 + 1) + 1;
                int petalCount = 1;

                if (randomNumber < 10) {
                    petalCount = 4;
                } else if (10 < randomNumber && randomNumber < 20) {
                    petalCount = 3;
                } else if (20 < randomNumber && randomNumber < 50) {
                    petalCount = 2;
                }

                generator.replace(petalPosition.down(i), Blocks.PINK_PETALS.getDefaultState().with(FLOWER_AMOUNT, petalCount));
            }
        }
    }

    private boolean isPetalPlantable(Generator generator, BlockPos petalPosition) {
        BlockPos petalGroundPosition = petalPosition.down();
        boolean isGroundSoil = Feature.isSoil(generator.getWorld(), petalGroundPosition);
        boolean isPetalPositionAir = generator.isAir(petalPosition);
        log.info(String.format("The block at x: %s y: %s z: %s is soil: %s", petalGroundPosition.getX(),
                petalGroundPosition.getY(), petalGroundPosition.getZ(), isGroundSoil));
        log.info(String.format("The block at x: %s y: %s z: %s is air: %s", petalPosition.getX(),
                petalPosition.getY(), petalPosition.getZ(), isPetalPositionAir));

        return isGroundSoil && isPetalPositionAir;
    }
}
