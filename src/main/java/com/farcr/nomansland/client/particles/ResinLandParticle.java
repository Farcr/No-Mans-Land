package com.farcr.nomansland.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SpriteSet;

public class ResinLandParticle extends ResinFallingParticle {

    public ResinLandParticle(ClientLevel level, double x, double y, double z, SpriteSet spriteSet) {
        super(level, x, y, z, spriteSet);
        this.lifetime = (int)(64.0 / (Math.random() * 0.8 + 0.2));
    }

    @Override
    protected void postMoveUpdate() {
    }
}
