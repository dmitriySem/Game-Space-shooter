package ru.gb.pool;

import ru.gb.base.Sprite;
import ru.gb.base.SpritesPool;
import ru.gb.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newSprite() {
        return new Bullet();
    }
}
