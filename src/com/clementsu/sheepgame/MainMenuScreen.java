package com.clementsu.sheepgame;

import java.util.List;

import com.clementsu.framework.Game;
import com.clementsu.framework.Graphics;
import com.clementsu.framework.Screen;
import com.clementsu.framework.Input.TouchEvent;

public class MainMenuScreen extends Screen {
	public static boolean mainMenu = false;

	enum MenuState {
		Go, Story
	}

	MenuState state = MenuState.Go;

	public MainMenuScreen(Game game) {
		super(game);

	}

	@Override
	public void update(float deltaTime) {

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		if (state == MenuState.Go) {
			updateGo(touchEvents, deltaTime);
		}
		if (state == MenuState.Story) {
			updateStory(touchEvents, deltaTime);
		}
		// Graphics g = game.getGraphics();
		// // Assets.theme.seekBegin();
		// Assets.theme.start();
		//
		// List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		//
		// int len = touchEvents.size();
		// for (int i = 0; i < len; i++) {
		// TouchEvent event = touchEvents.get(i);
		// if (event.type == TouchEvent.TOUCH_UP) {
		//
		// if (inBounds(event, 210, 148, 150, 54)) { // Play Button
		// Assets.theme.pause();
		// mainMenu = false;
		// GameScreen.gameScreen = true;
		// game.setScreen(new GameScreen(game));
		// }
		//
		// if (inBounds(event, 226, 386, 124, 54)) { // Exit Button
		// backButton();
		// }
		//
		// }
		// }
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	private void updateGo(List<TouchEvent> touchEvents, float deltaTime) {
		Graphics g = game.getGraphics();
		// Assets.theme.seekBegin();
		Assets.theme.start();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 210, 148, 150, 54)) { // Play Button
					Assets.theme.pause();
					mainMenu = false;
					GameScreen.gameScreen = true;
					game.setScreen(new GameScreen(game));
				}

				if (inBounds(event, 196, 261, 179, 54)) { // Story Button
					game.setScreen(new StoryScreen(game));
				}

				if (inBounds(event, 226, 386, 124, 54)) { // Exit Button
					backButton();
				}

			}
		}

	}

	private void updateStory(List<TouchEvent> touchEvents, float deltaTime) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 480)) {
					resume();
				}
			}
		}

	}

	@Override
	public void paint(float deltaTime) {
		if (state == MenuState.Go) {
			Graphics g = game.getGraphics();
			g.drawImage(Assets.menu, 0, 0);
		}

		if (state == MenuState.Story) {
			Graphics g = game.getGraphics();
			g.drawImage(Assets.parchment, 0, 0);
		}
	}

	@Override
	public void pause() {
		if (state == MenuState.Go)
			state = MenuState.Story;
	}

	@Override
	public void resume() {
		if (state == MenuState.Story)
			state = MenuState.Go;

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		android.os.Process.killProcess(android.os.Process.myPid());

	}
}
