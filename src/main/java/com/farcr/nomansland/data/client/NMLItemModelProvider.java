package com.farcr.nomansland.data.client;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class NMLItemModelProvider extends ItemModelProvider {
    public NMLItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NoMansLand.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
//     simplePineBlockItem("pine_planks");
//     simplePineBlockItem("pine_stairs");
//     simplePineBlockItem("pine_slab");
//     simplePineBlockItem("pine_leaves");
//     simplePineBlockItem("pine_log");
//     simplePineBlockItem("pine_wood");
//     simplePineBlockItem("stripped_pine_log");
//     simplePineBlockItem("stripped_pine_wood");
//     simplePineBlockItem("pine_button_inventory");
//     simplePineBlockItem("pine_fence_inventory");
//     simplePineBlockItem("pine_fence_gate");
//     simplePineBlockItem("pine_pressure_plate");
//     simplePineBlockItem("trimmed_pine_planks");
     //simplePineBlockItem("pine_trapdoor_bottom");
    }

    private ItemModelBuilder simpleItem(String parent) {
        return withExistingParent(parent,
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(NoMansLand.MODID, "item/" + parent));
    }

    private ItemModelBuilder simpleBlockTextureItem(String parent) {
        return withExistingParent(parent,
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(NoMansLand.MODID, "block/" + parent));
    }

    private ItemModelBuilder simpleBlockItem(String parent) {
        return withExistingParent(parent,
                new ResourceLocation("nomansland:block/" + parent));
    }
    private ItemModelBuilder simplePineBlockItem(String parent) {
        return withExistingParent(parent,
                new ResourceLocation("nomansland:block/pine/" + parent));
    }

    private ItemModelBuilder spawnEggItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("minecraft:item/template_spawn_egg"));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(NoMansLand.MODID, "item/" + item.getId().getPath()));
    }

}
