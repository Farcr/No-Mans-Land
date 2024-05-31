package com.farcr.nomansland.core.content.lootmodifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class PreventDropsModifier extends LootModifier {
    public static final Supplier<Codec<PreventDropsModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(inst -> codecStart(inst).and(Codec.BOOL.fieldOf("preventDrops").forGetter(m -> m.preventDrops)).apply(inst, PreventDropsModifier::new)));
    private final Boolean preventDrops;

    public PreventDropsModifier(LootItemCondition[] conditionsIn, Boolean preventDrops) {
        super(conditionsIn);
        this.preventDrops = preventDrops;
    }

    @Override
    public @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if(context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Monster monster && monster.wasExperienceConsumed() && monster.isPersistenceRequired() && preventDrops) generatedLoot.clear();

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
