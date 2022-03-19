package org.mogdx.towerdefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static com.badlogic.gdx.Gdx.files;

public class CheckerBoardButton extends Button {
    int row, col;
    Actor actor;

    public CheckerBoardButton(final int row, final int col, final CheckerBoardTable.CellTouchedHandler onTouch) {
        super(new TextureRegionDrawable(new Texture(files.internal("alien.png"))));

        this.row = row;
        this.col = col;

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onTouch.onTouch(CheckerBoardButton.this.row, CheckerBoardButton.this.col);
            }
        });
    }
}
