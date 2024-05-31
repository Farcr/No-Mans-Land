package com.farcr.nomansland.core.content.lootmodifiers;

import com.farcr.nomansland.core.registry.NMLLootItemConditions;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.fml.ModList;

public class ModLoadedLootCondition implements LootItemCondition {
    private static final ResourceLocation NAME = new ResourceLocation("nomansland", "mod_loaded");
    private final String modId;

    public ModLoadedLootCondition(String modId) {
        this.modId = modId;
    }

    @Override
    public LootItemConditionType getType() {
        return NMLLootItemConditions.MOD_LOADED.get();
    }

    @Override
    public boolean test(LootContext context) {
        return ModList.get().isLoaded(this.modId);
    }

    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<ModLoadedLootCondition> {
        public Serializer() {
        }

        public void serialize(JsonObject pJson, ModLoadedLootCondition pValue, JsonSerializationContext pSerializationContext) {
            pJson.add("mod_id", pSerializationContext.serialize(pValue.modId));
        }

        public ModLoadedLootCondition deserialize(JsonObject json, JsonDeserializationContext pSerializationContext) {
            return new ModLoadedLootCondition(GsonHelper.getAsString(json, "mod_id"));
        }
    }
}
