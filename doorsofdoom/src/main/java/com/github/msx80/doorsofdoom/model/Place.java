package com.github.msx80.doorsofdoom.model;

public enum Place {
	Left(2, 18, 257), 
	Right(30,18, 258), 
	Head(16,2,256), 
	Body(16,16,259), 
	Legs(16,30, 260);
	
	public int x;
	public int y;
	public int defaultSprite;
	
	private Place(int x, int y, int defaultSprite) {
		this.x = x;
		this.y = y;
		this.defaultSprite = defaultSprite;
	}
	
}
