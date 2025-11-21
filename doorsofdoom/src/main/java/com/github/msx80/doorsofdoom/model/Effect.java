package com.github.msx80.doorsofdoom.model;

public enum Effect {

	SMOKE("SMOKE", 270, 6, true, "Monsters won't be able to find and hit you."),
	LUCKY("LUCKY", 287, 9, true, "Always does max damage."),
	GHOSTLY("GHOSTLY", 271, 20, true, "Sometimes monsters will miss you."),
	MUSCLES("MUSCLES", 268, 15, true, "+5 to attack range."),
	MAGNETIC("MAGNETIC", 267, 10, true, "Always get max gold from monsters."),
	REGENERATION("REGENERATION", 284, 10, true, "Heal 2 HP each turn."),
	MADNESS("MADNESS", 339, 8, false, "Take one damage for each Madness."),
	BARRIER("MAGIC BARRIER", 344, 15, true, "Halves damage taken."),
	POISONED("POISONED", 356, 10, false, "Deal 2 damage each turn."),
	HONEYED("HONEYED", 359, 6, true, "Attracts friendly bees."),
	;
	
	public final String name;
	public final int sprite;
	public final int turns;
	public final boolean positive;
	public final String desc;
	
	private Effect(String name, int sprite, int turns, boolean positive, String desc) {
		this.name = name;
		this.sprite = sprite;
		this.turns = turns;
		this.positive = positive;
		this.desc = desc;
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
