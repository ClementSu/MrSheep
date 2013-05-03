package com.clementsu.sheepgame;

import java.util.ArrayList;

import com.clementsu.framework.Image;

// This class is the class that handles all the sprite animations.
public class Animation {

	private ArrayList frames;
	private int currentFrame;
	private long animTime;
	private long totalDuration;

	public Animation() {
		frames = new ArrayList(); //array that stores the frames of the image
		totalDuration = 0; // how long I want the frame to be

		synchronized (this) {
			animTime = 0;  //total time that has occurred
			currentFrame = 0; //the current frame
		}
	}

	public synchronized void addFrame(Image image, long duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}

	public synchronized void update(long elapsedTime) { // updates each frame according to the duration
		if (frames.size() > 1) {
			animTime += elapsedTime;
			if (animTime >= totalDuration) {
				animTime = animTime % totalDuration;
				currentFrame = 0;

			}

			while (animTime > getFrame(currentFrame).endTime) { //fetch each frame after the duration has ended
				currentFrame++;

			}
		}
	}

	public synchronized Image getImage() { // get the current frame
		if (frames.size() == 0) {
			return null;
		} else {
			return getFrame(currentFrame).image;
		}
	}

	private AnimFrame getFrame(int i) {
		return (AnimFrame) frames.get(i);
	}

	private class AnimFrame {

		Image image;
		long endTime;

		public AnimFrame(Image image, long endTime) {
			this.image = image;
			this.endTime = endTime;
		}
	}
}
