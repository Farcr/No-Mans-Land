package com.farcr.nomansland.common.mixin.variants;

import com.farcr.nomansland.common.entity.variant.SalmonVariant;
import com.farcr.nomansland.common.mixin.EntityMixin;
import com.farcr.nomansland.common.registry.NMLVariants;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFish.class)
public abstract class AbstractFishMixin extends EntityMixin implements VariantHolder<Holder<?>> {
    @Inject(method = "saveToBucketTag", at = @At("TAIL"))
    private void saveToBucketTag(ItemStack stack, CallbackInfo ci) {
        if (this.getType() == EntityType.SALMON) {
            CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, (data) -> {
                this.getVariant().unwrapKey().ifPresent((variant) -> {
                    data.putString("bucketVariant", variant.location().toString());
                });
            });
        }
    }

    @Inject(method = "loadFromBucketTag", at = @At("TAIL"))
    private void loadFromBucketTag(CompoundTag tag, CallbackInfo ci) {
        if (this.getType() == EntityType.SALMON) {
            if (tag.contains("variant")) {
                Registry<SalmonVariant> registry = this.registryAccess().registryOrThrow(NMLVariants.SALMON_VARIANT_KEY);
                this.setVariant(registry.holders().filter(v -> v.unwrapKey().get().location().toString().equals(tag.getString("bucketVariant"))).findAny().get());
            }
        }
    }
}
