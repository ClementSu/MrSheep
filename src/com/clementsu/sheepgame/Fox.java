package com.clementsu.sheepgame;

import android.graphics.Rect;

//Main enemy in the gain. Uses most of the methods from the enemy abstact class.

public class Fox extends Enemy {

	public Fox(int centerX, int centerY) { // initiate starting location

		setCenterX(centerX);
		setCenterY(centerY);
		if (centerY <= 240) { // need to create AI
			setStableUp(true); // if the location of fox is at a certain y
								// position, it goes up
			setStableDown(false); // otherwise it goes down

		}
		if (centerY >= 241) {
			setStableDown(true);
			setStableUp(false);

		}

	}

	// Behavioral Methods to update the sheep's functions
	public void update() {
		follow();
		centerX += speedX;
		centerY += speedY;

		speedX = -sheep.MOVESPEED / 2 + movementSpeedX;
		r.set(centerX - 25, centerY - 25, centerX + 25, centerY + 30);

		if (sheep.getCenterY() >= centerY - 100
				&& sheep.getCenterY() <= centerY + 100 && readyToFire
				&& health > 0) {
			shoot();
		}

	}

	protected void follow() { // defines what happens when the fox is at certain
								// locations of the screen and how it relates to
								// the position of the sheep
		if (health > 0) {
			if (centerX < -95 || centerX > 1505) {
				movementSpeedX = 0;
				speedY = 0;

			} else if (centerX > 800) {
				movementSpeedX = -1;

			} else if (speedX > 1 && centerX == 800) {
				speedX = -1;

			}

			else if (Math.abs(centerX - sheep.getCenterX()) >= 350
					&& centerX < 800) {
				movementSpeedX = -1;

			}

			else if (centerX < 450) {
				speedX = 1;
			}

			else {
				speedX = 1 + speedY;
			}

			if (centerY <= 20) {
				enableUp = false;
				enableDown = true;
			}
			if (centerY >= 460) {
				enableDown = false;
				enableUp = true;
			}

			if (centerY >= 0 && enableDown) {
				speedY = 2; // go down
			} else if (centerY <= 480 && enableUp) {
				speedY = -2; // go up
			}

		} else {
			speedX = 0;
			speedY = 4;
			if (soundplayed == false) {
				Assets.explode.play(1); // play the sound when the foxes health
										// is zero.
				soundplayed = true;
			}
			if (centerY > 523) {
				centerX = -100;
			}
		}
	}

	public void resetStable() { // the foxes are actually never deleted when
								// they "die." They just get moved off screen.
								// This method resets the foxes to original
								// conditions if I want another fox to appear.
		health = 3;
		soundplayed = false;
		if (getCenterY() <= 240) {
			setStableUp(true);
			setStableDown(false);

		}
		if (getCenterY() >= 241) {
			setStableDown(true);
			setStableUp(false);

		}
	}

}
