package org.mogdx.towerdefense.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import org.mogdx.towerdefense.screen.GameScreen;

public class CheckerBoard extends Group {

    CheckBoardCell[][] cells;

    public Group getFires() {
        return fires;
    }

    Group fires;
    public CheckerBoard(int rs, int cs) {
        super();


        fires = new Group();
        //addActor(fires);
        cells = new CheckBoardCell[rs][cs];
        for (int i = 0; i < rs; i++) {
            for (int j = 0; j < cs; j++) {
                Pixmap pixmap200 = new Pixmap(Gdx.files.internal("meadow.png"));
                Pixmap pixmap100 = new Pixmap(100, 100, pixmap200.getFormat());
                pixmap100.drawPixmap(pixmap200,
                        0, 0, pixmap200.getWidth(), pixmap200.getHeight(),
                        0, 0, pixmap100.getWidth(), pixmap100.getHeight()
                );
                final Texture texture = new Texture(pixmap100);
                pixmap200.dispose();
                pixmap100.dispose();
                final Image image = new Image(texture);
                image.setPosition(i * texture.getWidth(), j * texture.getHeight());
                final int row = i;
                final int col = j;
                image.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        Gdx.app.log("Sdsd", row + " dsd " + col);

                        Image actor;
                        switch (GameScreen.currentState) {
                            case FIRE: actor = new Fire(); break;
                            case FIGHTER: actor = new Alien(); break;
                            default: return false;
                        }

                        fires.addActor(actor);
                        actor.setScale(0.5f);
                        actor.setPosition(row * texture.getWidth(), col * texture.getHeight());
                        addActor(actor);
                        cells[row][col].setContent(actor);
                        return false;
                    }
                });

                CheckBoardCell cell = new CheckBoardCell(image);
                cells[i][j] = cell;
                addActor(image);
            }
        }
    }

    class CheckBoardCell {

        Image content;

        public Image getContent() {
            return content;
        }

        public void setContent(Image content) {
            this.content = content;
        }

        public CheckBoardCell(Image content) {
            this.content = content;
        }



    }
}
