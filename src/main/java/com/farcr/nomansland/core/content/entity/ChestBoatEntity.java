package com.farcr.nomansland.core.content.entity;

import com.farcr.nomansland.core.registry.NMLBlocks;
import com.farcr.nomansland.core.registry.NMLEntities;
import com.farcr.nomansland.core.registry.NMLItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.function.IntFunction;

public class ChestBoatEntity extends ChestBoat {

    public static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ChestBoatEntity.class, EntityDataSerializers.INT);

    public ChestBoatEntity(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.entityData.set(DATA_ID_TYPE, BoatEntity.Type.PINE.ordinal());
    }

    public ChestBoatEntity(Level level, double pX, double pY, double pZ) {
        this(NMLEntities.CHEST_BOAT.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return switch (getModVariant()) {
            case PINE -> NMLItems.PINE_CHEST_BOAT.get();
            case MAPLE -> NMLItems.MAPLE_CHEST_BOAT.get();
            case WALNUT -> NMLItems.WALNUT_CHEST_BOAT.get();
        };
    }

    public void setVariant(BoatEntity.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public BoatEntity.Type getModVariant() {
        return BoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
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
            this.setVariant(BoatEntity.Type.byName(pCompound.getString("Type")));
        }
    }

    public enum Type implements StringRepresentable {
        PINE(NMLBlocks.PINE_PLANKS.get(), "pine"),
        MAPLE(NMLBlocks.MAPLE_PLANKS.get(), "maple"),
        WALNUT(NMLBlocks.WALNUT_PLANKS.get(), "maple");


        public static final StringRepresentable.EnumCodec<ChestBoatEntity.Type> CODEC = StringRepresentable.fromEnum(ChestBoatEntity.Type::values);
        private static final IntFunction<ChestBoatEntity.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        private final String name;
        private final Block planks;

        Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }

        public static ChestBoatEntity.Type byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static ChestBoatEntity.Type byName(String pName) {
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
