package com.farcr.nomansland.common.registry;

import com.farcr.nomansland.NoMansLand;
import com.farcr.nomansland.common.blockentity.MonsterAnchorBlockEntity;
import com.farcr.nomansland.common.blockentity.NMLSignBlockEntity;
import com.farcr.nomansland.common.blockentity.RemainsBlockEntity;
import com.farcr.nomansland.common.blockentity.TapBlockEntity;
import com.farcr.nomansland.integration.Mods;
import com.farcr.nomansland.integration.farmersdelight.FDIntegration;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NMLBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, NoMansLand.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NMLSignBlockEntity>> NML_SIGN =
            BLOCK_ENTITIES.register("nml_sign", () ->
                    BlockEntityType.Builder.of(NMLSignBlockEntity::new,
                            NMLBlocks.PINE_SIGN.get(), NMLBlocks.PINE_WALL_SIGN.get(),
                            NMLBlocks.MAPLE_SIGN.get(), NMLBlocks.MAPLE_WALL_SIGN.get(),
                            NMLBlocks.WALNUT_SIGN.get(), NMLBlocks.WALNUT_WALL_SIGN.get(),
                            NMLBlocks.WILLOW_SIGN.get(), NMLBlocks.WILLOW_WALL_SIGN.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NMLSignBlockEntity>> NML_HANGING_SIGN =
            BLOCK_ENTITIES.register("nml_hanging_sign", () ->
                    BlockEntityType.Builder.of(NMLSignBlockEntity::new,
                            NMLBlocks.PINE_HANGING_SIGN.get(), NMLBlocks.PINE_HANGING_WALL_SIGN.get(),
                            NMLBlocks.MAPLE_HANGING_SIGN.get(), NMLBlocks.MAPLE_HANGING_WALL_SIGN.get(),
                            NMLBlocks.WALNUT_HANGING_SIGN.get(), NMLBlocks.WALNUT_HANGING_WALL_SIGN.get(),
                            NMLBlocks.WILLOW_HANGING_SIGN.get(), NMLBlocks.WILLOW_HANGING_WALL_SIGN.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TapBlockEntity>> TAP =
            BLOCK_ENTITIES.register("tap", () ->
                    BlockEntityType.Builder.of(TapBlockEntity::new, NMLBlocks.TAP.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MonsterAnchorBlockEntity>> MONSTER_ANCHOR =
            BLOCK_ENTITIES.register("monster_anchor", () ->
                    BlockEntityType.Builder.of(MonsterAnchorBlockEntity::new, NMLBlocks.MONSTER_ANCHOR.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RemainsBlockEntity>> REMAINS =
            BLOCK_ENTITIES.register("remains", () ->
                    BlockEntityType.Builder.of(RemainsBlockEntity::new, NMLBlocks.REMAINS.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> CABINET = Mods.FARMERSDELIGHT.isLoaded() ?
            BLOCK_ENTITIES.register("nml_cabinets", FDIntegration.cabinetBlockEntity()) : null;
}
