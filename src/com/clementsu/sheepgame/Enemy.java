package com.clementsu.sheepgame;

import java.util.*;

import android.graphics.Rect;

//This is an abstract class where the "Fireball" and "Fox" classes are derived from

public abstract class Enemy {

	// initiate an array to hold "EnemyProjectile" objects. Every time the shoot
	// function is called,
	// a projectile object gets added to the array.

	protected ArrayList<EnemyProjectile> eprojectiles = new ArrayList<EnemyProjectile>();
	public ArrayList eprojectiles3 = new ArrayList();
	public ArrayList eprojectiles2 = new ArrayList();

	private int power;
	protected int centerX;
	protected int speedX;
	protected int speedY;
	protected int centerY;
	protected boolean enableUp, enableDown;
	protected boolean readyToFire = true;
	protected boolean readyToGenerate = false;
	protected Background bg = GameScreen.getBg1();
	protected Sheep sheep = GameScreen.getSheep();
	public boolean soundplayed = false;

	public Rect r = new Rect(0, 0, 0, 0);
	public int health = 3;

	protected int movementSpeedX;

	public void shoot() { // the enemy shoots an "EnemyProjectile" object.
		if (readyToFire && centerX > -95 && centerX < 810) {
			speedY = 0;
			readyToFire = false;
			EnemyProjectile p = new EnemyProjectile(centerX + 17, centerY + 11);
			eprojectiles.add(p);
			fireTime();
			Assets.cannon.play(1); // play the sound when the shoot function is
									// calls

		}
	}

	public void fireTime() { // Dictates the time between each firing sequence.

		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(600);
					readyToFire = true;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		timer.start();
	}

	public void die() {

	}

	public void attack() {

	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

	public boolean isStableUp() {
		return enableUp;
	}

	public boolean isStableDown() {
		return enableDown;
	}

	public void setStableUp(boolean stableUp) {
		this.enableUp = stableUp;
	}

	public void setStableDown(boolean stableDown) {
		this.enableDown = stableDown;
	}

	public ArrayList getProjectiles() {
		return eprojectiles;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isReadyToGenerate() {
		return readyToGenerate;
	}

	public void setReadyToGenerate(boolean readyToGenerate) {
		this.readyToGenerate = readyToGenerate;
	}
}
