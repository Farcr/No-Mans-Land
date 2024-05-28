package com.farcr.nomansland.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.RisingParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.Mth;

public class FlameParticle extends RisingParticle {
    public FlameParticle(ClientLevel level, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet spriteSet) {
        super(level, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        this.setSprite(spriteSet.get(this.random.nextInt(4), 4));
        this.lifetime = (int)(64.0 / (Math.random() * 0.8 + 0.2));
    }

    public void move(double pX, double pY, double pZ) {
        this.setBoundingBox(this.getBoundingBox().move(pX, pY, pZ));
        this.setLocationFromBoundingbox();
    }

    public float getQuadSize(float pScaleFactor) {
        float $$1 = ((float)this.age + pScaleFactor) / (float)this.lifetime;
        return this.quadSize * (1.0F - $$1 * $$1 * 0.5F);
    }

    public int getLightColor(float pPartialTick) {
        float $$1 = ((float)this.age + pPartialTick) / (float)this.lifetime;
        $$1 = Mth.clamp($$1, 0.0F, 1.0F);
        int $$2 = super.getLightColor(pPartialTick);
        int $$3 = $$2 & 255;
        int $$4 = $$2 >> 16 & 255;
        $$3 += (int)($$1 * 15.0F * 16.0F);
        if ($$3 > 240) {
            $$3 = 240;
        }

        return $$3 | $$4 << 16;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }
}
