package com.farcr.nomansland.core.content.mixin.variants;

import com.farcr.nomansland.core.content.entity.LivingEntityDuck;
import com.farcr.nomansland.core.content.entity.variant.GoatVariant;
import com.farcr.nomansland.core.content.entity.variant.GoatVariants;
import com.farcr.nomansland.core.registry.NMLDataSerializers;
import com.farcr.nomansland.core.registry.NMLVariants;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(Goat.class)
public abstract class GoatMixin extends MobMixin implements LivingEntityDuck {

    @Unique
    private static final EntityDataAccessor<Holder<GoatVariant>> DATA_VARIANT_ID = SynchedEntityData.defineId(Goat.class, NMLDataSerializers.GOAT_VARIANT.get());
    @Unique
    private static final String VARIANT_KEY = "variant";
    @Unique
    private static final ResourceKey<GoatVariant> DEFAULT_VARIANT = GoatVariants.DEFAULT;

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(DATA_VARIANT_ID, this.registryAccess().registryOrThrow(NMLVariants.GOAT_VARIANT_KEY).getHolderOrThrow(DEFAULT_VARIANT));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        this.nomansland$getVariant().unwrapKey().ifPresent((variant) -> {
            compound.putString(VARIANT_KEY, variant.location().toString());
        });
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        Optional.ofNullable(ResourceLocation.tryParse(compound.getString(VARIANT_KEY)))
                .map((string) -> ResourceKey.create(NMLVariants.GOAT_VARIANT_KEY, string))
                .flatMap((p_352803_) -> this.registryAccess().registryOrThrow(NMLVariants.GOAT_VARIANT_KEY).getHolder(p_352803_))
                .ifPresent(this::nomansland$setVariant);
    }

    @Override
    protected void finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        this.nomansland$setVariant(GoatVariants.getSpawnVariant(this.registryAccess(), level.getBiome(this.blockPosition())));
    }

    @Override
    public Holder<GoatVariant> nomansland$getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    @Override
    public void nomansland$setVariant(Holder<GoatVariant> variant) {
        this.entityData.set(DATA_VARIANT_ID, variant);
    }
}
