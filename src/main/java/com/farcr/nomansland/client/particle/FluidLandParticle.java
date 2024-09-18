package com.farcr.nomansland.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SpriteSet;

public class FluidLandParticle extends FluidFallingParticle {

    public FluidLandParticle(ClientLevel level, double x, double y, double z, SpriteSet spriteSet) {
        super(level, x, y, z, spriteSet);
        this.lifetime = (int) (16.0 / (Math.random() * 0.8 + 0.2));
    }

    @Override
    protected void postMoveUpdate() {
    }
}
