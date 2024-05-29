package com.farcr.nomansland.core.content.blockentity;

import com.farcr.nomansland.core.content.block.MonsterAnchorBlock;
import com.farcr.nomansland.core.registry.NMLBlockEntities;
import com.farcr.nomansland.core.registry.NMLParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MonsterAnchorBlockEntity extends BlockEntity implements GameEventListener.Holder<MonsterAnchorBlockEntity.AnchorListener> {

    private final AnchorListener anchorListener;
    public final List<LivingEntity> entityQueue;
    public int timeResurrecting;
    public int timeIdle;
    public MonsterAnchorBlockEntity(BlockPos pos, BlockState state) {
        super(NMLBlockEntities.MONSTER_ANCHOR.get(), pos, state);
        this.anchorListener = new AnchorListener(state, new BlockPositionSource(pos));
        this.entityQueue = new ArrayList<>();
        this.timeResurrecting = 0;
        this.timeIdle = 0;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MonsterAnchorBlockEntity monsterAnchor) {

        ServerLevel serverLevel = (ServerLevel) level;
        List<LivingEntity> entityQueue = monsterAnchor.entityQueue;
        boolean empty = entityQueue.isEmpty();
        RandomSource random = level.random;

        if (empty) {
            // Start counting ticks
            monsterAnchor.timeResurrecting = 0;
            monsterAnchor.timeIdle++;

            // Deactivate the spawner after 10 seconds of inactivity
            if (state.getValue(MonsterAnchorBlock.ACTIVE)) {
                    level.playSound(null, pos, SoundEvents.BEACON_DEACTIVATE, SoundSource.BLOCKS, 1, 0.75F);
                    level.setBlockAndUpdate(pos, state.setValue(MonsterAnchorBlock.ACTIVE, false));
            }
            return;
        }

        // Code only continues of there is an entity in the queue

        // Start counting ticks
        monsterAnchor.timeIdle = 0;
        monsterAnchor.timeResurrecting++;

        // While it is resurrecting, these particles will always spawn with a particle count proportional to the progress of the current resurrection, thus playing in a loop
        serverLevel.sendParticles((ParticleOptions) NMLParticleTypes.MALEVOLENT_FLAME.get(),
                pos.getX()+random.nextFloat(),
                pos.getY()+random.nextFloat(),
                pos.getZ()+random.nextFloat(),
                monsterAnchor.timeResurrecting, 0, 0, 0, 0.0);

        // Loop through all the entities in the queue
        for (int i = 0; i < entityQueue.size(); i++) {
            Vec3 spawningPosition = entityQueue.get(i).getPosition(0);

            // Spawn particles at the entity death location will stay until they are out of the queue

            if (level.random.nextFloat() <= 0.1F) serverLevel.sendParticles((ParticleOptions) NMLParticleTypes.MALEVOLENT_FLAME.get(),
                    spawningPosition.x+random.nextFloat() - random.nextFloat(),
                    spawningPosition.y+random.nextFloat() - random.nextFloat(),
                    spawningPosition.z+random.nextFloat() - random.nextFloat(),
                    3, 0,0,0,0);

            // Select the first entity on the list
            if (i == 0) {
                // This sound plays a bit late, so it is played before the mob is resurrected to time it perfectly
                if (monsterAnchor.timeResurrecting <= 2) {
                    // Turn the block active on mob resurrection
                    if (!state.getValue(MonsterAnchorBlock.ACTIVE)) {
                        level.setBlockAndUpdate(pos, state.setValue(MonsterAnchorBlock.ACTIVE, true));
                        level.playSound(null, pos, SoundEvents.BEACON_POWER_SELECT, SoundSource.BLOCKS, 1, 0.25F);
                    }
                    level.playSound(null, pos, SoundEvents.BELL_RESONATE, SoundSource.BLOCKS, 1, 0.5F);
                }
                if (monsterAnchor.timeResurrecting == 80) {
                    monsterAnchor.timeResurrecting = 0;
                    // Resurrect the entity
                    LivingEntity resurrectedEntity = (LivingEntity) entityQueue.get(i).getType().create(level);
                    if (resurrectedEntity != null) {
                        if (resurrectedEntity instanceof AgeableMob ageableMob) ageableMob.setBaby(entityQueue.get(i).isBaby());
                        resurrectedEntity.moveTo(spawningPosition.x, spawningPosition.y, spawningPosition.z);
                        level.addFreshEntity(resurrectedEntity);
                        entityQueue.remove(i);
                        for(int p = 0; p <= 20; p++) {
                            serverLevel.sendParticles(ParticleTypes.SMOKE,
                                    pos.getX()+random.nextFloat(),
                                    pos.getY()+1,
                                    pos.getZ()+random.nextFloat(),
                                    5, 0, 0,0,0);
                            serverLevel.sendParticles((ParticleOptions) NMLParticleTypes.MALEVOLENT_FLAME.get(),
                                    spawningPosition.x+random.nextFloat() - random.nextFloat(),
                                    spawningPosition.y+1+random.nextFloat() - random.nextFloat(),
                                    spawningPosition.z+random.nextFloat() - random.nextFloat(),
                                    3, 0,0,0,0.2);
                        }
                    }
                }
            }
        }
    }

    public AnchorListener getListener() {
        return this.anchorListener;
    }

    public static class AnchorListener implements GameEventListener {
        private final BlockState state;
        private final PositionSource positionSource;


        public AnchorListener(BlockState pBlockState, PositionSource pPositionSource) {
            this.state = pBlockState;
            this.positionSource = pPositionSource;
        }

        public PositionSource getListenerSource() {
            return this.positionSource;
        }

        public int getListenerRadius() {
            return 5;
        }

        public DeliveryMode getDeliveryMode() {
            return DeliveryMode.BY_DISTANCE;
        }

        public boolean handleGameEvent(ServerLevel level, GameEvent event, GameEvent.Context context, Vec3 pos) {
            if (event == GameEvent.ENTITY_DIE) {
                Entity entity = context.sourceEntity();
                if (entity instanceof Monster deadEntity) {
                    if (!deadEntity.wasExperienceConsumed()) {
                        // Add the entity to the dead entity list
                        this.positionSource.getPosition(level).ifPresent((sourcePos) -> {
                            MonsterAnchorBlockEntity monsterAnchorBlockEntity = (MonsterAnchorBlockEntity) level.getBlockEntity(BlockPos.containing(sourcePos));
                            monsterAnchorBlockEntity.entityQueue.add(deadEntity);
                        });

                        // Stop the mob from dropping experience and loot
                        deadEntity.skipDropExperience();
                        Collection<ItemEntity> drops = entity.captureDrops(null);
                        if (drops != null) drops.forEach(itemEntity -> itemEntity.remove(Entity.RemovalReason.KILLED));

                        AABB boundingBox = deadEntity.getBoundingBox();
                        processPoints(level, boundingBox, 0.2D).forEach(point -> {
                            level.sendParticles((ParticleOptions) NMLParticleTypes.MALEVOLENT_EMBERS.get(), point.x, point.y, point.z, 1, 0,0,0, 0);
                        });
                    }
                    return true;
                }
            }
            return false;
        }

        // Get all the points on all the faces of a bounding box depending on the resolution and process them
        public static List<Vec3> processPoints(ServerLevel level, AABB boundingBox, double step) {
            double minX = boundingBox.minX;
            double minY = boundingBox.minY;
            double minZ = boundingBox.minZ;
            double maxX = boundingBox.maxX;
            double maxY = boundingBox.maxY;
            double maxZ = boundingBox.maxZ;

            List<Vec3> pointList = new ArrayList<>();

            // Front face (minZ face)
            for (double x = minX; x <= maxX; x += step) {
                for (double y = minY; y <= maxY; y += step) {
                    Vec3 point = new Vec3(x, y, minZ);
                    pointList.add(point);
                }
            }

            // Back face (maxZ face)
            for (double x = minX; x <= maxX; x += step) {
                for (double y = minY; y <= maxY; y += step) {
                    Vec3 point = new Vec3(x, y, maxZ);
                    pointList.add(point);
                }
            }

            // Left face (minX face)
            for (double z = minZ; z <= maxZ; z += step) {
                for (double y = minY; y <= maxY; y += step) {
                    Vec3 point = new Vec3(minX, y, z);
                    pointList.add(point);
                }
            }

            // Right face (maxX face)
            for (double z = minZ; z <= maxZ; z += step) {
                for (double y = minY; y <= maxY; y += step) {
                    Vec3 point = new Vec3(maxX, y, z);
                    pointList.add(point);
                }
            }

            // Top face (maxY face)
            for (double x = minX; x <= maxX; x += step) {
                for (double z = minZ; z <= maxZ; z += step) {
                    Vec3 point = new Vec3(x, maxY, z);
                    pointList.add(point);
                }
            }

            // Bottom face (minY face)
            for (double x = minX; x <= maxX; x += step) {
                for (double z = minZ; z <= maxZ; z += step) {
                    Vec3 point = new Vec3(x, minY, z);
                    pointList.add(point);
                }
            }
            return pointList;
        }
    }
}