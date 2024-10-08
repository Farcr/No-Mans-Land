package com.farcr.nomansland.common.mixin.variants;

import com.farcr.nomansland.NoMansLand;
import com.farcr.nomansland.common.entity.variant.CodVariant;
import com.farcr.nomansland.common.mixin.MobMixin;
import com.farcr.nomansland.common.registry.NMLDataSerializers;
import com.farcr.nomansland.common.registry.NMLVariants;
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
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;

@Mixin(Cod.class)
public abstract class CodMixin extends MobMixin implements VariantHolder<Holder<CodVariant>> {

    @Unique
    private static final EntityDataAccessor<Holder<CodVariant>> DATA_VARIANT_ID = SynchedEntityData.defineId(Cod.class, NMLDataSerializers.COD_VARIANT.get());
    @Unique
    private static final String VARIANT_KEY = "variant";
    @Unique
    private static final ResourceKey<CodVariant> DEFAULT_VARIANT = ResourceKey.create(NMLVariants.COD_VARIANT_KEY, ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, "default"));

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(DATA_VARIANT_ID, this.registryAccess().registryOrThrow(NMLVariants.COD_VARIANT_KEY).getHolderOrThrow(DEFAULT_VARIANT));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        this.getVariant().unwrapKey().ifPresent((variant) -> {
            compound.putString(VARIANT_KEY, variant.location().toString());
        });
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        Optional.ofNullable(ResourceLocation.tryParse(compound.getString(VARIANT_KEY)))
                .map((string) -> ResourceKey.create(NMLVariants.COD_VARIANT_KEY, string))
                .flatMap((variant) -> this.registryAccess().registryOrThrow(NMLVariants.COD_VARIANT_KEY).getHolder(variant))
                .ifPresent(this::setVariant);
    }

    @Override
    protected void finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        if (spawnType != MobSpawnType.BUCKET) {
            Registry<CodVariant> registry = this.registryAccess().registryOrThrow(NMLVariants.COD_VARIANT_KEY);
            List<Holder.Reference<CodVariant>> possibleVariants = registry.holders()
                    .filter((v) -> v.value().biomes().isPresent() && v.value().biomes().get().contains(level.getBiome(this.blockPosition())))
                    .toList();
            List<Holder.Reference<CodVariant>> defaultVariants = registry.holders()
                    .filter((v) -> v.value().biomes().isEmpty() || v.is(DEFAULT_VARIANT))
                    .toList();
            this.setVariant(possibleVariants.isEmpty() ? defaultVariants.get(random.nextInt(defaultVariants.size())) : possibleVariants.get(random.nextInt(possibleVariants.size())));
        }
    }

    @Override
    public Holder<CodVariant> getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    @Override
    public void setVariant(Holder<CodVariant> variantHolder) {
        this.entityData.set(DATA_VARIANT_ID, variantHolder);
    }
}
