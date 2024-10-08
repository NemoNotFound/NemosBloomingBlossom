package com.nemonotfound.bloomingblossom.world.gen.treedecorator;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.Random;

public class FallenLeavesTreeDecorator extends TreeDecorator {

    public static final FallenLeavesTreeDecorator INSTANCE = new FallenLeavesTreeDecorator();
    public static final MapCodec<FallenLeavesTreeDecorator> CODEC = MapCodec.unit(() -> INSTANCE);

    @Override
    protected TreeDecoratorType<?> getType() {
        return ModTreeDecoratorType.FALLEN_LEAVES_TREE_DECORATOR;
    }

    @Override
    public void generate(Generator generator) {
        BlockPos logPosition = generator.getLogPositions().get(0);
        generateRandomLeavesBlock(generator, logPosition);
    }

    private void generateRandomLeavesBlock(Generator generator, BlockPos logPosition) {
        Random random = new Random();
        int probability = 3;
        int from = 0;
        int to = 3;

        for (int i = from; i < to; i++) {
            for (int j = from; j < to; j++) {
                //TODO: I have no idea why I did this, please refactor
                generateFallenLeavesByProbability(random, probability, generator, logPosition, to - i + from, j + 1);
                generateFallenLeavesByProbability(random, probability, generator, logPosition, to - i + from, -j - 1);
                generateFallenLeavesByProbability(random, probability, generator, logPosition, -to + i - from, j + 1);
                generateFallenLeavesByProbability(random, probability, generator, logPosition, -to + i - from, -j - 1);

                generateFallenLeavesByProbability(random, probability, generator, logPosition, i + 1, 0);
                generateFallenLeavesByProbability(random, probability, generator, logPosition, -i - 1, 0);
                generateFallenLeavesByProbability(random, probability, generator, logPosition, 0, i + 1);
                generateFallenLeavesByProbability(random, probability, generator, logPosition, 0, i - 1);
            }
        }
    }

    private void generateFallenLeavesByProbability(Random random, int probability, Generator generator, BlockPos logPosition, int i, int j) {
        int randomNumber = random.nextInt(100 - 1 + 1) + 1;
        if (randomNumber < probability) {
            placeFallenLeavesBlockUpAndDown(generator, logPosition.north(i).east(j).up());
        }
    }

    private void placeFallenLeavesBlockUpAndDown(Generator generator, BlockPos petalPosition) {
        for (int i = 0; i < 3; i++) {
            placeFallenLeavesBlock(generator, petalPosition.up(i));
            placeFallenLeavesBlock(generator, petalPosition.down(i));
        }
    }

    private void placeFallenLeavesBlock(Generator generator, BlockPos fallenLeavesPosition) {
        TestableWorld world = generator.getWorld();

        if ((world instanceof StructureWorldAccess) && areLeavesBlockPlantable(generator, fallenLeavesPosition)) {
            BlockPos leavesPosition = generator.getLeavesPositions().get(0);
            Block leavesBlock = ((StructureWorldAccess) world).getBlockState(leavesPosition).getBlock();

            if (!generator.isAir(leavesPosition)) {
                generator.replace(fallenLeavesPosition, leavesBlock.getDefaultState().with(LeavesBlock.PERSISTENT, true));
            }
        }
    }

    private boolean areLeavesBlockPlantable(Generator generator, BlockPos leavesPosition) {
        BlockPos groundPosition = leavesPosition.down();
        boolean isGroundSoil = Feature.isSoil(generator.getWorld(), groundPosition);
        boolean isLeavesPositionAir = generator.isAir(leavesPosition);
        boolean isAboveBlockPositionAir = generator.isAir(leavesPosition.up());

        return isGroundSoil && isLeavesPositionAir && isAboveBlockPositionAir;
    }
}
