package ru.gb.pool;

import ru.gb.base.Sprite;
import ru.gb.base.SpritesPool;
import ru.gb.math.Rect;
import ru.gb.sprite.EnemyShip;

public class EnumyPool extends SpritesPool<EnemyShip> {

    private  Rect worldBounds;
    private  BulletPool BulletPool;

    public EnumyPool(Rect worldBounds, BulletPool BulletPool) {
        this.worldBounds = worldBounds;
        this.BulletPool = BulletPool;
    }


    @Override
    protected EnemyShip newSprite() {
        return new EnemyShip(worldBounds,BulletPool);
    }
}
