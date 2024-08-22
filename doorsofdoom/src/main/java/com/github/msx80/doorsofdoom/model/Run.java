package com.github.msx80.doorsofdoom.model;

public class Run {
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
		pg.inventoryAdd(Item.Ectoplasm, 20);
		pg.inventoryAdd(Item.BagOfGold, 2);
  		pg.inventoryAdd(Item.FlamingSword, 1);
      		pg.inventoryAdd(Item.CowardToken, 2);
		pg.inventoryAdd(Item.BagOfGold, 2);
  		pg.inventoryAdd(Item.Knife, 2);
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
  		pg.equip(Item.Bone);
       		// run.monster = new Monster(MonsterDef.SNAKE);
       		pg.addEffect(Effect.GHOSTLY);
       		pg.addEffect(Effect.REGENERATION);
       		*/
	}
	
	public long score() {
		return level * 10
			+ (pg.getInvCount(Item.Diamond) * 100)
			+ (pg.getInvCount(Item.Crown) * 1000)
			+ (pg.getInvCount(Item.CowardToken) * -15)
			+ (exited ? DoorsOfDoom.EXIT_BONUS : 0);
	}
}
