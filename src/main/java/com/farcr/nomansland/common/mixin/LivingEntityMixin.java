package com.farcr.nomansland.common.mixin;

import com.farcr.nomansland.common.mixin.variants.EntityMixin;
import com.farcr.nomansland.common.mixinduck.LivingEntityDuck;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin implements LivingEntityDuck {
    @Shadow public abstract float getHealth();

    @Shadow public float yBodyRot;

    @Shadow public abstract void swing(InteractionHand hand);

    @Unique
    private boolean nomansland$skipDroppingDeathLoot = false;

    @Override
    public void noMansLand$skipDroppingDeathLoot() {
        this.nomansland$skipDroppingDeathLoot = true;
    }

    @Inject(method = "dropAllDeathLoot", at = @At("HEAD"), cancellable = true)
    private void dropAllDeathLoot(ServerLevel p_level, DamageSource damageSource, CallbackInfo ci) {
        if (this.nomansland$skipDroppingDeathLoot) {
            ci.cancel();
        }
    }
}
