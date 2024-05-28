package com.farcr.nomansland.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BaseAshSmokeParticle;
import net.minecraft.client.particle.SpriteSet;

public class CaveDustParticle extends BaseAshSmokeParticle {

    public CaveDustParticle(ClientLevel pLevel, double pX, double pY, double pZ, float pXSeedMultiplier, float pYSpeedMultiplier, float pZSpeedMultiplier, double pXSpeed, double pYSpeed, double pZSpeed, float pQuadSizeMultiplier, SpriteSet pSprites, float pRColMultiplier, int pLifetime, float pGravity, boolean pHasPhysics) {
        super(pLevel, pX, pY, pZ, pXSeedMultiplier, pYSpeedMultiplier, pZSpeedMultiplier, pXSpeed, pYSpeed, pZSpeed, pQuadSizeMultiplier, pSprites, pRColMultiplier, pLifetime, pGravity, pHasPhysics);
    }
}
