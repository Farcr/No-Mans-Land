package com.farcr.nomansland.core.content.lootmodifiers;

import com.farcr.nomansland.core.registry.NMLLootItemConditions;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.fml.ModList;

public record ModLoadedLootCondition(String modId) implements LootItemCondition {
    private static final ResourceLocation NAME = ResourceLocation.fromNamespaceAndPath("nomansland", "mod_loaded");
    public static MapCodec<ModLoadedLootCondition> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance
                    .group(
                            Codec.STRING.fieldOf("modid").forGetter(ModLoadedLootCondition::modId))
                    .apply(instance, ModLoadedLootCondition::new));

    @Override
    public LootItemConditionType getType() {
        return NMLLootItemConditions.MOD_LOADED.get();
    }

    @Override
    public boolean test(LootContext context) {
        return ModList.get().isLoaded(this.modId);
    }
}
