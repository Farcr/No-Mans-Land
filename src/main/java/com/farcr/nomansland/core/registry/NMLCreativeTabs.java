package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class NMLCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NoMansLand.MODID);

    public static final RegistryObject<CreativeModeTab> NO_MANS_TAB = CREATIVE_TABS.register(NoMansLand.MODID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.nomansland"))
                    .icon(() -> new ItemStack(NMLItems.NO_MANS_GLOBE.get()))
                    .displayItems((parameters, output) -> NMLItems.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
                    .displayItems((parameters, output) -> NMLBlocks.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
                    .build());

}
