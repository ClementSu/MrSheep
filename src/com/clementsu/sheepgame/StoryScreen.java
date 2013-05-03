package com.clementsu.sheepgame;

import java.util.List;

import com.clementsu.framework.Game;
import com.clementsu.framework.Graphics;
import com.clementsu.framework.Screen;
import com.clementsu.framework.Input.TouchEvent;

public class StoryScreen extends Screen {
	public static boolean mainMenu = false;

	public StoryScreen(Game game) {
		super(game);

	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		Graphics g = game.getGraphics();
		// Assets.theme.seekBegin();
		Assets.theme.start();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 0, 0, 800, 480)) { // Play Button

					game.setScreen(new MainMenuScreen(game));
				}
			}

		}
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void paint(float deltaTime) {

		Graphics g = game.getGraphics();
		g.drawImage(Assets.parchment, 0, 0);

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		android.os.Process.killProcess(android.os.Process.myPid());

	}
}