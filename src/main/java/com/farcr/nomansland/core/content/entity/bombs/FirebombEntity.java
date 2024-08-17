package com.farcr.nomansland.core.content.entity.bombs;


import com.farcr.nomansland.core.registry.NMLBlocks;
import com.farcr.nomansland.core.registry.NMLEntities;
import com.farcr.nomansland.core.registry.NMLTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.WallTorchBlock.FACING;

public class FirebombEntity extends ThrowableBombEntity {

    public FirebombEntity(EntityType<? extends ThrowableBombEntity> entityType, Level level) {
        super(entityType, level);
    }

    public FirebombEntity(LivingEntity livingEntity, Level level) {
        super(NMLEntities.FIREBOMB.get(), livingEntity, level);
    }

    public FirebombEntity(Level level, double x, double y, double z) {
        super(NMLEntities.FIREBOMB.get(), x, y, z, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide() && this.level().getBlockState(this.blockPosition()).is(NMLTags.FIREBOMB_EXPLODE)) {
            this.explode();
        }
    }

    private void spawnParticles(ParticleOptions particle, int amount) {
        for (int i = 0; i < amount; i++) {
            double theta = this.random.nextFloat() * 2 * Math.PI;
            double alpha = this.random.nextFloat() * 2 * Math.PI;
            double cos = Math.cos(alpha);
            double xVelocity = Math.sin(theta) * cos * (this.random.nextFloat() * 0.3 + 0.7);
            double yVelocity = cos * Math.cos(theta) * (this.random.nextFloat() * 0.3 + 0.7);
            double zVelocity = Math.sin(alpha) * (this.random.nextFloat() * 0.3 + 0.7);
            this.level().addParticle(particle, this.getX(), this.getY(), this.getZ(), xVelocity * 0.6, yVelocity * 0.6, zVelocity * 0.6);
        }
    }

    @Override
    public void handleEntityEvent(byte b) {
        if (b == 0) {
            this.spawnParticles(ParticleTypes.SMOKE, 320);

            for (int i = 0; i < 40; i++) {
                double theta = this.random.nextFloat() * 2 * Math.PI;
                double alpha = this.random.nextFloat() * 2 * Math.PI;
                double cos = Math.cos(alpha);
                double xVelocity = Math.sin(theta) * cos * (this.random.nextFloat() * 0.3 + 0.7);
                double yVelocity = cos * Math.cos(theta) * (this.random.nextFloat() * 0.3 + 0.7);
                double zVelocity = Math.sin(alpha) * (this.random.nextFloat() * 0.3 + 0.7);
                this.level().addParticle(ParticleTypes.FLAME, false, this.getX(), this.getY(), this.getZ(), xVelocity * 0.1, yVelocity * 0.1, zVelocity * 0.1);
            }
        } else if (b == 1) {
            this.spawnParticles(ParticleTypes.SMOKE, 400);
        } else {
            super.handleEntityEvent(b);
        }
    }

    @Override
    protected void explode() {
        this.level().explode(this, this.getX(), this.getY(0.0625), this.getZ(), 2.0F, Level.ExplosionInteraction.NONE);
        // Light nearby campfires on fire
        BlockPos.withinManhattan(this.blockPosition(), 6, 4, 6).forEach(pos -> {
            BlockState state = this.level().getBlockState(pos);
            if (state.is(BlockTags.CAMPFIRES) && state.hasProperty(CampfireBlock.LIT) && !state.getValue(CampfireBlock.LIT)) {
                this.level().setBlock(pos, state.setValue(CampfireBlock.LIT, true), 3);
            }
            if (state.getBlock() == Blocks.TNT) {
                TntBlock.explode(this.level(), pos);
                this.level().setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            }
            if (state.getBlock() == NMLBlocks.EXTINGUISHED_TORCH.get()) {
                this.level().setBlock(pos, Blocks.TORCH.defaultBlockState(), 3);
            } else if (state.getBlock() == NMLBlocks.EXTINGUISHED_WALL_TORCH.get()) {
                this.level().setBlock(pos, Blocks.WALL_TORCH.defaultBlockState().setValue(FACING, state.getValue(FACING)), 3);
            } else if (state.getBlock() == NMLBlocks.EXTINGUISHED_SOUL_TORCH.get()) {
                this.level().setBlock(pos, Blocks.SOUL_TORCH.defaultBlockState(), 3);
            } else if (state.getBlock() == NMLBlocks.EXTINGUISHED_SOUL_WALL_TORCH.get()) {
                this.level().setBlock(pos, Blocks.SOUL_WALL_TORCH.defaultBlockState().setValue(FACING, state.getValue(FACING)), 3);
            } else if (state.getBlock() == NMLBlocks.EXTINGUISHED_SCONCE_TORCH.get()) {
                this.level().setBlock(pos, NMLBlocks.SCONCE_TORCH.get().defaultBlockState(), 3);
            } else if (state.getBlock() == NMLBlocks.EXTINGUISHED_SCONCE_WALL_TORCH.get()) {
                this.level().setBlock(pos, NMLBlocks.SCONCE_WALL_TORCH.get().defaultBlockState().setValue(FACING, state.getValue(FACING)), 3);
            } else if (state.getBlock() == NMLBlocks.EXTINGUISHED_SCONCE_SOUL_TORCH.get()) {
                this.level().setBlock(pos, NMLBlocks.SCONCE_SOUL_TORCH.get().defaultBlockState(), 3);
            } else if (state.getBlock() == NMLBlocks.EXTINGUISHED_SCONCE_SOUL_WALL_TORCH.get()) {

                this.level().setBlock(pos, NMLBlocks.SCONCE_SOUL_WALL_TORCH.get().defaultBlockState().setValue(FACING, state.getValue(FACING)), 3);
            }

        });
        this.level().broadcastEntityEvent(this, (byte) (this.isInWater() ? 1 : 0));
        this.discard();
    }

    @Override
    protected ParticleOptions getParticle() {
        return ParticleTypes.SMOKE;
    }
}