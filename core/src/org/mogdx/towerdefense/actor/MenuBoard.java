package org.mogdx.towerdefense.actor;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MenuBoard extends CheckerBoard {
    public MenuBoard(InputListener buttonClickHandler) {
        super(1, 5);
        Image button = new Image(new Texture(Gdx.files.internal("rsz_badlogic.jpg")));

        button.setTouchable(Touchable.enabled);

        button.addListener(buttonClickHandler);

        cells[0][1].setContent(button);
        addActor(button);
    }

    public abstract class ButtonClickHandler extends InputListener{
        abstract void onClick();
    }
}
