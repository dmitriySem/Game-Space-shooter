package ru.gb.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.gb.base.BaseButton;
import ru.gb.math.Rect;
import ru.gb.screen.GameScreen;

public class NewGameButton extends BaseButton {

    private static final float PADDING = 0.1f;

    private final Game game;


    public NewGameButton(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("button_new_game"));
        this.game = game;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.05f);
        setBottom(worldBounds.getHalfWidth() - PADDING);

    }

    @Override
    public void action() {
        game.setScreen(new GameScreen(game));

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }
}
