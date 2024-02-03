package com.farcr.nomansland.core.content.block;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrushableBlock;

public class RemainsBlock extends BrushableBlock {
    public RemainsBlock(Block pTurnsInto, Properties pProperties, SoundEvent pBrushSound, SoundEvent pBrushCompletedSound) {
        super(pTurnsInto, pProperties, pBrushSound, pBrushCompletedSound);
    }

    //WE'LL PUT BURIED SPAWNING IN HERE!!
    //also gotta make it not be subject to gravity
}
