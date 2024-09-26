package com.farcr.nomansland.common.mixin;

import com.farcr.nomansland.NMLConfig;
import com.farcr.nomansland.common.registry.NMLParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.BaseSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BaseSpawner.class)
public class BaseSpawnerMixin {

    @ModifyArg(method = "clientTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 1), index = 0)
    private ParticleOptions injected(ParticleOptions particle) {
        if (NMLConfig.MALEVOLENT_SPAWNER.get()) {
            return NMLParticleTypes.MALEVOLENT_FLAME.get();
        }
        return particle;
    }
}
