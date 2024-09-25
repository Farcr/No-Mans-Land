package com.farcr.nomansland.core.content.mixin.variants;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.entity.variant.FoxVariant;
import com.farcr.nomansland.core.content.mixinduck.FoxDuck;
import com.farcr.nomansland.core.registry.NMLDataSerializers;
import com.farcr.nomansland.core.registry.NMLVariants;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.Optional;

@Mixin(Fox.class)
public abstract class FoxMixin extends MobMixin implements FoxDuck {

    @Unique
    private static final EntityDataAccessor<Holder<FoxVariant>> DATA_VARIANT_ID = SynchedEntityData.defineId(Fox.class, NMLDataSerializers.FOX_VARIANT.get());
    @Unique
    private static final String VARIANT_KEY = "variant";
    @Unique
    private static final ResourceKey<FoxVariant> DEFAULT_VARIANT = ResourceKey.create(NMLVariants.FOX_VARIANT_KEY, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "default"));

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(DATA_VARIANT_ID, this.registryAccess().registryOrThrow(NMLVariants.FOX_VARIANT_KEY).getHolderOrThrow(DEFAULT_VARIANT));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        this.noMansLand$getCustomVariant().unwrapKey().ifPresent((variant) -> {
            compound.putString(VARIANT_KEY, variant.location().toString());
        });
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        Optional.ofNullable(ResourceLocation.tryParse(compound.getString(VARIANT_KEY)))
                .map((string) -> ResourceKey.create(NMLVariants.FOX_VARIANT_KEY, string))
                .flatMap((variant) -> this.registryAccess().registryOrThrow(NMLVariants.FOX_VARIANT_KEY).getHolder(variant))
                .ifPresent(this::noMansLand$setCustomVariant);
    }

    @Override
    protected void finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        Registry<FoxVariant> registry = this.registryAccess().registryOrThrow(NMLVariants.FOX_VARIANT_KEY);
        Optional<Holder.Reference<FoxVariant>> variant = registry.holders()
                .filter((v) -> v.value().biomes().contains(level.getBiome(this.blockPosition()))).findFirst()
                .or(() -> registry.getHolder(DEFAULT_VARIANT));
        Objects.requireNonNull(registry);
        this.noMansLand$setCustomVariant(variant.or(registry::getAny).orElseThrow());
    }

    @Override
    public Holder<FoxVariant> noMansLand$getCustomVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    @Override
    public void noMansLand$setCustomVariant(Holder<FoxVariant> foxVariantHolder) {
        this.entityData.set(DATA_VARIANT_ID, foxVariantHolder);
    }
}
