package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.blockentity.NMLSignBlockEntity;
import com.farcr.nomansland.core.content.blockentity.TapBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NMLBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, NoMansLand.MODID);

    public static final RegistryObject<BlockEntityType<NMLSignBlockEntity>> NML_SIGN =
            BLOCK_ENTITIES.register("nml_sign", () ->
                    BlockEntityType.Builder.of(NMLSignBlockEntity::new,
                            NMLBlocks.PINE_SIGN.get(), NMLBlocks.PINE_WALL_SIGN.get(),
                            NMLBlocks.MAPLE_SIGN.get(), NMLBlocks.MAPLE_WALL_SIGN.get(),
                            NMLBlocks.WALNUT_SIGN.get(), NMLBlocks.WALNUT_WALL_SIGN.get()
                            ).build(null));

    public static final RegistryObject<BlockEntityType<NMLSignBlockEntity>> NML_HANGING_SIGN =
            BLOCK_ENTITIES.register("nml_hanging_sign", () ->
                    BlockEntityType.Builder.of(NMLSignBlockEntity::new,
                            NMLBlocks.PINE_HANGING_SIGN.get(), NMLBlocks.PINE_HANGING_WALL_SIGN.get(),
                            NMLBlocks.MAPLE_HANGING_SIGN.get(), NMLBlocks.MAPLE_HANGING_WALL_SIGN.get(),
                            NMLBlocks.WALNUT_HANGING_SIGN.get(), NMLBlocks.WALNUT_HANGING_WALL_SIGN.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TapBlockEntity>> TAP =
            BLOCK_ENTITIES.register("tap", () ->
                    BlockEntityType.Builder.of(TapBlockEntity::new, NMLBlocks.TAP.get()).build(null));

    public static void register (IEventBus eventBus) {BLOCK_ENTITIES.register(eventBus);}
}
