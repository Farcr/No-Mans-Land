package com.farcr.nomansland.core.content.mixin.variants;


import com.farcr.nomansland.core.registry.NMLTags;
import com.farcr.nomansland.core.registry.NMLVariants;
import net.minecraft.core.Holder;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.FrogVariant;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Frog.class)
public abstract class FrogMixin extends EntityMixin {
    @Shadow
    public abstract void setVariant(Holder<FrogVariant> variant);

    @Inject(at = @At("TAIL"), method = "finalizeSpawn")
    private void finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, SpawnGroupData pSpawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        Holder<Biome> holder = pLevel.getBiome(this.blockPosition());
        if (holder.is(NMLTags.SPAWNS_MUD_FROGS)) setVariant(NMLVariants.MUD);
    }
}
