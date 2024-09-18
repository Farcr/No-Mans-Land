package com.farcr.nomansland.core.data.recipes;

import com.farcr.nomansland.core.registry.NMLBlocks;
import com.farcr.nomansland.core.registry.NMLItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.data.internal.NeoForgeItemTagsProvider;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BUNDLE)
                .pattern("RRR")
                .pattern("L L")
                .pattern("LLL")
                .define('R', ModItems.ROPE.get())
                .define('L', Items.LEATHER)
                .unlockedBy("has_leather", has(Items.LEATHER))
                .save(output.withConditions(FD));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.MAPLE_DOOR), Ingredient.of(ItemTags.AXES), NMLBlocks.MAPLE_PLANKS);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.MAPLE_HANGING_SIGN), Ingredient.of(ItemTags.AXES), NMLBlocks.MAPLE_PLANKS);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.MAPLE_SIGN), Ingredient.of(ItemTags.AXES), NMLBlocks.MAPLE_PLANKS);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.MAPLE_TRAPDOOR), Ingredient.of(ItemTags.AXES), NMLBlocks.MAPLE_PLANKS);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.MAPLE_WOOD), Ingredient.of(ItemTags.AXES), NMLBlocks.STRIPPED_MAPLE_WOOD)
                .addResult(ModItems.TREE_BARK.get());
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.MAPLE_LOG), Ingredient.of(ItemTags.AXES), NMLBlocks.STRIPPED_MAPLE_LOG)
                .addResult(ModItems.TREE_BARK.get());

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.PINE_DOOR), Ingredient.of(ItemTags.AXES), NMLBlocks.PINE_PLANKS);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.PINE_HANGING_SIGN), Ingredient.of(ItemTags.AXES), NMLBlocks.PINE_PLANKS);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.PINE_SIGN), Ingredient.of(ItemTags.AXES), NMLBlocks.PINE_PLANKS);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.PINE_TRAPDOOR), Ingredient.of(ItemTags.AXES), NMLBlocks.PINE_PLANKS);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.PINE_WOOD), Ingredient.of(ItemTags.AXES), NMLBlocks.STRIPPED_PINE_WOOD)
                .addResult(ModItems.TREE_BARK.get());
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.PINE_LOG), Ingredient.of(ItemTags.AXES), NMLBlocks.STRIPPED_PINE_LOG)
                .addResult(ModItems.TREE_BARK.get());

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.WALNUT_DOOR), Ingredient.of(ItemTags.AXES), NMLBlocks.WALNUT_PLANKS);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.WALNUT_HANGING_SIGN), Ingredient.of(ItemTags.AXES), NMLBlocks.WALNUT_PLANKS);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.WALNUT_SIGN), Ingredient.of(ItemTags.AXES), NMLBlocks.WALNUT_PLANKS);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.WALNUT_TRAPDOOR), Ingredient.of(ItemTags.AXES), NMLBlocks.WALNUT_PLANKS);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.WALNUT_WOOD), Ingredient.of(ItemTags.AXES), NMLBlocks.STRIPPED_WALNUT_WOOD)
                .addResult(ModItems.TREE_BARK.get());
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NMLBlocks.WALNUT_LOG), Ingredient.of(ItemTags.AXES), NMLBlocks.STRIPPED_WALNUT_LOG)
                .addResult(ModItems.TREE_BARK.get());


        conditionalRecipe(
                CookingPotRecipeBuilder.cookingPotRecipe(Items.SLIME_BALL, 1, 150, 1)
                        .addIngredient(NMLItems.RESIN.get())
                        .addIngredient(Items.KELP, 2)
                        .addIngredient(Items.CHARCOAL, 3)
                        .unlockedBy("has_resin", has(NMLItems.RESIN.get())),
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.SLIME_BALL)
                        .requires(NMLItems.RESIN.get())
                        .requires(Items.KELP, 2)
                        .requires(Items.CHARCOAL, 3)
                        .unlockedBy("has_resin", has(NMLItems.RESIN.get())),
                FD,
                output
        );

        conditionalRecipe(
                CookingPotRecipeBuilder.cookingPotRecipe(NMLItems.RESIN_OIL_BOTTLE, 1, 100, 0.25F, Items.GLASS_BOTTLE)
                        .addIngredient(NMLItems.RESIN.get(), 2)
                        .addIngredient(Items.HONEYCOMB)
                        .unlockedBy("has_resin", has(NMLItems.RESIN.get())),
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, NMLItems.RESIN_OIL_BOTTLE)
                        .requires(NMLItems.RESIN.get(), 2)
                        .requires(Items.HONEYCOMB)
                        .requires(Items.GLASS_BOTTLE)
                        .unlockedBy("has_resin", has(NMLItems.RESIN.get())),
                FD,
                output
        );

        conditionalRecipe(
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NMLItems.FIREBOMB, 2)
                        .pattern("  R")
                        .pattern("GP ")
                        .pattern("GG")
                        .define('R', ModItems.ROPE.get())
                        .define('G', Items.GUNPOWDER)
                        .define('P', NMLItems.RESIN.get())
                        .unlockedBy("has_gunpowder", has(Items.GUNPOWDER)),
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NMLItems.FIREBOMB, 2)
                        .pattern("  R")
                        .pattern("GP ")
                        .pattern("GG")
                        .define('R', Items.STRING)
                        .define('G', Items.GUNPOWDER)
                        .define('P', NMLItems.RESIN.get())
                        .unlockedBy("has_gunpowder", has(Items.GUNPOWDER)),
                FD,
                output
                );
    }

    private void conditionalRecipe(RecipeBuilder mainRecipe, RecipeBuilder fallbackRecipe, ICondition condition, RecipeOutput output) {
        mainRecipe.save(output.withConditions(condition));
        fallbackRecipe.save(output.withConditions(new NotCondition(condition)));
    }
}

