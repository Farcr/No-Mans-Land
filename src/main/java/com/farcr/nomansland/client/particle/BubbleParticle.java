package com.farcr.nomansland.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;

public class BubbleParticle extends net.minecraft.client.particle.BubbleParticle {
    private final ParticleOptions popParticle;
    public BubbleParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet spriteSet, ParticleOptions popParticle) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.setSprite(spriteSet.get(1, 1));
        this.popParticle = popParticle;
        this.lifetime = (int)(64 / (Math.random() * 0.8 + 0.2));
    }

    @Override
    public void tick() {
        if (this.lifetime-- <= 0) {
            float offset = random.nextInt(-10, 10) * 0.01F;
            if (popParticle != null) this.level.addParticle(popParticle, this.x + (offset * Math.random()), this.y, this.z + (offset * Math.random()), 0.0, 0.0, 0.0);
            float volume = Mth.randomBetween(this.random, 0.3F, 1.0F);
            this.level.playLocalSound(this.x, this.y, this.z, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundSource.BLOCKS, volume, 0.7F, false);
            this.remove();
        }
//        super.tick();
    }
}
