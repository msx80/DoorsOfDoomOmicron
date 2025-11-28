package com.github.msx80.doorsofdoom.model;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.github.msx80.doorsofdoom.DoorsOfDoom;
import com.github.msx80.doorsofdoom.dump.DumpReader;
import com.github.msx80.doorsofdoom.dump.DumpWriter;
import com.github.msx80.doorsofdoom.dump.Dumpable;

public class Run implements Dumpable{
	public Pg pg;
	public int level;
	public int kills;
	public Monster monster;
	public int gold;
	public int lootQty;
	public Item lootItem;
	public int shake = 0;
	public int shakePg = 0;
	public boolean exited = false;
	
	
	@Override
	public void dump(DumpWriter out) {
		out.dump(pg);
		out.dump(level);
		out.dump(kills);
		out.dump(monster);
		out.dump(gold);
		out.dump(lootQty);
		out.dump(lootItem);
		out.dump(shake);
		out.dump(shakePg);
		out.dump(exited);
		
	}

	@Override
	public void load(DumpReader in) {
		pg = in.loadDumpable(Pg::new);
		level = in.loadInt();
		kills = in.loadInt();
		monster = in.loadDumpable(Monster::new);
		gold = in.loadInt();
		lootQty = in.loadInt();
		lootItem = in.loadEnum(Item.class);
		shake = in.loadInt();
		shakePg = in.loadInt();
		exited = in.loadBoolean();
	}

	
	public Run() {
		pg = new Pg();	
	
		init();
	}
	
	public void damage(Entity e, int dmg) {
		e.hp -= dmg;
		if (e.hp < 0) e.hp = 0;
		if (e.hp > e.maxHp) e.hp = e.maxHp;
	}

