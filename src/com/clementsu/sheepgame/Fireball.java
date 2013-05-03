package com.clementsu.sheepgame;

import java.util.Random;

//Extends the enemy class, though it doesn't use all of the methods from enemy such as shoot()

import android.graphics.Rect;

public class Fireball extends Enemy {

	private boolean isExploded;
	Random rand = new Random();

	public Fireball(int centerX, int centerY) { // initiate the position of the
												// fireball

		setCenterX(centerX);
		setCenterY(centerY);
		isExploded = false;

	}

	// Behavioral Methods. Basically sends a fireball across the screen and
	// plays the sound if the fireball is in the right place.
	public void update() {
		flyAway();
		centerX -= speedX;

		r.set(centerX - 11, centerY - 11, centerX + 11, centerY + 11);
		if (centerX < -95) {
			isExploded = true;
			speedX = 0;
		}

		if (centerX < 800 && soundplayed == false) {
			Assets.fire.play(1);
			soundplayed = true;
		}

		if (Rect.intersects(r, Sheep.bigBox)) {
			checkCollision();
		}

	}

	protected void flyAway() { // defines the "speed" at which the fireball
								// flies.
		if (centerX >= -95) {
			speedX = 8;

		} else {
			soundplayed = true;
			speedX = 0;
		}

	}

	public void resetStable() { // method called just in case the fireball
								// object doesn't want to be removed by
								// attributes still reseted
		soundplayed = false;
		isExploded = false;
		speedX = 8;
		int xposition = rand.nextInt(20);
		centerX += xposition;
	}

	private void checkCollision() { //checks the hit boxes of the sheep and fireball.
		if (isExploded == false) {
			if (Rect.intersects(r, Sheep.rect)) {
				Sheep.health -= 3;
				isExploded = true;
				// add sound here
				soundplayed = true;
			}
		}

	}

	public boolean isExploded() {
		return isExploded;
	}

	public void setExploded(boolean isExploded) {
		this.isExploded = isExploded;
	}

}
