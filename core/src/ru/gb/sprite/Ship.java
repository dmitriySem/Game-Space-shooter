package ru.gb.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.gb.base.Sprite;
import ru.gb.math.Rect;

public class Ship extends Sprite {

    private static final float V_LEN = 0.01f;
    private static final float PADDING = 0.03f;

    private Vector2 velocity;
    private boolean pressed;
    private boolean left;
    private Rect worldBounds;


    public Ship(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship").split(195, 287)[0][0]);
        this.velocity = new Vector2(0,0);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(0.18f);
        setBottom(worldBounds.getBottom() + PADDING);
    }


    @Override
    public void update(float delta) {
        if (pressed && worldBounds.getLeft() + PADDING < getLeft() || left){
            pos.add(velocity);
            if (getLeft() < (worldBounds.getLeft() + PADDING))
                setLeft(worldBounds.getLeft() + PADDING);
        }

        if ((pressed && worldBounds.getRight() - PADDING > getRight()) || !left){
            pos.add(velocity);
            if (getRight() > worldBounds.getRight() - PADDING)
                setRight(worldBounds.getRight() - PADDING);
        }
        pos.add(0f,0f);
    }


    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.left =  touch.x < 0.0f ? true : false;
        velocity.set(touch.x - pos.x, 0).setLength(V_LEN);

        this.pressed = true;
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        velocity.set(0.0f,0.0f);
        this.pressed = false;
        return false;
    }



}
