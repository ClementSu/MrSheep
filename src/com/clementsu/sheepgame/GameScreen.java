package com.clementsu.sheepgame;

import java.util.*;

import android.graphics.Color;
import android.graphics.Paint;

import com.clementsu.framework.Game;
import com.clementsu.framework.Graphics;
import com.clementsu.framework.Image;
import com.clementsu.framework.Screen;
import com.clementsu.framework.Input.TouchEvent;

//The most important class. Creates what we see when we play the game.

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver // creates several "states" to define
											// what methods are certain states.
	}

	GameState state = GameState.Ready;

	// Variable Setup
	Random rand = new Random(); // random number for enemy generation
	private static Background bg1, bg2;
	private static Sheep sheep;
	public Heart heart;
	public static int score = 0;
	public int finalScore;
	public static int enemyCount = 0;
	public static int deathCount = 10;
	public int foxSpawn = 1;
	private int totalFox = 10;

	private int totalFireball = 3;

	private boolean alreadyExecuted1, alreadyExecuted2, alreadyExecuted3,
			alreadyExecuted4, alreadyExecuted5, alreadyExecuted6,
			alreadyExecuted7, alreadyExecuted8, alreadyExecuted9,
			alreadyExecuted10, fireballExecute, goHeart = false;
	public boolean isPlayingMusic = false;
	public static boolean gameScreen = false;
	public static ArrayList<Fox> foxes = new ArrayList<Fox>();
	public static ArrayList<Fireball> fireballs = new ArrayList<Fireball>();

	private Image character, character2, character3, character4, character5,
			fox, fox2, fox3, fox4, fox5, foxdie1, foxdie2, foxdie3, foxdie4,
			foxdie5, fire1, fire2, fire3;
	public Image currentSprite;
	private Animation anim, hanim, hanimdie, afire;

	Paint paint, paint2, paint3, paint4;

	public GameScreen(Game game) {
		super(game);

		// All the game objects are initialized here

		bg1 = new Background(0, 0);
		bg2 = new Background(800, 0);
		sheep = new Sheep();
		for (int i = 0; i < totalFox; i++) {
			int yposition = rand.nextInt(330) + 100; // generate y coordinate
			// from 51 - 430
			foxes.add(new Fox(-100, yposition)); // add new foxes at random y
													// coordinates
		}
		heart = new Heart(-100, 100); // create a new "Heart" object that will
										// appear periodically

		character = Assets.character; // define the images that will be used as
										// graphics
		character2 = Assets.character2;
		character3 = Assets.character3;
		character4 = Assets.character4;
		character5 = Assets.character5;

		fox = Assets.fox;
		fox2 = Assets.fox2;
		fox3 = Assets.fox3;
		fox4 = Assets.fox4;
		fox5 = Assets.fox5;
		foxdie1 = Assets.foxdie1;
		foxdie2 = Assets.foxdie2;
		foxdie3 = Assets.foxdie3;
		foxdie4 = Assets.foxdie4;
		foxdie5 = Assets.foxdie5;

		fire1 = Assets.fire1;
		fire2 = Assets.fire2;
		fire3 = Assets.fire3;

		afire = new Animation(); // create a new Animation object
		afire.addFrame(fire1, 250); // each of the images are added to
									// animation. These animations will keep
									// running in the background. We only see
									// them once they are "painted."
		afire.addFrame(fire2, 250);
		afire.addFrame(fire3, 250);

		anim = new Animation();
		anim.addFrame(character, 50);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character4, 50);
		anim.addFrame(character5, 50);

		hanim = new Animation();
		hanim.addFrame(fox, 100);
		hanim.addFrame(fox2, 100);
		hanim.addFrame(fox3, 100);
		hanim.addFrame(fox4, 100);
		hanim.addFrame(fox5, 100);
		hanim.addFrame(fox4, 100);
		hanim.addFrame(fox3, 100);
		hanim.addFrame(fox2, 100);

		hanimdie = new Animation();
		hanimdie.addFrame(foxdie1, 250);
		hanimdie.addFrame(foxdie2, 250);
		hanimdie.addFrame(foxdie3, 250);
		hanimdie.addFrame(foxdie4, 250);
		hanimdie.addFrame(foxdie5, 250);
		hanimdie.addFrame(foxdie4, 250);
		hanimdie.addFrame(foxdie3, 250);
		hanimdie.addFrame(foxdie2, 250);

		currentSprite = anim.getImage(); // the currentSprite is what we
											// currently see of our "sheep"
											// class

		// Defining a paint object. In other words, we make new "fonts."
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);

		paint3 = new Paint();
		paint3.setTextSize(30);
		paint3.setTextAlign(Paint.Align.CENTER);
		paint3.setAntiAlias(true);
		paint3.setColor(Color.RED);

		paint4 = new Paint();
		paint4.setTextSize(60);
		paint4.setTextAlign(Paint.Align.CENTER);
		paint4.setAntiAlias(true);
		paint4.setColor(Color.WHITE);

	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {

		// The first state when the GameScreen starts is "Ready." The game only
		// knows these methods until the screen is touched. Then the "Running"
		// state is initialized.

		if (touchEvents.size() > 0)
			state = GameState.Running;

	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

		// This update method is what updates the core of the game. It takes in
		// parameters "TouchEvents" to read the user inputs. It also takes in a
		// float that represents the ongoing and running game.

		finalScore = score + Background.distance;

		// 1. All touch input is handled here:
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DRAGGED
					|| event.type == TouchEvent.TOUCH_DOWN) {

				if (inBounds(event, 65, 350, 65, 65)) {
					sheep.moveUp();
					sheep.setMovingUp(true);
					currentSprite = Assets.characterJump;

				}

				else if (inBounds(event, 65, 415, 65, 65)) {
					currentSprite = Assets.characterDown;
					sheep.moveDown();
					sheep.setMovingDown(true);

				}

				else if (inBounds(event, 130, 383, 65, 65)) {
					sheep.moveRight();
					sheep.setMovingRight(true);

				}

				else if (inBounds(event, 0, 383, 65, 65)) {
					sheep.moveLeft();
					sheep.setMovingLeft(true);
				}
			}
			if (event.type == TouchEvent.TOUCH_DOWN) {

				if (inBounds(event, 703, 383, 65, 65)) {
					// shoot
					if (sheep.isReadytoFire()) {
						sheep.shoot();
					}
				}

			}

			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 0, 0, 499, 480)) {
					sheep.stopUp();
				}

				if (inBounds(event, 0, 0, 499, 480)) {
					currentSprite = anim.getImage();
					sheep.stopDown();

				}

				if (inBounds(event, 0, 0, 499, 480)) {
					sheep.stopLeft();
				}

				if (inBounds(event, 0, 0, 499, 480)) {
					sheep.stopRight();
				}

				if (inBounds(event, 0, 0, 35, 35)) {
					pause();

				}

			}

		}

		// 2. Check miscellaneous events like death:

		if (Sheep.health < 1) {
			state = GameState.GameOver;
		}

		// 3. Call individual update() methods here.
		// This is where all the game updates happen.

		generateEnemies(); // This method "generates" enemies

		// define the Current Sprite of the sheep.

		if (sheep.isMovingUp() && sheep.isMovingDown() == false
				&& Sheep.isSheepHit == false) {
			currentSprite = Assets.characterJump;
		}
		if (sheep.isMovingDown() && sheep.isMovingUp() == false
				&& Sheep.isSheepHit == false) {
			currentSprite = Assets.characterDown;
		}

		if (sheep.isMovingUp() && sheep.isMovingDown() == false
				&& Sheep.isSheepHit == true) {
			currentSprite = Assets.characterHitup;
		}
		if (sheep.isMovingDown() && sheep.isMovingUp() == false
				&& Sheep.isSheepHit == true) {
			currentSprite = Assets.characterHitdown;
		}

		if (Sheep.isSheepHit == true && sheep.isMovingUp() == false
				&& sheep.isMovingDown() == false) {
			currentSprite = Assets.characterHit;

		} else if (sheep.isMovingUp() == false && sheep.isMovingDown() == false
				&& Sheep.isSheepHit == false) {
			currentSprite = anim.getImage();
		}

		// //////////////////////////////////////////////////////////
		animate(); // This method will "paint" the current animation objects.
		sheep.update(); // updates sheep characteristics
		heart.update(); // updates heart characteristics

		// Go through the list
		// of all the
		// projectiles. It only
		// updates if the
		// projectile is
		// "visible." Otherwise
		// the projectile will
		// be removed.
		ArrayList projectiles = sheep.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			if (p.isVisible() == true) {
				p.update();
			} else {
				projectiles.remove(i);
			}
		}

		for (int i = 0; i < foxes.size(); i++) { // same for enemy projectiles.
			foxes.get(i).eprojectiles3 = foxes.get(i).getProjectiles();
			for (int j = 0; j < foxes.get(i).eprojectiles3.size(); j++) {
				EnemyProjectile ep = (EnemyProjectile) foxes.get(i).eprojectiles3
						.get(j);
				if (ep.isVisible() == true) {
					ep.update();
				} else {
					foxes.get(i).eprojectiles3.remove(j);
				}
			}
		}

		for (int i = 0; i < foxes.size(); i++) {
			foxes.get(i).update();
		}

		for (int i = 0; i < fireballs.size(); i++) {
			if (fireballs.get(i).isExploded() == false) {
				fireballs.get(i).update();
			} else {
				fireballs.remove(i);
			}

		}

		bg1.update(); // update background.
		bg2.update(); // update second background.

	}

	public void generateEnemies() { // generates enemies and defines what
									// happens at certain "distances" of the
									// game.

		if (Background.distance >= 800 && Background.distance <= 1500
				&& alreadyExecuted1 == false) {

			foxSpawn += 2;
			totalFireball = 4;

			alreadyExecuted1 = true;
		}
		if (Background.distance >= 2300 && Background.distance + score <= 2800
				&& alreadyExecuted2 == false) {

			foxSpawn += 3;
			totalFireball = 4;

			alreadyExecuted2 = true;
		}
		if (Background.distance >= 4500 && Background.distance <= 6000
				&& alreadyExecuted3 == false) {

			foxSpawn += 4;
			totalFireball = 5;
			alreadyExecuted3 = true;
		}
		if (Background.distance >= 7000 && Background.distance <= 7500
				&& alreadyExecuted4 == false) {

			foxSpawn += 4;
			totalFireball = 6;

			alreadyExecuted4 = true;
		}
		if (Background.distance >= 8000 && Background.distance <= 8500
				&& alreadyExecuted5 == false) {

			foxSpawn += 4;
			totalFireball = 6;

			alreadyExecuted5 = true;
		}

		if (Background.distance > 1000) {

			if (Background.distance % 250 == 0 && fireballExecute == false) {
				generateFire();
			}

			if (Background.distance % 500 == 0) {
				generateHeart();
			}
		}

		for (int i = 0; i < totalFox; i++) {
			if (enemyCount < foxSpawn && foxes.get(i).getCenterX() <= -95) {
				enemyCount++;
				deathCount--;
				int yposition = rand.nextInt(9) + 1; // generate y
														// coordinate
				// from 0- 479

				foxes.get(i).setCenterY(yposition * 48);
				foxes.get(i).setCenterX(1500);
				foxes.get(i).resetStable();

			} else
				break;
		}

	}

	public void generateHeart() { // randomly generates the location of the
									// heart.
		goHeart = true;
		if (goHeart == true) {
			int yposition = rand.nextInt(1) + 240;
			heart.setCenterX(1500);
			heart.setCenterY(yposition);
			heart.resetStable();
		}
		goHeart = false;
	}

	public void generateFire() { // randomly generates the location of the
									// fireball.
		fireballExecute = true;
		if (fireballExecute == true) {
			for (int i = 0; i < totalFireball; i++) {

				int yposition = rand.nextInt(9) + 1;
				int xposition = rand.nextInt(2) + 1;
				fireballs.add(new Fireball(1500 - xposition * 100,
						yposition * 48));
			}
			fireballExecute = false;

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

	private void updatePaused(List<TouchEvent> touchEvents) { // defines the
																// methods that
																// apply when
																// the current
																// state is
																// "pause."
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 35, 35)) {
						resume();
					}
				}

				if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					resetGameConditions();
					Assets.gameTheme.pause();
					gameScreen = false;
					MainMenuScreen.mainMenu = true;
					goToMenu();
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) { // defines the
																// method when
																// the current
																// state is game
																// over.
		int len = touchEvents.size();

		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 350, 350, 101, 101)) {
					nullify();
					resetGameConditions();
					Assets.gameTheme.pause();
					gameScreen = false;
					MainMenuScreen.mainMenu = true;
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}

	}

	@Override
	public void paint(float deltaTime) { // Along with the updateRunning()
											// method, this method is called as
											// "time" changes. This method
											// paints the graphics.
		Graphics g = game.getGraphics();

		g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
		g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());
		g.drawImage(Assets.heart, heart.getCenterX() - 15,
				heart.getCenterY() - 15, 0, 0, 33, 40);

		ArrayList projectiles = sheep.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.drawImage(Assets.sheepBullet, p.getX(), p.getY(), 0, 0, 11, 3);
			// g.drawRect(p.getX(), p.getY(), 10, 5, Color.RED);
		}

		for (int i = 0; i < foxes.size(); i++) {
			foxes.get(i).eprojectiles2 = foxes.get(i).getProjectiles();
			for (int j = 0; j < foxes.get(i).eprojectiles2.size(); j++) {
				EnemyProjectile ep2 = (EnemyProjectile) foxes.get(i).eprojectiles2
						.get(j);
				g.drawImage(Assets.enemyBullet, ep2.getX() - 2, ep2.getY() - 2,
						0, 0, 29, 14);
				// g.drawRect(ep2.getX(), ep2.getY(), 20, 10, Color.RED);
			}
		}

		for (int i = 0; i < fireballs.size(); i++) {
			g.drawImage(afire.getImage(), fireballs.get(i).getCenterX() - 11,
					fireballs.get(i).getCenterY() - 11);
		}

		// First draw the game elements.

		g.drawImage(currentSprite, sheep.getCenterX() - 47,
				sheep.getCenterY() - 33);

		for (int i = 0; i < foxes.size(); i++) {
			if (foxes.get(i).health < 1) {
				g.drawImage(hanimdie.getImage(),
						foxes.get(i).getCenterX() - 48, foxes.get(i)
								.getCenterY() - 48);
			} else {
				g.drawImage(hanim.getImage(), foxes.get(i).getCenterX() - 48,
						foxes.get(i).getCenterY() - 48);
			}

		}

		// Example:
		// g.drawImage(Assets.background, 0, 0);
		// g.drawImage(Assets.character, characterX, characterY);

		// Secondly, draw the UI above the game elements.
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();

		// draw score

		g.drawString("Score: " + Integer.toString(score), 700, 30, paint);
		g.drawString("Health: " + Integer.toString(Sheep.health), 120, 30,
				paint3);
		g.drawString("Distance: " + Integer.toString(Background.distance), 430,
				30, paint);

	}

	public void animate() { // updates the frames in the Animation class.
		anim.update(10);
		hanim.update(50);
		hanimdie.update(50);
		afire.update(50);
	}

	public void resetGameConditions() { // after the user exits the gameScreen,
										// this method is called to reset all
										// the static variables.
		score = 0;
		Background.distance = 0;
		enemyCount = 0;
		deathCount = 20;
		Sheep.health = 10;
		// foxSpawn = 1;
		isPlayingMusic = false;

	}

	private void nullify() { // removes everything when the game ends.

		// Set all variables to null. You will be recreating them in the
		// constructor.
		paint = null;
		bg1 = null;
		bg2 = null;
		sheep = null;
		currentSprite = null;
		character = null;
		character2 = null;
		character3 = null;
		character4 = null;
		character5 = null;
		heart = null;
		fox = null;
		fox2 = null;
		fox3 = null;
		fox4 = null;
		fox5 = null;
		anim = null;
		hanim = null;
		hanimdie = null;
		afire = null;
		foxes.clear();
		fireballs.clear();

		// Call garbage collector to clean up memory.
		System.gc();

	}

	private void drawReadyUI() { //Draw what we see in the "Ready" state.

		Graphics g = game.getGraphics();
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Survive as long as you can to get a high score!", 400,
				150, paint);
		g.drawString("Use the Arrow Keys to Move", 400, 200, paint);
		g.drawString("Press the Fire Button to Fire", 400, 250, paint);
		g.drawString("TAP TO START!", 400, 400, paint2);

		Assets.gameTheme.start();

	}

	private void drawRunningUI() { //Draw what we see in the "Running" state.
		Graphics g = game.getGraphics();
		// g.drawImage(Assets.button, 0, 285, 0, 0, 65, 65);
		// g.drawImage(Assets.button, 0, 350, 0, 65, 65, 65);
		// g.drawImage(Assets.button, 0, 415, 0, 130, 65, 65);
		// g.drawImage(Assets.button, 0, 0, 0, 195, 35, 35);
		g.drawImage(Assets.buttonup, 65, 350, 0, 0, 65, 65);
		g.drawImage(Assets.buttondown, 65, 415, 0, 0, 65, 65);
		g.drawImage(Assets.buttonleft, 0, 383, 0, 0, 65, 65);
		g.drawImage(Assets.buttonright, 130, 383, 0, 0, 65, 65);
		g.drawImage(Assets.buttonfire, 703, 383, 0, 0, 65, 65);
		g.drawImage(Assets.buttonpause, 0, 0, 0, 0, 35, 35);

	}

	private void drawPausedUI() { //Draw what we see in the "Paused" state.
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 400, 165, paint2);
		g.drawString("Menu", 400, 360, paint2);

	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		// g.drawRect(0, 0, 1281, 801, Color.BLACK);
		g.drawARGB(155, 155, 0, 0);
		// if (finalScore > SampleGame.highscores){
		// g.drawString("NEW HIGH SCORE!!!: " + Integer.toString(finalScore),
		// 400, 240, paint4);
		// g.drawImage(Assets.returnbutton, 350, 350, 0, 0, 101, 101);
		// }else{
		g.drawString("GAME OVER.", 400, 240, paint2);
		g.drawString("Final Score: " + Integer.toString(finalScore), 400, 290,
				paint);
		g.drawImage(Assets.returnbutton, 350, 350, 0, 0, 101, 101);
		// }

	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game));

	}

	public static Background getBg1() {
		// TODO Auto-generated method stub
		return bg1;
	}

	public static Background getBg2() {
		// TODO Auto-generated method stub
		return bg2;
	}

	public static Sheep getSheep() {
		// TODO Auto-generated method stub
		return sheep;
	}

}