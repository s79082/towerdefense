package org.mogdx.towerdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import org.mogdx.towerdefense.actor.Fire;

import java.util.function.Function;

import static com.badlogic.gdx.Gdx.*;

public class CheckerBoardTable extends Table {

    CellTouchedHandler onCellTouched;

    public CheckerBoardTable() {
        super();
        this.onCellTouched = new CellTouchedHandler() {
            @Override
            public void onTouch(int row, int col) {
                Fire fire = new Fire();
                fire.setTouchable(Touchable.enabled);
                fire.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        System.out.println("sdasdsdsddd");
                        return false;
                    }
                });
                addActor(fire);
                setActorTo(fire, row, col);
                //addAction(Actions.visible(true));
            }
        };
        setPosition(300, 200);
        generate(5, 4);
    }

    private void generate(final int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                CheckerBoardButton button = new CheckerBoardButton(i, j, onCellTouched);

                add(button);
            }
            this.row();
        }
    }

    public void setActorTo(Actor actor, int r, int c) {
        for (Cell cell: getCells()) {
            if (!(cell.getActor() instanceof CheckerBoardButton))
                continue;
            CheckerBoardButton boardButton = (CheckerBoardButton) cell.getActor();
            Actor actor1 = boardButton.actor;
            if (boardButton.row == r && boardButton.col == c) {

                cell.setActor(new Fire());
                //boardButton.setBackground(new TextureRegionDrawable(new Texture(files.internal("badlogic.jpg"))));
                break;
            }

        }
    }

    public interface CellTouchedHandler {
        void onTouch(int row, int col);
    }
}