	public void init() {
		
		exited = false;
		level = 0;
		kills = 0;
		monster = null;
		gold = 0;
		lootQty = 0;
		lootItem = null;
		
		pg.maxHp = 20;
		pg.hp = 20;
		pg.effects.clear();
		pg.equip.clear();
		pg.blockedRemainder = 0;
		pg.inventory.clear();
		pg.ricalcola();
		
		pg.inventoryAdd(Item.SmallPotion, 3);
		pg.inventoryAdd(Item.Key, 50);
		
		
		
		/*
		pg.inventoryAdd(Item.Map, 1);
		pg.inventoryAdd(Item.Rock, 1000);
		pg.inventoryAdd(Item.Slingshot, 1);
		
		
		pg.inventoryAdd(Item.MediumPotion, 3);
		pg.inventoryAdd(Item.BigPotion, 3);
  		pg.inventoryAdd(Item.Sword, 3);
    		pg.inventoryAdd(Item.Shield, 1);
      		pg.inventoryAdd(Item.Greaves, 1);
		pg.inventoryAdd(Item.Armour, 1);
  		pg.inventoryAdd(Item.Helm, 1);
      		pg.inventoryAdd(Item.EyePatch, 1);
		pg.inventoryAdd(Item.Tricorn, 1);
  		pg.inventoryAdd(Item.Fang, 2);
    		pg.inventoryAdd(Item.Sprite, 12);
      		pg.inventoryAdd(Item.Venom, 22);
  		pg.inventoryAdd(Item.Wisdom, 1);
    		pg.inventoryAdd(Item.Ectoplasm, 22);
      		pg.inventoryAdd(Item.Slime, 22);
		pg.inventoryAdd(Item.Hamburger, 2);
		pg.inventoryAdd(Item.Phlogiston, 20);
		pg.inventoryAdd(Item.Fur, 20);
		pg.inventoryAdd(Item.Slime, 20);
		pg.inventoryAdd(Item.Ectoplasm, 20);
		pg.inventoryAdd(Item.BagOfGold, 2);
  		pg.inventoryAdd(Item.FlamingSword, 1);
      		pg.inventoryAdd(Item.CowardToken, 2);
		pg.inventoryAdd(Item.BagOfGold, 2);
  		pg.inventoryAdd(Item.Knife, 2);
  		
  		pg.inventoryAdd(Item.DuraniumArmour, 2);
  		pg.inventoryAdd(Item.DuraniumShield, 2);
  		pg.inventoryAdd(Item.DuraniumChausses, 2);
  		pg.inventoryAdd(Item.DuraniumHelm, 2);
  		
    		pg.inventoryAdd(Item.Blood, 4);
      		pg.inventoryAdd(Item.Cheese, 15);
		pg.inventoryAdd(Item.Jacket, 15);
		pg.inventoryAdd(Item.Throusers, 15);
		pg.inventoryAdd(Item.Leather, 15);
		pg.inventoryAdd(Item.Bread, 15);
		pg.inventoryAdd(Item.Stick, 15);
		pg.inventoryAdd(Item.Rock, 15);
		pg.inventoryAdd(Item.Armour, 15);
		pg.inventoryAdd(Item.Venom, 15);
		pg.inventoryAdd(Item.EctoDrink, 15);
		pg.inventoryAdd(Item.Slingshot, 15);
		pg.inventoryAdd(Item.Magnet, 15);
		pg.inventoryAdd(Item.Pants, 15);
		pg.inventoryAdd(Item.Gelatin, 15);
		pg.inventoryAdd(Item.Stockade, 15);
		pg.inventoryAdd(Item.Mint, 15);
		pg.inventoryAdd(Item.Ectoplasm, 15);
		pg.inventoryAdd(Item.Tomato, 15);
		pg.inventoryAdd(Item.Fork, 15);
		pg.inventoryAdd(Item.Phlogiston, 15);
		pg.inventoryAdd(Item.Diamond, 15);
		pg.inventoryAdd(Item.Crown, 15);
		pg.inventoryAdd(Item.Gold, 1005);
		pg.inventoryAdd(Item.Scroll, 15);
		pg.inventoryAdd(Item.SmallPotion, 1);
		pg.inventoryAdd(Item.Spinach, 1);
		pg.inventoryAdd(Item.Elixir, 10);
		pg.inventoryAdd(Item.Clover, 1);
		pg.inventoryAdd(Item.Tomato, 1);
		pg.inventoryAdd(Item.Shield, 15);
		pg.inventoryAdd(Item.Shirt, 15);
		pg.inventoryAdd(Item.Helm, 15);
		pg.inventoryAdd(Item.Bone, 15);
		pg.inventoryAdd(Item.FlamingSword, 1);
		pg.inventoryAdd(Item.Sword, 1);
		pg.inventoryAdd(Item.Claw, 1);
		*/
		
		/*
  		pg.equip(Item.FlamingSword);
  		pg.equip(Item.Armour);
  		pg.equip(Item.Helm);
  		pg.equip(Item.Greaves);
  		pg.equip(Item.Shield);
  		*/
       		// run.monster = new Monster(MonsterDef.SNAKE);
       		//pg.addEffect(Effect.GHOSTLY);
       		//pg.addEffect(Effect.BARRIER);
       		
	}
	
	public long score() {
		return level * 10
			+ (pg.getInvCount(Item.Diamond) * 100)
			+ (pg.getInvCount(Item.Crown) * DoorsOfDoom.CROWN_POINT)
			+ (pg.getInvCount(Item.CowardToken) * -15)
			+ (exited ? DoorsOfDoom.EXIT_BONUS : 0);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(exited, gold, kills, level, lootItem, lootQty, monster, pg, shake, shakePg);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Run other = (Run) obj;
		return exited == other.exited && gold == other.gold && kills == other.kills && level == other.level
				&& lootItem == other.lootItem && lootQty == other.lootQty && Objects.equals(monster, other.monster)
				&& Objects.equals(pg, other.pg) && shake == other.shake && shakePg == other.shakePg;
	}
	
	
}
