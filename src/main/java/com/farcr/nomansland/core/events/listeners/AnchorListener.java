package com.farcr.nomansland.core.events.listeners;

import com.farcr.nomansland.core.content.blockentity.MonsterAnchorBlockEntity;
import com.farcr.nomansland.core.registry.NMLParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class AnchorListener implements GameEventListener {
    private final BlockState state;
    private final PositionSource positionSource;


    public AnchorListener(BlockState pBlockState, PositionSource pPositionSource) {
        this.state = pBlockState;
        this.positionSource = pPositionSource;
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

    public PositionSource getListenerSource() {
        return this.positionSource;
    }

    public int getListenerRadius() {
        return 5;
    }

    @Override
    public boolean handleGameEvent(ServerLevel level, Holder<GameEvent> gameEvent, GameEvent.Context context, Vec3 pos) {
        if (gameEvent == GameEvent.ENTITY_DIE) {
            Entity entity = context.sourceEntity();
            if (entity instanceof Monster deadEntity) {
                if (!deadEntity.wasExperienceConsumed()) {
                    // Add the entity to the dead entity list
                    this.positionSource.getPosition(level).ifPresent((sourcePos) -> {
                        MonsterAnchorBlockEntity monsterAnchorBlockEntity = (MonsterAnchorBlockEntity) level.getBlockEntity(BlockPos.containing(sourcePos));
                        monsterAnchorBlockEntity.entityQueue.put(deadEntity, deadEntity.getPosition(0));
                    });

                    // Stop the mob from dropping experience and loot
                    deadEntity.skipDropExperience();
                    deadEntity.setPersistenceRequired();

                    AABB boundingBox = deadEntity.getBoundingBox();
                    processPoints(level, boundingBox, 0.2D).forEach(point -> {
                        level.sendParticles((ParticleOptions) NMLParticleTypes.MALEVOLENT_EMBERS.get(), point.x, point.y, point.z, 1, 0, 0, 0, 0);
                    });
                }
            }
            return true;
        }
        return false;
    }

    public DeliveryMode getDeliveryMode() {
        return DeliveryMode.BY_DISTANCE;
    }
}
