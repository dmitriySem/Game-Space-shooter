package ru.gb.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.gb.base.BaseScreen;
import ru.gb.math.Rect;
import ru.gb.pool.BulletPool;
import ru.gb.pool.EnumyPool;
import ru.gb.sprite.Background;
import ru.gb.sprite.MainShip;
import ru.gb.sprite.Star;
import ru.gb.utils.EnemyEmitter;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;

    private Texture bg;
    private Background background;

    private TextureAtlas atlas;

    private Star[] stars;
    private BulletPool bulletPool;
    private EnumyPool enumyPool;
    private MainShip mainShip;

    private EnemyEmitter enemyEmitter;

    private Music music;
    private Sound laserSound;
    private Sound bulletSound;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);

        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));

        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        enumyPool = new EnumyPool(worldBounds, bulletPool);

        mainShip = new MainShip(atlas, bulletPool, bulletSound);
        enemyEmitter = new EnemyEmitter(worldBounds,laserSound, enumyPool, atlas);

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setVolume(0.2f);
        music.setLooping(true);
        music.play();

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyed();
        enemyEmitter.generate(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        enumyPool.dispose();
        music.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        mainShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        mainShip.touchUp(touch, pointer, button);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        mainShip.update(delta);
        bulletPool.updateActiveSprites(delta);
        enumyPool.updateActiveSprites(delta);
    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprites();
        enumyPool.freeAllDestroyedActiveSprites();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        enumyPool.drawActiveSprites(batch);
        batch.end();
    }
}
