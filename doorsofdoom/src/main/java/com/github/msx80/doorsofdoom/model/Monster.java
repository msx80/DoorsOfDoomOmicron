package com.github.msx80.doorsofdoom.model;

import java.util.Objects;

import com.github.msx80.doorsofdoom.dump.DumpReader;
import com.github.msx80.doorsofdoom.dump.DumpWriter;
import com.github.msx80.doorsofdoom.dump.Dumpable;

public class Monster extends Entity implements Dumpable {
	public MonsterDef type;
	public Range attack;
	
	
	@Override
	public void dump(DumpWriter out) {
		out.dump(hp);
		out.dump(maxHp);
		out.dump(type);
		out.dump(attack);
		
	}

	@Override
	public void load(DumpReader in) {
		hp = in.loadInt();
		maxHp = in.loadInt();
		type = in.loadEnum(MonsterDef.class);
		attack = in.loadDumpable(Range::new);
	}
	public Monster() {
	}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(attack, type);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monster other = (Monster) obj;
		return Objects.equals(attack, other.attack) && type == other.type;
	}
	
	
	
}
