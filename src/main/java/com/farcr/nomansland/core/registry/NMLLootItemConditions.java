package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.lootmodifiers.ModLoadedLootCondition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NMLLootItemConditions {
    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITIONS = DeferredRegister.create(BuiltInRegistries.LOOT_CONDITION_TYPE, NoMansLand.MODID);

    public static final DeferredHolder<LootItemConditionType, LootItemConditionType> MOD_LOADED = LOOT_CONDITIONS.register("mod_loaded", () -> new LootItemConditionType(ModLoadedLootCondition.CODEC));
}
