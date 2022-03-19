package org.mogdx.towerdefense.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Fighter extends Image {

    Animation<TextureRegionDrawable> animation;
    private static final int FRAME_COLS = 4, FRAME_ROWS = 1;
    static final Texture textureSheet = new Texture(Gdx.files.internal("fighter.png"));

    ShapeRenderer renderer = new ShapeRenderer();
    float stateTime;

    public Fighter() {
        super(new Texture(Gdx.files.internal("fighter.png")));

        TextureRegion[][] tmp = TextureRegion.split(textureSheet, 400/FRAME_COLS,
                100/FRAME_ROWS);

        TextureRegionDrawable[] frames = new TextureRegionDrawable[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                frames[index++] = new TextureRegionDrawable(tmp[i][j]);
            }
        }
        stateTime = 0f;
        animation = new Animation<>(0.5f, frames);
        setDrawable(animation.getKeyFrame(stateTime, true));

        setPosition(200, 200);
        setSize(100, 100);
        //setScale(0.5f);
        setDrawable(new TextureRegionDrawable(new Texture(Gdx.files.internal("badlogic.jpg"))));
    }

    @Override
    public void act(float delta) {
        super.act(delta);


        stateTime += delta;
        //setDrawable(animation.getKeyFrame(stateTime, true));

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
