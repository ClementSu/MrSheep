package com.clementsu.sheepgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

import com.clementsu.framework.Screen;
import com.clementsu.framework.implementation.AndroidGame;
import com.clementsu.sheepgame.R;

//This class defines what happens what the game first starts.

public final class SampleGame extends AndroidGame {
	public static String raw;
	public static int highscores;
	boolean firstTimeCreate = true;

	@Override
	public Screen getInitScreen() {

		if (firstTimeCreate) {
			Assets.load(this);

			firstTimeCreate = false;
		}
		
		 InputStream is = getResources().openRawResource(R.raw.highscores);
	       raw = convertStreamToString(is);
	       
	       try {
	    	    highscores = Integer.parseInt(raw);
	    	} catch(NumberFormatException nfe) {
	    	   System.out.println("Could not parse " + nfe);
	    	} 
	       //highscores = Integer.parseInt(raw);

		return new SplashLoadingScreen(this);

	}

	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			}
		} catch (IOException e) {
			Log.w("LOG", e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.w("LOG", e.getMessage());
			}
		}
		return sb.toString();
	}

	@Override
	public void onResume() { //play music when game starts.
		super.onResume();
		if (MainMenuScreen.mainMenu == true) {
			Assets.theme.play();
		} else if (GameScreen.gameScreen == true) {
			Assets.gameTheme.play();
		} else {
			Assets.theme.play();
		}

	}

	@Override
	public void onPause() { //pause music when game pauses.
		super.onPause();
		Assets.theme.pause();
		Assets.gameTheme.pause();

	}

}