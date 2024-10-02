package com.farcr.nomansland.common.mixinduck;

import com.farcr.nomansland.common.entity.variant.CowVariant;
import com.farcr.nomansland.common.entity.variant.FoxVariant;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.state.BlockState;

public interface MooshroomDuck {
    Holder<CowVariant> noMansLand$getCustomVariant();

    BlockState noMansLand$getMushroomBlock(Holder<CowVariant> mooshroomVariantHolder);

    void noMansLand$setCustomVariant(Holder<CowVariant> mooshroomVariantHolder);

    boolean noMansLand$isMooshroomVariant(Holder<CowVariant> cowVariantHolder);
}
