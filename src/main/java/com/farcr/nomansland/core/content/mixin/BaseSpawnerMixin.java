package com.farcr.nomansland.core.content.mixin;

import com.farcr.nomansland.core.config.NMLConfig;
import com.farcr.nomansland.core.registry.NMLParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.BaseSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BaseSpawner.class)
public class BaseSpawnerMixin {

    // clienttick
    @ModifyArg(method = "clientTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"), index = 0)
    private ParticleOptions injected(ParticleOptions particle) {
        if (particle == ParticleTypes.FLAME && NMLConfig.MALEVOLENT_SPAWNER.get()) {
            return NMLParticleTypes.MALEVOLENT_FLAME.get();
        }
        return particle;
    }
}
