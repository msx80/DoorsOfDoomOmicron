package com.github.msx80.doorsofdoom.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.github.msx80.doorsofdoom.dump.DumpReader;
import com.github.msx80.doorsofdoom.dump.DumpWriter;
import com.github.msx80.doorsofdoom.dump.Dumpable;

public class Pg extends Entity implements Dumpable {
	
	public Map<Item, Integer> inventory = new HashMap<Item, Integer>();
	public Map<Place, Item> equip = new HashMap<Place, Item>();
	public float blockedRemainder = 0;
	
	public Map<Effect, Integer> effects = new HashMap<Effect, Integer>();
	
	// dati calcolati
	
	public Range attack = Range.of(2, 4);
	public int armour = 0;
	
	
	@Override
	public void dump(DumpWriter out) {
		out.dump(hp);
		out.dump(maxHp);
		out.dump(armour);
		out.dump(blockedRemainder);
		out.dump(attack);
		out.dump(inventory);
		out.dump(equip);
		out.dump(effects);
	}

	@Override
	public void load(DumpReader in) {
		hp = in.loadInt();
		maxHp = in.loadInt();
		armour = in.loadInt();
		blockedRemainder = in.loadFloat();
		attack = in.loadDumpable(Range::new);
		inventory = in.loadMap( Item.class, Integer.class);
		equip = in.loadMap(Place.class, Item.class);
		effects = in.loadMap( Effect.class, Integer.class);
	}
	
	public Pg() {
		hp = 20;
		maxHp = 20;
		ricalcola();	
	}
	
	void ricalcola() {
		// ricalcola i dati del pg
		// in base alle robe che ha addosso
		// etc
		
		// attack
		Item weapon = equip.get(Place.Left);
		Range base;
		if (weapon != null)
			base = weapon.attack;
		else
			base = Range.of(2, 4);
		
		int b = 0;
		if (hasEffect(Effect.MUSCLES)) b = 5;
		this.attack = Range.of(base.min+b, base.max+b); 
		
		// armour
		int arm = 0;
		for (Place p : Place.values()) {
			Item e = this.equip.get(p);
			if (e != null) arm += e.armour;
		}
		if(arm>=100) arm = 99; // do not exceed 99%
		this.armour = arm;
		
		// maxHp
		// pg.maxHp = pg.level * 10
		if (hp > maxHp) hp = maxHp; 
	}
	
	public int getInvCount(Item item) {
		return inventory.getOrDefault(item, 0);
	}
	
	public int inventoryAdd(Item item, int qty) { // anche negativi
		int curr = getInvCount(item);
		int niu = curr + qty;
		
		if (niu > 0) {
			inventory.put(item, niu);
			
		} else {
			inventory.remove(item);
			
			if (equip.containsValue(item)) {
				equip.remove(item.equip);
			}
			
			niu = 0;
		}
		
		ricalcola();
		return niu;
	}
	
	public void equip(Item item) {
		if (item.equip != null) {
			if (getInvCount(item) > 0) {
				equip.put(item.equip, item);
			}
		}
		
		ricalcola();
	}

	public void unequip(Item item) {
		if (item.equip != null) {
			if (equip.get(item.equip) == item) {
				equip.remove(item.equip);
				ricalcola();
			}
		}
	}

	public boolean hasEffect(Effect e) {
		return effects.containsKey(e);
	}

	public void decEffects() {
		
		List<Effect> eff = new ArrayList<>(effects.keySet());
		
		for (Effect e : eff) {
			Integer n = effects.get(e) - 1;
			if (n <= 0) {
				effects.remove(e);
			} else {
				effects.put(e, n);
			}
		}
		
		ricalcola();
	}
	
	public boolean isEquipped(Item item) {
		if (item.equip != null) return item == equip.get(item.equip);
		return false;
	}


	public void addEffect(Effect e) {
		addEffect(e, e.turns);
	}
	

	public void addEffect(Effect e, int turns) {
		effects.put(e, effects.getOrDefault(e, 0) + turns);
		ricalcola();
	}
	
	public void incMaxHp(int i) {
		this.maxHp += i;
		this.hp += i;
		ricalcola();		
	}

	public void removeBadEffects() {
		for (Effect e : Effect.values()) {
			if(!e.positive)
			{
				effects.remove(e);
			}
		}
		ricalcola();
	}

	@Override
	public int hashCode() {
		return Objects.hash(armour, attack, blockedRemainder, effects, equip, inventory);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pg other = (Pg) obj;
		return armour == other.armour && Objects.equals(attack, other.attack)
				&& Float.floatToIntBits(blockedRemainder) == Float.floatToIntBits(other.blockedRemainder)
				&& Objects.equals(effects, other.effects) && Objects.equals(equip, other.equip)
				&& Objects.equals(inventory, other.inventory);
	}


}
