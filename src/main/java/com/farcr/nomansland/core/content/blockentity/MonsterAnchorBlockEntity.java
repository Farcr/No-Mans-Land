package com.farcr.nomansland.core.content.blockentity;

import com.farcr.nomansland.core.registry.NMLBlockEntities;
import com.farcr.nomansland.core.registry.NMLParticleTypes;
import com.google.common.annotations.VisibleForTesting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SculkCatalystBlock;
import net.minecraft.world.level.block.SculkSpreader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SculkCatalystBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class MonsterAnchorBlockEntity extends BlockEntity implements GameEventListener.Holder<MonsterAnchorBlockEntity.AnchorListener> {

    private final AnchorListener anchorListener;

    public MonsterAnchorBlockEntity(BlockPos pos, BlockState state) {
        super(NMLBlockEntities.MONSTER_ANCHOR.get(), pos, state);
        this.anchorListener = new AnchorListener(state, new BlockPositionSource(pos));
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MonsterAnchorBlockEntity monsterAnchor) {
    }

    public AnchorListener getListener() {
        return this.anchorListener;
    }

    public static class AnchorListener implements GameEventListener {
        private final BlockState blockState;
        private final PositionSource positionSource;

        public AnchorListener(BlockState pBlockState, PositionSource pPositionSource) {
            this.blockState = pBlockState;
            this.positionSource = pPositionSource;
        }

        public PositionSource getListenerSource() {
            return this.positionSource;
        }

        public int getListenerRadius() {
            return 5;
        }

        public GameEventListener.DeliveryMode getDeliveryMode() {
            return DeliveryMode.BY_DISTANCE;
        }

        public boolean handleGameEvent(ServerLevel level, GameEvent event, GameEvent.Context context, Vec3 pos) {
            if (event == GameEvent.ENTITY_DIE) {
                Entity entity = context.sourceEntity();
                if (entity instanceof LivingEntity) {
                    LivingEntity deadEntity = (LivingEntity)entity;
                    if (!deadEntity.wasExperienceConsumed()) {
                        level.addParticle((ParticleOptions) NMLParticleTypes.MALEVOLENT_EMBERS.get(), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);

                        // Stop the mob from dropping experience and loot
                        deadEntity.skipDropExperience();
                        Objects.requireNonNull(deadEntity.captureDrops()).clear();
                    }

                    return true;
                }
            }

            return false;
        }
    }
}
