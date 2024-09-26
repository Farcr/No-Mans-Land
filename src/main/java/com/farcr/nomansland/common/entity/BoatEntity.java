package com.farcr.nomansland.common.entity;

import com.farcr.nomansland.common.registry.NMLBlocks;
import com.farcr.nomansland.common.registry.NMLEntities;
import com.farcr.nomansland.common.registry.NMLItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.function.IntFunction;

public class BoatEntity extends Boat {
    public static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(BoatEntity.class, EntityDataSerializers.INT);

    public BoatEntity(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.entityData.set(DATA_ID_TYPE, Type.PINE.ordinal());
    }

    public BoatEntity(Level level, double pX, double pY, double pZ) {
        this(NMLEntities.BOAT.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return switch (getModVariant()) {
            case PINE -> NMLItems.PINE_BOAT.get();
            case MAPLE -> NMLItems.MAPLE_BOAT.get();
            case WALNUT -> NMLItems.WALNUT_BOAT.get();
            case WILLOW -> NMLItems.WILLOW_BOAT.get();
        };
    }

    public void setVariant(Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public Type getModVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_TYPE, 0);
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(Type.byName(pCompound.getString("Type")));
        }
    }

    public enum Type implements StringRepresentable {
        PINE(NMLBlocks.PINE_PLANKS.get(), "pine"),
        MAPLE(NMLBlocks.MAPLE_PLANKS.get(), "maple"),
        WALNUT(NMLBlocks.WALNUT_PLANKS.get(), "walnut"),
        WILLOW(NMLBlocks.WILLOW_PLANKS.get(), "willow");

        public static final StringRepresentable.EnumCodec<BoatEntity.Type> CODEC = StringRepresentable.fromEnum(BoatEntity.Type::values);
        private static final IntFunction<Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        private final String name;
        private final Block planks;

        Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }

        public static BoatEntity.Type byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static BoatEntity.Type byName(String pName) {
            return CODEC.byName(pName, PINE);
        }

        public String getSerializedName() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }
    }
}


