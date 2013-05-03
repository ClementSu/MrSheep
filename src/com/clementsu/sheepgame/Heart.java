package com.clementsu.sheepgame;

import java.util.Random;
import android.graphics.Rect;

// A separate class that defines a heart object that moves across the screen up and down based on it's 
//current y coordinates. It heals the sheep when the hitboxes touch.

public class Heart {

	private int centerX, centerY, speedX, speedY;
	Random rand = new Random();
	private boolean isHealed;
	private boolean enableUp, enableDown;

	public Rect r = new Rect(0, 0, 0, 0);

	public Heart(int centerX, int centerY) {

		setCenterX(centerX);
		setCenterY(centerY);
		isHealed = false;
		if (centerY <= 240) {
			enableUp = true;
			enableDown = false;
		}
		if (centerY >= 241) {
			enableDown = true;
			enableUp = false;
		}

	}

	// Behavioral Methods
	public void update() {
		flyAway();
		centerX -= speedX;
		centerY += speedY;

		r.set(centerX - 15, centerY - 15, centerX + 15, centerY + 15);
		if (centerX < -95) {

			speedX = 0;
		}

		if (Rect.intersects(r, Sheep.bigBox)) {
			checkCollision();
		}

	}

	protected void flyAway() {
		if (centerX >= -95) {
			speedX = 5;

		} else {
			speedX = 0;
		}

		if (centerY <= 120) {
			enableUp = false;
			enableDown = true;
		}
		if (centerY >= 360) {
			enableDown = false;
			enableUp = true;
		}

		if (centerY >= 0 && enableDown) {
			speedY = 4; // go down
		} else if (centerY <= 480 && enableUp) {
			speedY = -4; // go up
		}

	}

	private void checkCollision() {
		if (isHealed == false && Sheep.health <= 8) {
			if (Rect.intersects(r, Sheep.rect)) {
				Sheep.health += 2;
				centerX = -100;
				Assets.health.play(1);
				isHealed = true;
				// add sound here

			}
		}

	}

	public void resetStable() {
		isHealed = false;
		if (centerY <= 240) {
			enableUp = true;
			enableDown = false;
		}
		if (centerY >= 241) {
			enableDown = true;
			enableUp = false;
		}
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

}