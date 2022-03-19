package org.mogdx.towerdefense.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import org.mogdx.towerdefense.actor.util.Scaler;

public class Fire extends Image {

    private static final int FRAME_COLS = 3, FRAME_ROWS = 1;
    Animation<TextureRegionDrawable> idleAnimation;
    static final Texture textureSheet = new Texture(Gdx.files.internal("fire2.png"));

    static final float lifetime = 10f;
    float stateTime;

    public boolean isRemove() {
        return remove;
    }

    boolean remove = false;

    ShapeRenderer renderer = new ShapeRenderer();

    public Fire() {
        super(textureSheet);
        createIdleAnimation();
        setTouchable(Touchable.enabled);
        setSize(100, 100);

    }

    void createIdleAnimation() {

        TextureRegion[][] tmp = TextureRegion.split(textureSheet, 189 * 1/FRAME_COLS,
                112 * 1/FRAME_ROWS);

        TextureRegionDrawable[] frames = new TextureRegionDrawable[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {

                frames[index++] = new TextureRegionDrawable(tmp[i][j]);
            }
        }
        stateTime = 0f;
        idleAnimation = new Animation<>(0.5f, frames);
        setDrawable(idleAnimation.getKeyFrame(stateTime));
        //setDrawable(new TextureRegionDrawable(new Texture(Gdx.files.internal("badlogic.jpg"))));

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        stateTime += delta;
        setDrawable(idleAnimation.getKeyFrame(stateTime, true));
        if (stateTime >= lifetime) {
            remove = true;
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.RED);
        renderer.rect(getX(), getY() , getWidth(), getHeight());
        renderer.end();
        batch.begin();
    }
}
