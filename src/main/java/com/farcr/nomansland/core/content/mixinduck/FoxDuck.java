package com.farcr.nomansland.core.content.mixinduck;

import com.farcr.nomansland.core.content.entity.variant.FoxVariant;
import net.minecraft.core.Holder;

public interface FoxDuck {
    Holder<FoxVariant> noMansLand$getCustomVariant();

    void noMansLand$setCustomVariant(Holder<FoxVariant> foxVariantHolder);
}
