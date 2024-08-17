package com.github.msx80.doorsofdoom.model;

public class Loot {
	public final int probability;
	public final Item item;
	public final Range qty;
	
	public Loot(int probability, Item item, Range qty) {
		this.probability = probability;
		this.item = item;
		this.qty = qty;
	}
	
	public static Loot of(int probability, Item item, Range qty) {
		return new Loot(probability, item, qty);
	}
}
