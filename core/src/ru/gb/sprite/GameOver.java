package ru.gb.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.gb.base.Sprite;
import ru.gb.math.Rect;

public class GameOver extends Sprite {



    public GameOver(TextureAtlas atlas) {
        regions = new TextureRegion[1];
        regions[0] = atlas.findRegion("message_game_over");
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.08f);
        setBottom(worldBounds.getHalfWidth());
    }


}
