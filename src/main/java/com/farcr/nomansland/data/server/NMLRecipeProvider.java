package com.farcr.nomansland.data.server;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

public class NMLRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public NMLRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        //Pebbles
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, NMLBlocks.PEBBLES.get(), 3)
//                .requires(Tags.Items.COBBLESTONE)
//                .unlockedBy("has_cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLESTONE))
//                .save(consumer);
//        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.COBBLED_DEEPSLATE)
//                .pattern("#D")
//                .pattern("D#")
//                .define('#', NMLBlocks.PEBBLES.get())
//                .define('D', Blocks.DEEPSLATE)
//                .unlockedBy("has_pebbles", InventoryChangeTrigger.TriggerInstance.hasItems(NMLBlocks.PEBBLES.get()))
//                .save(consumer, new ResourceLocation(NoMansLand.ID, "cobbled_deepslate_from_pebbles"));
    }

//    protected static void oreSmelting(RecipeOutput p_250654_, List<ItemLike> p_250172_, RecipeCategory p_250588_, ItemLike p_251868_, float p_250789_, int p_252144_, String p_251687_) {
//        oreCooking(p_250654_, RecipeSerializer.SMELTING_RECIPE, p_250172_, p_250588_, p_251868_, p_250789_, p_252144_, p_251687_, "_from_smelting");
//    }
//
//    protected static void oreCooking(RecipeOutput p_250791_, RecipeSerializer<? extends AbstractCookingRecipe> p_251817_, List<ItemLike> p_249619_, RecipeCategory p_251154_, ItemLike p_250066_, float p_251871_, int p_251316_, String p_251450_, String p_249236_) {
//        for (ItemLike itemlike : p_249619_) {
//            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), p_251154_, p_250066_, p_251871_, p_251316_, p_251817_).group(p_251450_)
//                    .unlockedBy(getHasName(itemlike), has(itemlike)).save(p_250791_, new ResourceLocation(NoMansLand.ID, getItemName(p_250066_)) + p_249236_ + "_" + getItemName(itemlike));
//        }
//    }
}
