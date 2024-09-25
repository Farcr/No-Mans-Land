package com.farcr.nomansland.core.content.item;

import com.farcr.nomansland.core.content.entity.bombs.ExplosiveEntity;
import com.farcr.nomansland.core.content.entity.bombs.ThrowableBombEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ExplosiveItem extends BombItem
{
    public ExplosiveItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ThrowableBombEntity createBomb(LivingEntity entity, Level level) {
        return new ExplosiveEntity(entity, level);
    }
}
