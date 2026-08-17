package com.github.msx80.doorsofdoom.model;

import com.github.msx80.doorsofdoom.DoorsOfDoom;

public enum Place {
	
	
	
	Left(2, 18, 257), 
	Right(30, 18, 258), 
	Head(16, 2, 256), 
	Body(16, 16, 259), 
	Legs(16, 30, 260);
	
	public int x;
	public int y;
	public int defaultSprite;
	
	public static final int EQUIP_START_X = DoorsOfDoom.STATS_X+3;
	public static final int EQUIP_START_Y = 50;
	
	
	private Place(int x, int y, int defaultSprite) {
		this.x = x;
		this.y = y;
		this.defaultSprite = defaultSprite;
	}	
}
