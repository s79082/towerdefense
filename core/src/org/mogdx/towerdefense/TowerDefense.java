package org.mogdx.towerdefense;

import com.badlogic.gdx.Game;

import org.mogdx.towerdefense.screen.MainMenuScreen;

public class TowerDefense extends Game {

	@Override
	public void create () {

		setScreen(new MainMenuScreen(this));
	}

}
