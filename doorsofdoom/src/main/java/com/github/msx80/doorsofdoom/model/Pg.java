package com.github.msx80.doorsofdoom.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pg extends Entity {
	
	public Map<Item, Integer> inventory = new HashMap<Item, Integer>();
	public Map<Place, Item> equip = new HashMap<Place, Item>();
	public float blockedRemainder = 0;
	
	public Map<Effect, Integer> effects = new HashMap<Effect, Integer>();
	
	// dati calcolati
	
	public Range attack = Range.of(2, 4);
	public int armour = 0;
	
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
		effects.put(e, effects.getOrDefault(e, 0) + e.turns);
		ricalcola();
	}
	
	public void incMaxHp(int i) {
		this.maxHp += i;
		this.hp += i;
		ricalcola();		
	}
}
