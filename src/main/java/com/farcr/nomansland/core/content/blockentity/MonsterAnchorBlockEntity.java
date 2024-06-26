package com.farcr.nomansland.core.content.blockentity;

import com.farcr.nomansland.core.content.block.MonsterAnchorBlock;
import com.farcr.nomansland.core.events.listeners.AnchorListener;
import com.farcr.nomansland.core.registry.NMLBlockEntities;
import com.farcr.nomansland.core.registry.NMLParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.farcr.nomansland.core.events.listeners.AnchorListener.processPoints;

public class MonsterAnchorBlockEntity extends BlockEntity implements GameEventListener.Holder<AnchorListener> {

    private final AnchorListener anchorListener;
    public final LinkedHashMap<LivingEntity, Vec3> entityQueue;
    public int timeResurrecting;
    public int timeIdle;
    public MonsterAnchorBlockEntity(BlockPos pos, BlockState state) {
        super(NMLBlockEntities.MONSTER_ANCHOR.get(), pos, state);
        this.anchorListener = new AnchorListener(state, new BlockPositionSource(pos));
        this.entityQueue = new LinkedHashMap<>();
        this.timeResurrecting = 0;
        this.timeIdle = 0;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MonsterAnchorBlockEntity monsterAnchor) {

        ServerLevel serverLevel = (ServerLevel) level;
        LinkedHashMap<LivingEntity, Vec3> entityQueue = monsterAnchor.entityQueue;
        List<LivingEntity> deadEntities = new ArrayList<>(entityQueue.keySet());
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
                    level.gameEvent(GameEvent.BLOCK_DEACTIVATE, pos, GameEvent.Context.of(state));
            } else if (monsterAnchor.timeIdle%5 == 0) serverLevel.sendParticles(ParticleTypes.SMOKE, pos.getX()+0.5+random.nextInt(0,3)*0.01 - random.nextInt(0,3)*0.01, pos.getY()+0.2, pos.getZ()+0.5+random.nextInt(0,3)*0.01 - random.nextInt(0,3)*0.01, 3, 0,0,0,0);
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
        for (int i = 0; i < deadEntities.size(); i++) {
            LivingEntity deadEntity = deadEntities.get(i);
            Vec3 spawningPosition = entityQueue.get(deadEntity);

            if (level.random.nextFloat() <= 0.1F) serverLevel.sendParticles((ParticleOptions) NMLParticleTypes.MALEVOLENT_FLAME.get(),
                    spawningPosition.x+random.nextFloat() - random.nextFloat(),
                    spawningPosition.y+random.nextFloat() - random.nextFloat(),
                    spawningPosition.z+random.nextFloat() - random.nextFloat(),
                    3, 0,0,0,0);

            if (monsterAnchor.timeResurrecting%80 == 0 && i != 0) {
                for (double y = 0; y <= 4; y++) {
                    if(level.getBlockState(BlockPos.containing(spawningPosition.relative(Direction.DOWN, y))).isSolid()) {
                        LivingEntity tempEntity = (LivingEntity) deadEntity.getType().create(level);
                        if (tempEntity!= null) tempEntity.moveTo(spawningPosition);
                        if (tempEntity instanceof Zombie zombie) zombie.setBaby(deadEntity.isBaby());
                        double finalY = y-1.1D;
                        processPoints(serverLevel, tempEntity.getBoundingBox(), 0.2D).forEach(point -> {
                            serverLevel.sendParticles((ParticleOptions) NMLParticleTypes.MALEVOLENT_EMBERS.get(), point.x, spawningPosition.y-finalY, point.z, 1, 0,0,0, 0);
                        });
                        tempEntity.remove(Entity.RemovalReason.DISCARDED);
                        break;
                    }
                }
            }


                // Select the first entity on the list
            if (i == 0) {
                // This sound plays a bit late, so it is played before the mob is resurrected to time it perfectly
                if (monsterAnchor.timeResurrecting <= 2) {
                    // Turn the block active on mob resurrection
                    if (!state.getValue(MonsterAnchorBlock.ACTIVE)) {
                        level.playSound(null, pos, SoundEvents.BEACON_POWER_SELECT, SoundSource.BLOCKS, 1, 0.25F);
                        level.setBlockAndUpdate(pos, state.setValue(MonsterAnchorBlock.ACTIVE, true));
                        level.gameEvent(GameEvent.BLOCK_ACTIVATE, pos, GameEvent.Context.of(state));
                    }
                    level.playSound(null, pos, SoundEvents.BELL_RESONATE, SoundSource.BLOCKS, 1, 0.5F);
                }
                if (monsterAnchor.timeResurrecting == 80) {
                    monsterAnchor.timeResurrecting = 0;
                    // Resurrect the entity
                    LivingEntity resurrectedEntity = (LivingEntity) deadEntity.getType().create(level);
                    if (resurrectedEntity != null) {
                        if (resurrectedEntity instanceof Zombie zombie) zombie.setBaby(deadEntity.isBaby());
                        resurrectedEntity.moveTo(spawningPosition.x, spawningPosition.y, spawningPosition.z);
                        level.addFreshEntity(resurrectedEntity);
                        entityQueue.remove(deadEntity);
                        for (double y = 0; y <= 4; y++) {
                            if(level.getBlockState(BlockPos.containing(spawningPosition.relative(Direction.DOWN, y))).isSolid()) {
                                double finalY = y - 1.1D;
                                processPoints(serverLevel, resurrectedEntity.getBoundingBox(), 0.4D).forEach(point -> {
                                    serverLevel.sendParticles((ParticleOptions) NMLParticleTypes.MALEVOLENT_FLAME.get(), point.x, spawningPosition.y - finalY, point.z, 1, 0, 0, 0, 0.1);
                                });
                            }
                        }
                    }
                }
            }
        }
    }

    public AnchorListener getListener() {
        return this.anchorListener;
    }
}