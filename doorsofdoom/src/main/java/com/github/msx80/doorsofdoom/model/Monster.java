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
	
}
