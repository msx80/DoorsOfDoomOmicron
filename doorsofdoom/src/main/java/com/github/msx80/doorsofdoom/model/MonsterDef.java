package com.github.msx80.doorsofdoom.model;

public enum MonsterDef {
	MOUSE(
		"MOUSE", -1,
		range(5, 8),
		range(1, 3),
		range(0, 15),
		range(1, 2),
		Loot.of(5, Item.Cheese, range(1, 2)),
		Loot.of(2, Item.Blood, range(2, 3))
	),
	SNAKE(
		"SNAKE", -2,
		range(6, 10),
		range(2, 3),
		range(0, 17),
		range(2, 3),
		Loot.of(5, Item.Leather, range(1, 2)),
		Loot.of(2, Item.Blood, range(2, 3)),
		Loot.of(2, Item.Venom, range(2, 3))
	),
	KOBOLD(
		"KOBOLD", -4,
		range(8, 14),
		range(1, 5),
		range(0, 20),
		range(4, 5),
		Loot.of(3, Item.Pants, range(1, 1)),
		Loot.of(3, Item.Tomato, range(1, 2)),
		Loot.of(3, Item.MintLeaf, range(2, 4))
	),
	BAT(
		"BAT", -34,
		range(4, 9),
		range(1, 4),
		range(0, 18),
		range(2, 4),
		Loot.of(1, Item.Leather, range(1, 1)),
		Loot.of(1, Item.SmallPotion, range(1, 1)),
		Loot.of(1, Item.Rock, range(2, 3))
	),
	SKELETON(
		"SKELETON", -3,
		range(9, 13),
		range(3, 4),
		range(3, 28),
		range(3, 6),
		Loot.of(3, Item.Bone, range(1, 1)),
		Loot.of(3, Item.Shirt, range(1, 1))
	),
	CRAB(
		"CRAB", -27,
		range(10, 18),
		range(4, 6),
		range(7, 32),
		range(3, 4),
		Loot.of(3, Item.Blood, range(2, 3)),
		Loot.of(3, Item.CrabShell, range(1, 1)),
		Loot.of(4, Item.Claw, range(2, 2))
	),
	GHOST(
		"GHOST", -5,
		range(15, 20),
		range(2, 5),
		range(7, 29),
		range(1, 2),
		Loot.of(1, Item.Ectoplasm, range(2, 6))
	),
	GOBLIN(
		"GOBLIN", -11,
		range(6, 14),
		range(4, 5),
		range(10, 35),
		range(3, 6),
		Loot.of(2, Item.Shirt, range(1, 1)),
		Loot.of(3, Item.Stick, range(1, 1)),
		Loot.of(3, Item.MintLeaf, range(3, 4))
	),
	SLUG(
		"SLUG", -7,
		range(13, 16),
		range(0, 3),
		range(13, 37),
		range(1, 2),
		Loot.of(5, Item.MediumPotion, range(1, 1)),
		Loot.of(3, Item.Slime, range(1, 2)),
		Loot.of(4, Item.Blood, range(3, 6))
	), 
	MYCONID(
		"MYCONID", -10,
		range(15, 18),
		range(2, 5),
		range(15, 40),
		range(3, 6),
		Loot.of(3, Item.Clover, range(1, 2)),
		Loot.of(1, Item.Spinach, range(1, 1)),
		Loot.of(1, Item.Mushroom, range(1, 2))
	),
	MIMIC(
		"MIMIC", -14,
		range(10, 14),
		range(3, 8),
		range(17, 40),
		range(1, 15),
		Loot.of(2, Item.Mace, range(1, 1)),
		Loot.of(2, Item.Spinach, range(1, 3)),
		Loot.of(2, Item.Magnet, range(2, 3))
	),
	SKULL(
		"SKULL", -12,
		range(25, 30),
		range(1, 10),
		range(19, 38),
		range(4, 7),
		Loot.of(3, Item.Helm, range(1, 1)),
		Loot.of(5, Item.Phlogiston, range(4, 6))
	),
	HELLFLY(
		"HELLFLY", -22,
		range(8, 12),
		range(3, 10),
		range(22, 43),
		range(4, 6),
		Loot.of(3, Item.Venom, range(3, 4)),
		Loot.of(3, Item.Blood, range(2, 4))
	),
	EVILCOOK(
		"EVIL COOK", -21,
		range(16, 25),
		range(3, 8),
		range(18, 48),
		range(4, 8),
		Loot.of(6, Item.Bread, range(2, 3)),
		Loot.of(4, Item.Tomato, range(2, 3)),
		Loot.of(2, Item.Cheese, range(2, 4)),
		Loot.of(2, Item.HoneyPot, range(1, 3)),
		Loot.of(5, Item.Fork, range(1, 1))
	),
	FIREIMP(
		"FIREIMP", -6,
		range(8, 12),
		range(6, 12),
		range(20, 60),
		range(7, 8),
		Loot.of(3, Item.Phlogiston, range(3, 4)),
		Loot.of(5, Item.Blood, range(3, 5))
	),
	PIRATE(
		"PIRATE", -29,
		range(20, 28),
		range(5, 7),
		range(25, 45),
		range(5, 10),
		Loot.of(2, Item.Pants, range(1, 1)),
		Loot.of(2, Item.Tricorn, range(1, 1)),
		Loot.of(2, Item.Hook, range(1, 1))
	),
	OGRE(
		"OGRE", -15,
		range(25, 30),
		range(6, 8),
		range(29, 60),
		range(7, 9),
		Loot.of(5, Item.Buckler, range(1, 1)),
		Loot.of(3, Item.Leather, range(2, 4))
	),
	DWARF(
		"DWARF", -23,
		range(25, 30),
		range(7, 10),
		range(30, 58),
		range(6, 10),
		Loot.of(3, Item.Rejuvenant, range(1, 1)),
		Loot.of(3, Item.SmokeBomb, range(1, 1)),
		Loot.of(1, Item.Magnet, range(1, 1))
	),
	SPIDER(
		"SPIDER", -16,
		range(18, 25),
		range(8, 12),
		range(34, 65),
		range(10, 12),
		Loot.of(3, Item.Venom, range(4, 6)),
		Loot.of(5, Item.Blood, range(3, 5))
	),
	ENT(
		"ENT", -20,
		range(30, 120),
		range(10, 20),
		range(39, 65),
		range(20, 22),
		Loot.of(5, Item.Rejuvenant, range(2, 4)),
		Loot.of(3, Item.BagOfGold, range(2, 3))
	),
	EVILEYE(
		"EVIL EYE", -26,
		range(20, 27),
		range(8, 13),
		range(40, 55),
		range(15, 18),
		Loot.of(3, Item.Stick, range(3, 5)),
		Loot.of(1, Item.Clover, range(2, 3))
	),
	KITTEN(
		"LOST KITTEN", -28,
		range(2, 3),
		range(5, 6),
		range(40, 41),
		range(10, 15),
		Loot.of(3, Item.BagOfGold, range(3, 4))
	),
	GOLEM(
		"GOLEM", -8,
		range(50, 60),
		range(15, 20),
		range(40, 119),
		range(10, 20),
		Loot.of(3, Item.Rock, range(5, 8))
	),
	DARKKNIGHT(
		"DARK KNIGHT", -18,
		range(50, 80),
		range(20, 30),
		range(43, 79),
		range(8, 20),
		Loot.of(1, Item.Shield, range(1, 1)),
		Loot.of(1, Item.Armour, range(1, 1)),
		Loot.of(1, Item.Sword, range(1, 1))
	),
	JESTER(
		"JESTER", -31,
		range(15, 16),
		range(0, 25),
		range(40, 69),
		range(3, 8),
		Loot.of(2, Item.Rock, range(1, 3)),
		Loot.of(2, Item.Clover, range(1, 3)),
		Loot.of(2, Item.MintLeaf, range(2, 3)),
		Loot.of(2, Item.Rejuvenant, range(1, 2)),
		Loot.of(2, Item.Pants, range(1, 1)),
		Loot.of(2, Item.Tomato, range(2, 3))
	),
	KEYMASTER(
		"KEY MASTER", -25,
		range(25, 35),
		range(10, 15),
		range(45, 119),
		range(15, 20),
		Loot.of(1, Item.Key, range(3, 5))
	),
	SUCCUBUS(
		"SUCCUBUS", -17,
		range(40, 60),
		range(20, 25),
		range(49, 89),
		range(10, 12),
		Loot.of(1, Item.Helm, range(1, 1)),
		Loot.of(5, Item.Gold, range(10, 20))
	),
	SPRITES(
		"SPRITES", -33,
		range(40, 60),
		range(5, 40),
		range(55, 85),
		range(30, 50),
		//Loot.of(1, Item.Ectoplasm, range(4, 8)),
		Loot.of(2, Item.Sprite, range(2, 3)),
		Loot.of(1, Item.Rejuvenant, range(2, 4))
	),
	TARDIGRADE(
		"TARDIGRADE", -13,
		range(80, 100),
		range(15, 25),
		range(59, 109),
		range(3, 6),
		Loot.of(3, Item.Spinach, range(1, 3)),
		Loot.of(2, Item.Leather, range(6, 8))
	),
	MAGE(
		"MAGE", -9,
		range(45, 55),
		range(30, 60),
		range(55, 1000),
		range(15, 30),
		Loot.of(5, Item.BigPotion, range(2, 2)),
		Loot.of(2, Item.Elixir, range(1, 1)),
		Loot.of(3, Item.Scroll, range(2, 4))
	),
	BLOB(
		"BLOB", -35,
		range(35, 45),
		range(10, 15),
		range(60, 90),
		range(4, 20),
		Loot.of(1, Item.Gelatin, range(2, 3)),
		Loot.of(1, Item.Slime, range(4, 6))
	),
	VAMPIRE(
			"VAMPIRE", -32,
			range(40, 70),
			range(20, 30),
			range(65, 1000),
			range(30, 50),
			Loot.of(1, Item.Sword, range(1, 1)),
			Loot.of(1, Item.Fang, range(2, 2)),
			Loot.of(1, Item.Blood, range(3, 6))
		),
	WITCH(
			"WITCH", -44,
			range(30, 50),
			range(40, 50),
			range(94, 130),
			range(3, 12),
			Loot.of(1, Item.HoneyPot, range(1, 2)),
			Loot.of(1, Item.Mushroom, range(2, 2)),
			Loot.of(1, Item.CleansingPotion, range(3, 5))
		),
	DEMON(
		"DEMON", -19,
		range(50, 100),
		range(30, 50),
		range(75, 1000),
		range(30, 50),
		Loot.of(2, Item.Key, range(2, 3)),
		Loot.of(2, Item.Phlogiston, range(2, 4)),
		Loot.of(3, Item.Greaves, range(1, 1))
	),
	DRAGON(
		"DRAGON", -30,
		range(80, 150),
		range(20, 40),
		range(70, 1000),
		range(35, 60),
		Loot.of(1, Item.Fang, range(2, 3)),
		Loot.of(1, Item.BagOfGold, range(2, 3)),
		Loot.of(1, Item.Blood, range(4, 6))
	),
	TROLL(
			"TROLL", -41,
			range(50, 80),
			range(25, 50),
			range(80, 100),
			range(10, 15),
			Loot.of(2, Item.Armour, range(1, 1)),
			Loot.of(1, Item.Clover, range(2, 3)),
			Loot.of(1, Item.Spinach, range(2, 3)),
			Loot.of(1, Item.Slime, range(3, 5))
		),		
	DEVIL(
		"DEVIL", -24,
		range(100, 200), // hp
		range(40, 150),  // attack
		range(100, 1000),
		range(30, 50),
		Loot.of(4, Item.Crown, range(1, 1)),
		Loot.of(2, Item.Phlogiston, range(6, 10))
	),
	WEREWOLF(
			"WEREWOLF", -38,
			range(60, 80),
			range(60, 130),
			range(110, 1000),
			range(5, 20),
			Loot.of(2, Item.Greaves, range(1, 1)),
			Loot.of(4, Item.Fur, range(1, 3))
		),		
	BANSHEE(
			"BANSHEE", -39,
			range(30, 50),
			range(120, 200),
			range(110, 1000),
			range(10, 20),
			Loot.of(1, Item.Ectoplasm, range(3, 6)),
			Loot.of(1, Item.Rejuvenant, range(2, 4))
		),	
	NAGA(
			"NAGA", -40,
			range(70, 120),
			range(60, 140),
			range(120, 1000),
			range(10, 20),
			Loot.of(1, Item.Venom, range(5, 8)),
			Loot.of(1, Item.BigPotion, range(2, 3))
		),	
	GENIE(
			"GENIE", -42,
			range(80, 100),
			range(60, 80),
			range(130, 1000),
			range(10, 20),
			Loot.of(5, Item.Phlogiston, range(3, 5)),
			Loot.of(5, Item.BigPotion, range(2, 2)),
			Loot.of(5, Item.BagOfGold, range(1, 1)),
			Loot.of(1, Item.MagicLamp, range(1, 1))
		),	

