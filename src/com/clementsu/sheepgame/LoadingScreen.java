package com.clementsu.sheepgame;

import com.clementsu.framework.Game;
import com.clementsu.framework.Graphics;
import com.clementsu.framework.Screen;
import com.clementsu.framework.Graphics.ImageFormat;

// This class will load all the graphical images in the game.

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {

		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		Assets.background = g.newImage("background.png", ImageFormat.RGB565);
		Assets.parchment = g.newImage("parchment.png", ImageFormat.RGB565);
		Assets.character = g.newImage("default.png", ImageFormat.ARGB4444);
		Assets.character2 = g.newImage("default2.png", ImageFormat.ARGB4444);
		Assets.character3 = g.newImage("default3.png", ImageFormat.ARGB4444);
		Assets.character4 = g.newImage("default4.png", ImageFormat.ARGB4444);
		Assets.character5 = g.newImage("default5.png", ImageFormat.ARGB4444);
		Assets.characterJump = g.newImage("up.png", ImageFormat.ARGB4444);
		Assets.characterDown = g.newImage("down.png", ImageFormat.ARGB4444);
		Assets.characterHit = g.newImage("characterHit.png",
				ImageFormat.ARGB4444);
		Assets.characterHitup = g.newImage("characterHitup.png",
				ImageFormat.ARGB4444);
		Assets.characterHitdown = g.newImage("characterHitdown.png",
				ImageFormat.ARGB4444);

		Assets.fox = g.newImage("fox.png", ImageFormat.ARGB4444);
		Assets.fox2 = g.newImage("fox2.png", ImageFormat.ARGB4444);
		Assets.fox3 = g.newImage("fox3.png", ImageFormat.ARGB4444);
		Assets.fox4 = g.newImage("fox4.png", ImageFormat.ARGB4444);
		Assets.fox5 = g.newImage("fox5.png", ImageFormat.ARGB4444);
		Assets.foxdie1 = g.newImage("foxdie1.png", ImageFormat.ARGB4444);
		Assets.foxdie2 = g.newImage("foxdie2.png", ImageFormat.ARGB4444);
		Assets.foxdie3 = g.newImage("foxdie3.png", ImageFormat.ARGB4444);
		Assets.foxdie4 = g.newImage("foxdie4.png", ImageFormat.ARGB4444);
		Assets.foxdie5 = g.newImage("foxdie5.png", ImageFormat.ARGB4444);

		Assets.buttonup = g.newImage("buttonup.png", ImageFormat.RGB565);
		Assets.buttondown = g.newImage("buttondown.png", ImageFormat.RGB565);
		Assets.buttonleft = g.newImage("buttonleft.png", ImageFormat.RGB565);
		Assets.buttonright = g.newImage("buttonright.png", ImageFormat.RGB565);
		Assets.buttonpause = g.newImage("buttonpause.png", ImageFormat.RGB565);
		Assets.buttonfire = g.newImage("buttonfire.png", ImageFormat.RGB565);
		Assets.returnbutton = g
				.newImage("returnbutton.png", ImageFormat.RGB565);

		Assets.enemyBullet = g.newImage("enemyBullet.png", ImageFormat.RGB565);
		Assets.sheepBullet = g.newImage("sheepbullet.png", ImageFormat.RGB565);
		Assets.fire1 = g.newImage("fire1.png", ImageFormat.RGB565);
		Assets.fire2 = g.newImage("fire2.png", ImageFormat.RGB565);
		Assets.fire3 = g.newImage("fire3.png", ImageFormat.RGB565);
		Assets.heart = g.newImage("heart.png", ImageFormat.RGB565);

		// This is how you would load a sound if you had one.
		Assets.click = game.getAudio().createSound("laserSound.mp3");
		Assets.explode = game.getAudio().createSound("explosion.mp3");
		Assets.cannon = game.getAudio().createSound("cannon.mp3");
		Assets.health = game.getAudio().createSound("health.mp3");
		Assets.fire = game.getAudio().createSound("fireSound.mp3");

		MainMenuScreen.mainMenu = true;
		game.setScreen(new MainMenuScreen(game));

	}

	@Override
	public void paint(float deltaTime) { // The splash screen is displayed until
											// all the objects have done loading
											// and a new screen is created.
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);

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

	}
}