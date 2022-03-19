package org.mogdx.towerdefense.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Alien extends Image {
    static final Texture texture = new Texture(Gdx.files.internal("alien2.png"));

    public Alien() {
        super(texture);
    }

}
