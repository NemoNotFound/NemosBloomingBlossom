package com.nemonotfound.bloomingblossom.world.tree;

import com.mojang.serialization.Codec;
import com.nemonotfound.bloomingblossom.BloomingBlossomMod;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import static com.nemonotfound.bloomingblossom.BloomingBlossomMod.log;

public class ExtendedCherryTreeDecorator extends TreeDecorator {

    public static final  ExtendedCherryTreeDecorator INSTANCE = new ExtendedCherryTreeDecorator();
    public static final Codec<ExtendedCherryTreeDecorator> CODEC = Codec.unit(() -> INSTANCE);

    private ExtendedCherryTreeDecorator() {}

    @Override
    protected TreeDecoratorType<?> getType() {
        return BloomingBlossomMod.EXTENDED_CHERRY_DECORATOR;
    }

    @Override
    public void generate(Generator generator) {
       BlockPos logPosition = generator.getLogPositions().get(0);

       putFlowers(generator, logPosition.east(2).up());
    }

    private void putFlowers(Generator generator, BlockPos petalPosition) {
        for (int i = 0; i < 3; i++) {
            if (isPetalPlantable(generator, petalPosition.down(i))) {
                generator.replace(petalPosition.down(i), Blocks.PINK_PETALS.getDefaultState());
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

    private void setArea(TreeDecorator.Generator generator, BlockPos origin) {
        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                if (Math.abs(i) == 2 && Math.abs(j) == 2) continue;
                this.setColumn(generator, origin.add(i, 0, j));
            }
        }
    }

    private void setColumn(TreeDecorator.Generator generator, BlockPos origin) {
        for (int i = 2; i >= -3; --i) {
            BlockPos blockPos = origin.up(i);
            if (Feature.isSoil(generator.getWorld(), blockPos)) {
                generator.replace(blockPos, BlockStateProvider.of(Blocks.PINK_PETALS).get(generator.getRandom(), origin));
                break;
            }
            if (!generator.isAir(blockPos) && i < 0) break;
        }
    }
}
