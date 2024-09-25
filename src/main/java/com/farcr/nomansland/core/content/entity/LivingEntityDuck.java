package com.farcr.nomansland.core.content.entity;

import com.farcr.nomansland.core.content.entity.variant.GoatVariant;
import net.minecraft.core.Holder;

public interface LivingEntityDuck {

    void nomansland$skipDroppingDeathLoot();

    Holder<GoatVariant> nomansland$getVariant();

    void nomansland$setVariant(Holder<GoatVariant> variant);
}
