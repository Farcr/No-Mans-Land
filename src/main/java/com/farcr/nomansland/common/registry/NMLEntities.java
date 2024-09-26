package com.farcr.nomansland.common.registry;

import com.farcr.nomansland.NoMansLand;

import com.farcr.nomansland.common.entity.BoatEntity;
import com.farcr.nomansland.common.entity.ChestBoatEntity;
import com.farcr.nomansland.common.entity.bombs.ExplosiveEntity;
import com.farcr.nomansland.common.entity.bombs.FirebombEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NMLEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, NoMansLand.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<BoatEntity>> BOAT =
            ENTITIES.register("boat", () -> EntityType.Builder.<BoatEntity>of(BoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("boat"));
    public static final DeferredHolder<EntityType<?>, EntityType<ChestBoatEntity>> CHEST_BOAT =
            ENTITIES.register("chest_boat", () -> EntityType.Builder.<ChestBoatEntity>of(ChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("chest_boat"));

    public static final DeferredHolder<EntityType<?>, EntityType<FirebombEntity>> FIREBOMB =
            ENTITIES.register("firebomb", () -> EntityType.Builder.<FirebombEntity>of(FirebombEntity::new, MobCategory.MISC)
                    .sized(0.375F, 0.375F).clientTrackingRange(4).updateInterval(20).build("fire_bomb"));

    public static final DeferredHolder<EntityType<?>, EntityType<ExplosiveEntity>> EXPLOSIVE =
            ENTITIES.register("explosive", () -> EntityType.Builder.<ExplosiveEntity>of(ExplosiveEntity::new, MobCategory.MISC)
                    .sized(0.3F, 0.3F).clientTrackingRange(4).updateInterval(20).build("explosive"));

//    public static final DeferredHolder<EntityType<?>, EntityType<BuriedEntity>> BURIED =
//            ENTITIES.register("buried", () -> EntityType.Builder.of(BuriedEntity::new, MobCategory.MONSTER)
//                    .sized(1.0f, 1.0f).clientTrackingRange(8).build("buried"));
//
//    public static final DeferredHolder<EntityType<?>, EntityType<MooseEntity>> MOOSE =
//            ENTITIES.register("moose", () -> EntityType.Builder.of(MooseEntity::new, MobCategory.CREATURE)
//                    .sized(2.5f, 2.5f).build("moose"));

}
