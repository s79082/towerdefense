package org.mogdx.towerdefense.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import org.mogdx.towerdefense.actor.CheckerBoard;
import org.mogdx.towerdefense.actor.Fighter;
import org.mogdx.towerdefense.actor.Fire;
import org.mogdx.towerdefense.actor.MenuBoard;
import org.mogdx.towerdefense.actor.util.Hit;
import org.mogdx.towerdefense.actor.util.Scaler;
import org.w3c.dom.css.Rect;

public class GameScreen implements Screen, InputProcessor {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;
    final Texture texture = new Texture(Gdx.files.internal("alien.png"));
    TextButton backButton;
    Stage stage;

    CheckerBoard table;


    Touchpad touchpad;

    Game game;

    Image fighter, zweiter;

    ShapeRenderer renderer = new ShapeRenderer();

    public static State currentState = State.FIRE;

    public enum State {
        FIRE,
        FIGHTER
    }

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        stage = new Stage(new StretchViewport(WIDTH, HEIGHT));
        //stage.addActor(new Image(texture));
        Skin skin = new Skin(Gdx.files.internal("comic-style/comic-ui.json"));
        backButton = new TextButton("X", skin);
        backButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                game.setScreen(new MainMenuScreen(game));
                return false;
            }
        });

        Gdx.app.log("", String.valueOf(Gdx.graphics.getHeight()));
        backButton.setPosition(0, HEIGHT - backButton.getHeight());


        //final CheckerBoardTable table = new CheckerBoardTable();
        table = new CheckerBoard(7, 5);



        final MenuBoard menuBoard = new MenuBoard(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("dsadsaddddddddd", "sdasdsssssssssssssss");
                currentState = State.FIGHTER;
            }
        });
        menuBoard.setPosition(7 * 100, 0);
        //Container<CheckerBoardTable> container = new Container<>();
        //container.setActor(table);
        stage.addActor(table);
        stage.addActor(menuBoard);
        stage.addActor(backButton);
        final Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.background = new TextureRegionDrawable(new Texture("badlogic.jpg"));
        touchpadStyle.knob = new TextureRegionDrawable(Scaler.scale(new Pixmap(
                Gdx.files.internal("badlogic.jpg")),
                100, 100));
        touchpad = new Touchpad(20, touchpadStyle);

        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {


                if (fighter.getX() > WIDTH - fighter.getWidth()) {
                    fighter.setX(WIDTH - fighter.getWidth());
                    return;
                }
                if (fighter.getY() > HEIGHT - fighter.getHeight()) {
                    fighter.setY(HEIGHT - fighter.getHeight());
                    return;
                }
                fighter.addAction(Actions.moveBy((touchpad.getKnobX()- 150) / 10, (touchpad.getKnobY() - 150) / 10));
            }
        });/*
        touchpad.addListener(new InputListener(){

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println(touchpad.getKnobX());

                fighter.addAction(Actions.moveBy(x, y, 1));
                return false;
            }
        });

        */
        stage.addActor(touchpad);

        fighter = new Fighter();
        zweiter = new Fighter();
        stage.addActor(zweiter);
        stage.addActor(fighter);


        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        //inputMultiplexer.addProcessor(0, this);
        inputMultiplexer.addProcessor(0, stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float x = zweiter.getX() - fighter.getX();
        float y = zweiter.getY() - fighter.getY();
        //System.out.println("x" + x);
        //System.out.println("y" + y);

        Actor hit = fighter.hit(zweiter.getX() - fighter.getX(),
                zweiter.getY() - fighter.getY(), false);


        stage.act();
        stage.draw();

        for (Actor a: table.getChildren()) {

            Rectangle intersection = new Rectangle();
            Intersector.intersectRectangles(new Rectangle(fighter.getX(), fighter.getY(),
                    fighter.getWidth(), fighter.getHeight()), new Rectangle(a.getX(),
                    a.getY(), a.getWidth(), a.getHeight()), intersection);
            if (a instanceof Fire && intersection.width > 0 && intersection.width > 0) {
                Fire fire = (Fire) a;
                System.out.println("burned " + intersection);
                // draw the intersect


                stage.getBatch().begin();
                renderer.begin(ShapeRenderer.ShapeType.Filled);
                renderer.setProjectionMatrix(stage.getBatch().getProjectionMatrix());
                renderer.setColor(Color.GREEN);
                renderer.rect(intersection.x, intersection.y, intersection.width, intersection.height);
                renderer.end();
                stage.getBatch().end();
                //stage.getBatch().begin();

            }
        }



    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {



        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.BACK)
            game.setScreen(new MainMenuScreen(game));
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Image actor = new Fire();

        // get wolrd coords
        Vector3 world = stage.getCamera().unproject(new Vector3(screenX, screenY, 0));

        // center
        world = world.sub(new Vector3(actor.getWidth() / 2, actor.getHeight() / 2, 0));

        actor.setPosition(world.x, world.y);
        //actors.addActor(actor);
        //actors.addAction(Actions.moveTo(screenX, screenY));
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        Vector3 world = stage.getCamera().unproject(new Vector3(screenX, screenY, 0));

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
