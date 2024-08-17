package com.farcr.nomansland.core.content.item;

import com.farcr.nomansland.core.content.entity.bombs.FirebombEntity;
import com.farcr.nomansland.core.content.entity.bombs.ThrowableBombEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class FirebombItem extends BombItem {

    public FirebombItem(Properties properties) {
        super(properties);
    }

    @Override
    public ThrowableBombEntity createBomb(LivingEntity entity, Level level) {
        return new FirebombEntity(entity, level);
    }
}