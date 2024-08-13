package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.lootmodifiers.AddLootTableModifier;
import com.farcr.nomansland.core.content.lootmodifiers.PreventDropsModifier;
import com.mojang.serialization.Codec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class NMLLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, NoMansLand.MODID);

    public static final Supplier<Codec<? extends IGlobalLootModifier>> PREVENT_DROPS =
            LOOT_MODIFIERS.register("prevent_drops", PreventDropsModifier.CODEC);

    public static final Supplier<Codec<? extends IGlobalLootModifier>> ADD_LOOT_TABLE =
            LOOT_MODIFIERS.register("add_loot_table", AddLootTableModifier.CODEC);
}
