package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.blockentity.MonsterAnchorBlockEntity;
import com.farcr.nomansland.core.content.blockentity.NMLSignBlockEntity;
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
                            NMLBlocks.WALNUT_SIGN.get(), NMLBlocks.WALNUT_WALL_SIGN.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NMLSignBlockEntity>> NML_HANGING_SIGN =
            BLOCK_ENTITIES.register("nml_hanging_sign", () ->
                    BlockEntityType.Builder.of(NMLSignBlockEntity::new,
                            NMLBlocks.PINE_HANGING_SIGN.get(), NMLBlocks.PINE_HANGING_WALL_SIGN.get(),
                            NMLBlocks.MAPLE_HANGING_SIGN.get(), NMLBlocks.MAPLE_HANGING_WALL_SIGN.get(),
                            NMLBlocks.WALNUT_HANGING_SIGN.get(), NMLBlocks.WALNUT_HANGING_WALL_SIGN.get()
                    ).build(null));

//    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TapBlockEntity>> TAP =
//            BLOCK_ENTITIES.register("tap", () ->
//                    BlockEntityType.Builder.of(TapBlockEntity::new, NMLBlocks.TAP.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MonsterAnchorBlockEntity>> MONSTER_ANCHOR =
            BLOCK_ENTITIES.register("monster_anchor", () ->
                    BlockEntityType.Builder.of(MonsterAnchorBlockEntity::new, NMLBlocks.MONSTER_ANCHOR.get()).build(null));
}
