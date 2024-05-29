package com.farcr.nomansland.core.content.blockentity;

import com.farcr.nomansland.core.content.block.MonsterAnchorBlock;
import com.farcr.nomansland.core.registry.NMLBlockEntities;
import com.farcr.nomansland.core.registry.NMLParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.Vec3;

import java.util.*;

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
        serverLevel.sendParticles((ParticleOptions) NMLParticleTypes.MALEVOLENT_FLAME.get(), pos.getX() + Math.random(), pos.getY() + Math.random(), pos.getZ() + Math.random(), monsterAnchor.timeResurrecting, 0, 0, 0, 0.0);

        // Loop through all the entities in the queue
        for (int i = 0; i < entityQueue.size(); i++) {
            Vec3 spawningPosition = entityQueue.get(i).getPosition(1);
            // Spawn particles at the entity death location will stay until they are out of the queue
            serverLevel.sendParticles((ParticleOptions) NMLParticleTypes.MALEVOLENT_EMBERS.get(), spawningPosition.x + Math.random(), spawningPosition.y + Math.random(), spawningPosition.z + Math.random(), 3, 0, 0, 0, 0.0);

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
                if (entity instanceof LivingEntity deadEntity && !(entity instanceof Player)) {
                    if (!deadEntity.wasExperienceConsumed()) {
                        // Add the entity to the dead entity list
                        this.positionSource.getPosition(level).ifPresent((sourcePos) -> {
                            MonsterAnchorBlockEntity monsterAnchorBlockEntity = (MonsterAnchorBlockEntity) level.getBlockEntity(BlockPos.containing(sourcePos));
                            monsterAnchorBlockEntity.entityQueue.add(deadEntity);
//                            level.scheduleTick(BlockPos.containing(sourcePos), state.getBlock(), 1);
                        });

                        // Stop the mob from dropping experience and loot
                        deadEntity.skipDropExperience();
//                    if (!deadEntity.captureDrops().isEmpty()) deadEntity.captureDrops().clear();
//                        Collection<ItemEntity> drops = deadEntity.captureDrops();
//                        if (drops != null) drops.forEach(itemEntity -> itemEntity.remove(Entity.RemovalReason.KILLED));
                    }
                    return true;
                }
            }
            return false;
        }
    }
}