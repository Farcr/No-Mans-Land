package com.farcr.nomansland.core.content.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.neoforged.neoforge.event.level.AlterGroundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Iterator;

@Mixin(AlterGroundDecorator.class)
public class AlterGroundDecoratorMixin {

    /**
     * @author
     * @reason
     */
    @Overwrite
    private void placeCircle(TreeDecorator.Context context, BlockPos pos, AlterGroundEvent.StateProvider eventProvider) {
        placeBlockAt(context, pos, eventProvider);
    }

/**
 * @author
 * @reason
 */
    @Overwrite
    private void placeBlockAt(TreeDecorator.Context context, BlockPos pos, AlterGroundEvent.StateProvider eventProvider) {
        if (Feature.isGrassOrDirt(context.level(), pos) && context.isAir(pos.above())) {
            // Ensure the dirt that is being right-clicked has a suitable block such as grass nearby
            for (Direction d : Direction.values()) {
                for (Direction d1 : Direction.values()) {
                    if (d == d1) break;
                    BlockPos newBlockPos = pos.relative(d);
                    if (Feature.isGrassOrDirt(context.level(), newBlockPos)) {
                        bonemealDirt(context, pos, eventProvider);
                        return;
                    } else if (Feature.isGrassOrDirt(context.level(), newBlockPos.relative(d1))) {
                        bonemealDirt(context, pos, eventProvider);
                        return;
                    }
                }
            }
        }

//        for (int i = 2; i >= -3; i--) {
//            BlockPos blockpos = pos.above(i);
//            if (Feature.isGrassOrDirt(context.level(), blockpos)) {
//                for (Direction d : Direction.values()) {
//                    if (!Feature.isGrassOrDirt(context.level(), blockpos.relative(d)) && !context.isAir(blockpos.relative(d))) {
//                        ci.cancel();
//                        return;
//                    };
//                }
//                context.setBlock(blockpos, eventProvider.getState(context.random(), pos));
//
//                break;
//            }
//
//            if (!context.isAir(blockpos) && i < 0) {
//                break;
//            }
//        }
//
//        ci.cancel();
//    }
    }
    private static void bonemealDirt(TreeDecorator.Context context, BlockPos pos, AlterGroundEvent.StateProvider eventProvider) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        // Iterate through a cube and find suitable dirt blocks that can be turned into the new block
        Iterator<BlockPos> it = BlockPos.betweenClosedStream(x - 3, y - 1, z - 3, x + 3, y + 2, z + 3).iterator();
        while (it.hasNext()) {
            BlockPos bp = it.next();
            for (Direction d : Direction.values()) {
                if (Feature.isGrassOrDirt(context.level(), bp) && Feature.isGrassOrDirt(context.level(), bp.relative(d)) && context.isAir(bp.above()) && context.isAir(bp.relative(d).above())) {
                    context.setBlock(bp, eventProvider.getState(context.random(), pos));
                }
            }
        }
    }
}
