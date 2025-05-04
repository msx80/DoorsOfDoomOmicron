package com.github.msx80.doorsofdoom.model;

public enum Effect {

	SMOKE("SMOKE", 270, 6, true),
	LUCKY("LUCKY", 287, 9, true),
	GHOSTLY("GHOSTLY", 271, 20, true),
	MUSCLES("MUSCLES", 268, 15, true),
	MAGNETIC("MAGNETIC", 267, 10, true),
	REGENERATION("REGENERATION", 284, 10, true),
	MADNESS("MADNESS", 339, 8, false),
	BARRIER("MAGIC BARRIER", 344, 15, true),
	POISONED("POISONED", 356, 10, false),
	HONEYED("HONEYED", 359, 6, true),
	;
	
	public final String name;
	public final int sprite;
	public final int turns;
	public final boolean positive;
	
	private Effect(String name, int sprite, int turns, boolean positive) {
		this.name = name;
		this.sprite = sprite;
		this.turns = turns;
		this.positive = positive;
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