	DUNGEON_BOSS(
			"DUNGEON BOSS", -37,
			range(400, 400),
			range(100, 300),
			range(150, 150),
			range(100, 100),
			true, // unique: appears a single time at specific level.
			Loot.of(1, Item.Map, range(1, 1))
		),
	ANCIENT(
			"ANCIENT", -36,
			range(500, 600),
			range(300, 500),
			range(160, 1000),
			range(20, 50),
			Loot.of(1, Item.Wisdom, range(1, 1))
		),	
	TRIFFID(
			"TRIFFID", -43,
			range(90, 110), // hp
			range(20, 50), // attack
			range(140, 1000), // levels
			range(5, 12), // gold
			Loot.of(10, Item.Slime, range(2, 3)),
			Loot.of(10, Item.MintLeaf, range(2, 5))
		),	

	;
	
	public String name;
	public int sprite;
	public Range maxHpRange;
	public Range defaultattack;
	public Range levels;
	public Range gold;
	public Loot[] loots;
	public boolean unique;
	
	private MonsterDef(String name, int sprite, Range maxHpRange, Range attack, Range levels, Range gold, Loot... loots) {
		this.name = name;
		this.sprite = sprite;
		this.maxHpRange = maxHpRange;
		this.defaultattack = attack;
		this.levels = levels;
		this.gold = gold;
		this.loots = loots;
		this.unique = false;
	}
	
	private MonsterDef(String name, int sprite, Range maxHpRange, Range attack, Range levels, Range gold, boolean unique, Loot... loots) {
		this.name = name;
		this.sprite = sprite;
		this.maxHpRange = maxHpRange;
		this.defaultattack = attack;
		this.levels = levels;
		this.gold = gold;
		this.loots = loots;
		this.unique = unique;
		
		if(unique)
		{
			if(levels.min != levels.max)
			{
				throw new RuntimeException("Unique monsters must have a single appearance level");
			}
		}
	}
	
	private static Range range(int min, int max) {
		return Range.of(min, max);
	}
	
	public boolean hasLoot(Item item) {
		for (Loot loot : loots) {
			if (loot.item == item) return true;
		}
		
		return false;
	}
	
	
	
}

