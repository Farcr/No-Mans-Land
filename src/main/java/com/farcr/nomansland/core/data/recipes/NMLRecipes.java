package com.farcr.nomansland.core.data.recipes;

import com.farcr.nomansland.core.registry.NMLBlocks;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class NMLRecipes extends RecipeProvider {
    public NMLRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    private final ModLoadedCondition FD = new ModLoadedCondition("farmersdelight");

    @Override
    protected void buildRecipes(RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NMLBlocks.WOODEN_SCAFFOLDING)
                .pattern("XYX")
                .pattern("X X")
                .pattern("X X")
                .define('X', Items.STICK)
                .define('Y', Items.STRING)
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .save(output.withConditions(FD));
    }

    private void conditionalRecipe(RecipeBuilder mainRecipe, RecipeBuilder fallbackRecipe, ICondition condition, RecipeOutput output) {
        mainRecipe.save(output.withConditions(condition));
        fallbackRecipe.save(output.withConditions(new NotCondition(condition)));
    }
}

