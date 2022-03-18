package ru.gb.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.gb.base.BaseScreen;
import ru.gb.math.Rect;
import ru.gb.sprite.Background;
import ru.gb.sprite.Ship;
import ru.gb.sprite.Star;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;
    private Texture bg;
    private Background background;
    private Ship ship;

    private TextureAtlas atlas;

    private Star[] stars;

    @Override
    public void show() {
        bg = new Texture("textures/bg.png");
        background = new Background(bg);

        super.show();
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++)
            stars[i] = new Star(atlas);

        ship = new Ship(atlas);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        draw();
        update(delta);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star:stars)
            star.resize(worldBounds);

        ship.resize(worldBounds);

    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        ship.touchDown(touch,pointer,button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        ship.touchUp(touch,pointer,button);
        return false;
    }

    private void update(float delta){
        for (Star star:stars)
            star.update(delta);

        ship.update(delta);
    }

    private void draw(){
        batch.begin();
        background.draw(batch);

        for (Star star:stars)
            star.draw(batch);

        ship.draw(batch);

        batch.end();

    }
}
