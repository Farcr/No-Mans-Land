package com.farcr.nomansland.core.content.mixin.variants;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.entity.variant.FoxVariant;
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
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

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
        Stream<Holder.Reference<FoxVariant>> allVariants = registry.holders();
        List<Holder.Reference<FoxVariant>> possibleVariants = allVariants
                .filter((v) -> v.value().biomes().isPresent())
                .filter((v) -> v.value().biomes().get().contains(level.getBiome(this.blockPosition())))
                .toList();
        this.noMansLand$setCustomVariant(possibleVariants.isEmpty() ? registry.getHolderOrThrow(DEFAULT_VARIANT) : possibleVariants.get(random.nextInt(possibleVariants.size())));
    }

    @Override
    public Holder<FoxVariant> noMansLand$getCustomVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    @Override
    public void noMansLand$setCustomVariant(Holder<FoxVariant> foxVariantHolder) {
        this.entityData.set(DATA_VARIANT_ID, foxVariantHolder);
    }

    @Inject(method = "getBreedOffspring*", at = @At("RETURN"))
    private void getBreedOffspring(ServerLevel level, AgeableMob otherParent, CallbackInfoReturnable<AgeableMob> cir) {
        Fox fox = EntityType.FOX.create(level);
        if (fox != null) {
            ((FoxDuck)fox).noMansLand$setCustomVariant(this.random.nextBoolean() ? ((FoxDuck)this).noMansLand$getCustomVariant() : ((FoxDuck)otherParent).noMansLand$getCustomVariant());
        }
        cir.setReturnValue(fox);
    }
}
