package com.clementsu.sheepgame;

import java.util.ArrayList;

import android.graphics.Rect;

//This is the main character.

public class Sheep {

	// Constants are Here
	public final int UPSPEED = -7;
	public final int MOVESPEED = 5;

	private int centerX = 100;
	private int centerY = 200;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingUp = false;
	private boolean movingDown = false;
	private boolean readyToFire = true;
	public static boolean isSheepHit = false;

	private int speedX = 0;
	private int speedY = 0;
	public static int health = 10;
	public static Rect rect = new Rect(0, 0, 0, 0); // rect and rect2 are the
													// plane's hitboxes.
	public static Rect rect2 = new Rect(0, 0, 0, 0);
	public static Rect bigBox = new Rect(0, 0, 0, 0); // only check for
														// collisions within big
														// box

	private Background bg1 = GameScreen.getBg1();
	private Background bg2 = GameScreen.getBg2();

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public void update() {
		// Moves Character or Scrolls Background accordingly.


		bg1.setSpeedX(-MOVESPEED);
		bg2.setSpeedX(-MOVESPEED);
		if (speedX > 0) { // move right
			centerX += speedX;
		}

		if (speedX < 0) { // move left
			centerX += speedX;
		}


		if (speedY < 0) { // move up
			centerY += speedY;
		}

		if (speedY > 0) { // move down
			centerY += speedY;
		}

		// Prevents going beyond boundaries of screen
		if (centerX + speedX <= 47) {
			centerX = 48;
		}

		if (centerX + speedX >= 710) {
			centerX = 709;
		}

		if (centerY + speedY <= 30) {
			centerY = 31;
		}
		if (centerY + speedY >= 446) {
			centerY = 445;
		}
		isSheepHit = false;
		rect.set(centerX - 30, centerY, centerX + 24, centerY + 27);
		rect2.set(centerX - 16, centerY - 23, centerX + 17, centerY);
		bigBox.set(centerX - 110, centerY - 110, centerX + 70, centerY + 70);

	}

	public void moveRight() {
		speedX = MOVESPEED;
	}

	public void moveLeft() {
		speedX = -MOVESPEED;
	}

	public void moveUp() {
		speedY = UPSPEED;
	}

	public void moveDown() {
		speedY = -UPSPEED;
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	public void stopUp() {
		setMovingUp(false);
		stop();
	}

	public void stopDown() {
		setMovingDown(false);
		stop();
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingUp() == false && isMovingDown() == false) {
			speedY = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}

		if (isMovingUp() == false && isMovingDown() == true) {
			moveDown();
		}

		if (isMovingUp() == true && isMovingDown() == false) {
			moveUp();
		}

	}

	public void shoot() {
		readyToFire = false;
		Projectile p = new Projectile(centerX + 17, centerY + 11);
		projectiles.add(p);
		Assets.click.play(1);
		time();
	}

	public void time() {

		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(200);
					readyToFire = true;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		timer.start();
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}

	public ArrayList getProjectiles() {
		return projectiles;
	}

	public boolean isReadytoFire() {
		return readyToFire;
	}

	public void setReadytoFire(boolean readytoFire) {
		this.readyToFire = readytoFire;
	}

}
