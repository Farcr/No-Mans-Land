package com.farcr.nomansland.common.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Set;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Shadow public abstract double getY();

    @Shadow public abstract double getY(double scale);

    @Shadow public abstract boolean isInvulnerable();

    @Shadow @Nullable public abstract ItemEntity spawnAtLocation(ItemStack stack, float offsetY);

    @Shadow public abstract float getBbHeight();


    @Shadow public abstract void discard();

    @Shadow public abstract float getXRot();

    @Shadow public abstract float getYRot();

    @Shadow public abstract double getZ();


    @Shadow public abstract double getX();

    @Shadow public abstract boolean hasCustomName();

    @Shadow public abstract boolean isCustomNameVisible();

    @Shadow @Nullable public abstract Component getCustomName();

    @Shadow public abstract Level level();

    @Shadow public abstract EntityType<?> getType();

    @Shadow @Final protected RandomSource random;

    @Shadow public abstract BlockPos blockPosition();

    @Shadow public abstract RegistryAccess registryAccess();

    @Shadow @Final
    protected SynchedEntityData entityData;

    @Shadow private EntityDimensions dimensions;

    @Shadow public abstract EntityDimensions getDimensions(Pose pose);

    @Shadow public abstract Pose getPose();

    @Shadow public abstract boolean removeTag(String tag);

    @Shadow public abstract Set<String> getTags();

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType entityType, Level level, CallbackInfo ci) {
        this.dimensions = this.getDimensions(this.getPose());
    }
}
