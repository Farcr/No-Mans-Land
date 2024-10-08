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
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(AbstractFish.class)
public abstract class AbstractFishMixin extends EntityMixin implements VariantHolder<Holder<?>> {

    @Inject(method = "saveToBucketTag", at = @At("TAIL"))
    private void saveToBucketTag(ItemStack stack, CallbackInfo ci) {
        if (this.getTags().contains("variant")) {
            CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, (data) -> {
                this.getVariant().unwrapKey().ifPresent((variant) -> {
                    data.putString("variant", variant.location().toString());
                });
            });
        }
    }

    @Inject(method = "loadFromBucketTag", at = @At("TAIL"))
    private void loadFromBucketTag(CompoundTag tag, CallbackInfo ci) {
            if (tag.contains("variant")) {
                Optional<Registry<Object>> optionalRegistry = this.registryAccess().registry(NMLVariants.getVariantOfType(this.getType()));
                if (optionalRegistry.isPresent()) {
                    Registry<Object> registry = optionalRegistry.get();
                    this.setVariant(registry.holders().filter(v -> v.unwrapKey().get().location().toString().equals(tag.getString("variant"))).findAny().get());
                }
            }
    }
}
