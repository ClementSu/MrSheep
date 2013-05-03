package com.clementsu.sheepgame;

import android.graphics.Rect;

//The framework of this class is based off of the "Projectile" class.

public class EnemyProjectile {

	private int x, y, speedX;
	private boolean visible;

	private Rect r;

	public EnemyProjectile(int startX, int startY) {
		x = startX;
		y = startY;
		speedX = 7;
		visible = true;

		r = new Rect(0, 0, 0, 0); // initiate a rectangle object that will
									// define the projectile's hit box.
	}

	public void update() {
		x -= speedX;
		r.set(x, y, x + 20, y + 12); // define the hit box
		if (x > 820) { // this statement ensures that bullets only exists in the
						// range of the screen
			visible = false;
			r = null;

		}
		if (visible && Rect.intersects(r, Sheep.bigBox)) { //if hit boxes collide, call check collision
			checkCollision();
		}

	}

	private void checkCollision() {

		if (Rect.intersects(r, Sheep.rect) || Rect.intersects(r, Sheep.rect2)) {
			visible = false;

			if (Sheep.health > 0) {
				Sheep.health -= 1;  //decrement's the sheep's health
				Sheep.isSheepHit = true;

			}

		}

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
