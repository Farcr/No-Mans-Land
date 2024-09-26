package com.farcr.nomansland.common.mixinduck;

import com.farcr.nomansland.common.entity.variant.FoxVariant;
import net.minecraft.core.Holder;

public interface FoxDuck {
    Holder<FoxVariant> noMansLand$getCustomVariant();

    void noMansLand$setCustomVariant(Holder<FoxVariant> foxVariantHolder);
}
