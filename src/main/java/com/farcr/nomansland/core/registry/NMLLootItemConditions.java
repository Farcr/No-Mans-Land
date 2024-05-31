package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.lootmodifiers.ModLoadedLootCondition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class NMLLootItemConditions {
    public static final DeferredRegister<LootItemConditionType> LOOT_FUNCTIONS = DeferredRegister.create(BuiltInRegistries.LOOT_CONDITION_TYPE.key(), NoMansLand.MODID);

    public static final RegistryObject<LootItemConditionType> MOD_LOADED = LOOT_FUNCTIONS.register("copy_meal", () -> new LootItemConditionType(new ModLoadedLootCondition.Serializer()));
}
