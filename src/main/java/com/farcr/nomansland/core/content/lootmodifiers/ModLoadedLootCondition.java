package com.farcr.nomansland.core.content.lootmodifiers;

import com.farcr.nomansland.core.registry.NMLLootItemConditions;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.fml.ModList;

public record ModLoadedLootCondition(String modId) implements LootItemCondition {
    public static final Codec<ModLoadedLootCondition> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(Codec.STRING.fieldOf("mod_id").forGetter((m) -> m.modId))
                    .apply(instance, ModLoadedLootCondition::new)
    );
    private static final ResourceLocation NAME = new ResourceLocation("nomansland", "mod_loaded");

    @Override
    public LootItemConditionType getType() {
        return NMLLootItemConditions.MOD_LOADED.get();
    }

    @Override
    public boolean test(LootContext context) {
        return ModList.get().isLoaded(this.modId);
    }
}
