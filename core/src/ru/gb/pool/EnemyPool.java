package ru.gb.pool;

import ru.gb.base.Sprite;
import ru.gb.base.SpritesPool;
import ru.gb.math.Rect;
import ru.gb.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private  Rect worldBounds;
    private  BulletPool bulletPool;
    private ExplosionPool explosionPool;

    public EnemyPool(Rect worldBounds, BulletPool bulletPool, ExplosionPool explosionPool) {
        this.worldBounds = worldBounds;
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
    }


    @Override
    protected EnemyShip newSprite() {
        return new EnemyShip(worldBounds,bulletPool, explosionPool);
    }
}
