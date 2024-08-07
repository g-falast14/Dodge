package com.tutorial.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {

	public static Map<String, Sound> soundMap = new HashMap();
	public static Map<String, Music> musicMap = new HashMap();
	
	public static void load() {
		
		try {
			soundMap.put("menu_sound", new Sound("res/Select Sound Effect.wav"));
			musicMap.put("music", new Music("res/Hateno Village (Night) - The Legend of Zelda： Tears of the Kingdom OST.wav"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
	
}
