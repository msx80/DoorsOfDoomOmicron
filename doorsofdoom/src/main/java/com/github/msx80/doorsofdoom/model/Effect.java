package com.github.msx80.doorsofdoom.model;

public enum Effect {

	SMOKE("SMOKE", 270, 6),
	LUCKY("LUCKY", 287, 9),
	GHOSTLY("GHOSTLY", 271, 20),
	MUSCLES("MUSCLES", 268, 15),
	MAGNETIC("MAGNETIC", 267, 10),
	REGENERATION("REGENERATION", 284, 10),
	MADNESS("MADNESS", 339, 8),
	BARRIER("MAGIC BARRIER", 344, 15)
	;
	
	public final String name;
	public final int sprite;
	public final int turns;
	
	private Effect(String name, int sprite, int turns) {
		this.name = name;
		this.sprite = sprite;
		this.turns = turns;
	}
	
	/*
	SMOKE = {
		spr = 270,
		name = "SMOKE",
		turns = 4
	},
	LUCKY = {
		spr = 287,
		name = "LUCKY",
		turns = 9
	},
	GHOSTLY = {
		spr = 271,
		name = "GHOSTLY",
		turns = 8
	},
	MUSCLES = {
		spr = 268,
		name = "MUSCLES",
		turns = 15
	},
	MAGNETIC = {
		spr = 267,
		name = "MAGNETIC",
		turns = 10
	},
	REGENERATION = {
		spr = 284,
		name = "REGENERATION",
		turns = 10
	},
	*/
}
