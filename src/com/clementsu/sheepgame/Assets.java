package com.clementsu.sheepgame;

import com.clementsu.framework.Image;
import com.clementsu.framework.Music;
import com.clementsu.framework.Sound;

//This class defines all the image, sound, and music variables that will be referenced in the game

public class Assets {

	public static Image menu, splash, background, parchment, character, character2,
			character3, character4, character5, fox, fox2, fox3,
			fox4, fox5, foxdie1, foxdie2, foxdie3, foxdie4, foxdie5;
	public static Image characterJump, characterDown, characterHit,
			characterHitdown, characterHitup;
	public static Image buttonup, buttondown, buttonleft, buttonright,
			buttonfire, buttonpause, returnbutton;
	public static Image enemyBullet, fire1, fire2, fire3, heart, sheepBullet;
	public static Sound click, explode, cannon, fire, health;
	public static Music theme, gameTheme;

	public static void load(SampleGame sampleGame) {
		// TODO Auto-generated method stub
		theme = sampleGame.getAudio().createMusic("menutheme.mp3"); //fetch the music file
		theme.setLooping(true);
		theme.setVolume(0.85f);
		theme.play();
		gameTheme = sampleGame.getAudio().createMusic("gametheme.mp3"); //fetch the music file
		gameTheme.setLooping(true);
		gameTheme.setVolume(0.85f);
		gameTheme.stop();
	}

}
