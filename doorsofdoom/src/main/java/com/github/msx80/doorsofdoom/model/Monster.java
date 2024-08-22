package com.github.msx80.doorsofdoom.model;

public class Monster extends Entity {
	public MonsterDef type;
	public Range attack;
	
	public Monster(MonsterDef type) {
		this.type = type;
		this.maxHp = type.maxHpRange.random();
		this.hp = maxHp;
		this.attack = Range.of(type.defaultattack.min, type.defaultattack.max);
	}
	
	/**
	 * Give a monster based on the definition but stronger (or weaker)
	 * @param type
	 * @param multiplier 1 = normal values, 2 = doubly as strong etc
	 */
	public Monster(MonsterDef type, double multiplier) {
		this.type = type;
		this.maxHp = mult(type.maxHpRange.random(), multiplier );
		this.hp = maxHp;
		this.attack = Range.of(mult(type.defaultattack.min, multiplier ), mult( type.defaultattack.max, multiplier));
	}
	
	private static final int mult(int value, double multiplier)
	{
		return Math.max((int) Math.round( ((double) value) * multiplier ), 1) ;
	}

	@Override
	public String toString() {
		return "Monster " + type + " attack " + attack + ", hp = "+hp;
	}
	
	
}
