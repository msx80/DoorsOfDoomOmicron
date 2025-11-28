package com.github.msx80.doorsofdoom.model;

import java.util.Objects;

public class Entity {
	public int maxHp = 20;
	public int hp;
	@Override
	public int hashCode() {
		return Objects.hash(hp, maxHp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		return hp == other.hp && maxHp == other.maxHp;
	}
	
	
}
