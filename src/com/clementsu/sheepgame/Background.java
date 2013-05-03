package com.clementsu.sheepgame;

//This class defines the background the scrolls in the game. In the main class, we make two
//background objects that repeat after one has done scrolling

public class Background {

	private int bgX, bgY, speedX;
	public static int distance = 0; // variable to keep track of the artificial
									// "distance" in the game.
	boolean enableCount = true;

	public Background(int x, int y) { // define the top left corner of the
										// background image
		bgX = x;
		bgY = y;
		speedX = 0;
	}

	public void update() { // change the coordinates of the background.
		bgX += speedX;
		distanceCounter();

		if (bgX <= -800) {
			bgX += 1600;
		}
	}

	public void distanceCounter() { //increments the distance.
		if (enableCount) {
			enableCount = false;
			distance += 1;
			distanceTimer();
		}
	}

	public void distanceTimer() { // sets the speed at which the distance
									// counts. Otherwise, it would count too
									// fast.

		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(50);
					enableCount = true;

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		timer.start();
	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

}
