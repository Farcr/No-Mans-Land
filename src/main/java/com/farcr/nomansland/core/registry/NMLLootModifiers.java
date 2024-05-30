package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.lootmodifiers.AddLootTableModifier;
import com.farcr.nomansland.core.content.lootmodifiers.PreventDropsModifier;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditions;
import net.minecraftforge.common.data.ForgeLootTableProvider;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NMLLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, NoMansLand.MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_LOOT_TABLE =
            LOOT_MODIFIER_SERIALIZERS.register("add_loot_table", AddLootTableModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> PREVENT_DROPS =
            LOOT_MODIFIER_SERIALIZERS.register("prevent_drops", PreventDropsModifier.CODEC);

    //TODO: ADD MOD LOADED CONDITION TO LOOT MODIFIER
    //TODO: ABILITY TO ADD DROPS TO ENTITIES TOO (MAY ALREADY BE A THING?)


    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
