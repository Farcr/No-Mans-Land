package com.farcr.nomansland.core.content.blockentity;

import com.farcr.nomansland.core.content.block.MonsterAnchorBlock;
import com.farcr.nomansland.core.events.listeners.AnchorListener;
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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
        for (int i = 0; i < deadEntities.size(); i++) {
            Vec3 spawningPosition = entityQueue.get(deadEntities.get(i));

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
                    LivingEntity resurrectedEntity = (LivingEntity) deadEntities.get(i).getType().create(level);
                    if (resurrectedEntity != null) {
                        if (resurrectedEntity instanceof AgeableMob ageableMob) ageableMob.setBaby(deadEntities.get(i).isBaby());
                        resurrectedEntity.moveTo(spawningPosition.x, spawningPosition.y, spawningPosition.z);
                        level.addFreshEntity(resurrectedEntity);
                        entityQueue.remove(deadEntities.get(i));
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
}