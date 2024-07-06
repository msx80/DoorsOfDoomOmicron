package com.github.msx80.doorsofdoom.model;

public enum MonsterDef {
	MOUSE( "MOUSE",-1,Range.of(5,8),Range.of(1,3), 
			Range.of(0,15), Range.of(1,2), Loot.of(5, Item.Cheese, Range.of(1,2)), Loot.of(2, Item.Blood, Range.of(2,3))),
	
	SNAKE("SNAKE", -2,Range.of(6,10),Range.of(2,3), 
			Range.of(0,17), Range.of(2,3),
			Loot.of(5, Item.Leather, Range.of(1, 2)),
			Loot.of(2, Item.Blood, Range.of(2,3)),
			Loot.of(2, Item.Venom, Range.of(2, 3))
			 ),
	
	 KOBOLD("KOBOLD",-4,range(8,14),range(1,5),
				range(0,20), range(4,5),
				Loot.of(3, Item.Pants, Range.of(1,1)),
				Loot.of(3, Item.Tomato, Range.of(1, 2)),
				Loot.of(3, Item.MintLeaf, Range.of(2, 4))),
	 
	 
	 BAT("BAT",-34,range(4,9),range(1,4), 
			 range(0,20), range(2,4),
				Loot.of(1, Item.Leather, Range.of(1,1)),
				Loot.of(1, Item.Rock, Range.of(1, 2))
			 ),
	 
	 SKELETON("SKELETON",-3,range(9,13),range(3,4), 
			 range(3,28), range(3,6),
				Loot.of(3, Item.Bone, Range.of(1,1)),
				Loot.of(3, Item.Shirt, Range.of(1, 1))
			 ),
	 
	 CRAB("CRAB",-27,range(10, 18),range(4, 6),
				range(7,32), range(3,4),				
				Loot.of(3, Item.Blood, Range.of(2,3)),				
				Loot.of(3, Item.CrabShell, Range.of(1,1)),
				Loot.of(4, Item.Claw, Range.of(2,2))),

	 GOBLIN("GOBLIN",-11,range(6,14),range(4,5),
				range(8,30), range(3,6),
				Loot.of(3, Item.Shirt, Range.of(1,1)),
				Loot.of(3, Item.Stick, Range.of(1,1)),
				Loot.of(3, Item.MintLeaf, Range.of(2,4))),
	 SLUG("SLUG", -7, range(13,16), range(0,3),
				range(10,30), range(1,2),
				Loot.of(5, Item.MediumPotion, Range.of(1,1)),
				Loot.of(5, Item.Blood, Range.of(3,6))),
	 
	 MYCONID("MYCONID",-10,range(15,18),range(2,5),
				range(13,40), range(3,6),
				Loot.of(3, Item.Clover, Range.of(1,2)),
				Loot.of(1, Item.Spinach, Range.of(1,1))),
			 
	 MIMIC("MIMIC",-14,range(10,14),range(3,8),
				range(17,40), range(1,15),
				Loot.of(2, Item.Mace, Range.of(1,1)),
				Loot.of(2, Item.Spinach, Range.of(1,3)),
				Loot.of(2, Item.Magnet, Range.of(2,3))),
	 
	 
	 SKULL("SKULL",-12,range(25,30),range(1,10),
				range(15,30), range(4,7),
				Loot.of(3, Item.Helm, Range.of(1,1)),
				Loot.of(5, Item.Phlogiston, Range.of(4,6))),
	 
	 OGRE("OGRE",-15,range(25,30),range(6,8),
				range(25,60), range(7,9),
				Loot.of(5, Item.Buckler, Range.of(1,1)),
				Loot.of(3, Item.Leather, Range.of(2,4))),
	 DWARF("DWARF",-23,range(25,30),range(7,10),
				range(30,58), range(6,10),
				Loot.of(3, Item.Rejuvenant, Range.of(1,1)),
				Loot.of(3, Item.SmokeBomb, Range.of(1,1)),
				Loot.of(1, Item.Magnet, Range.of(1,1))),
			
	 
	 TARDIGRADE("TARDIGRADE",-13,range(80,100),range(15,25),
				range(55,1000), range(3,6),
				Loot.of(2, Item.Spinach, Range.of(1,3)),
				Loot.of(2, Item.Leather, Range.of(6,8))),
	 FIREIMP("FIREIMP",-6,range(8,12),range(6,12),
				range(20,60), range(7,8),

				Loot.of(3, Item.Phlogiston, Range.of(3,4)),
				Loot.of(5, Item.Blood, Range.of(3,5))),
	 
	 EVILCOOK("EVIL COOK",-21,range(25,35),range(5,8),
				range(20,50), range(5,8),
				Loot.of(6, Item.Bread, Range.of(2,3)),
				Loot.of(4, Item.Tomato, Range.of(2,3)),
				Loot.of(2, Item.Cheese, Range.of(2,3)),
				Loot.of(5, Item.Fork, Range.of(1,1))),
	 
	 SPIDER("SPIDER",-16,range(18,25),range(8,12),
				range(30,55), range(10,12),
				Loot.of(3, Item.Venom, Range.of(4,6)),
				Loot.of(5, Item.Blood, Range.of(3,5))),
	 
	 HELLFLY("HELLFLY",-22,range(8,12),range(3,10),
				range(20,40), range(4,6),
				Loot.of(3, Item.Venom, Range.of(3,4)),
				Loot.of(3, Item.Blood, Range.of(2,4))),
			 
	 GOLEM("GOLEM",-8,range(50,60),range(15,20),
				range(40,1000), range(10,20),
				Loot.of(3, Item.Rock, Range.of(5, 8))),
	 GHOST("GHOST",-5,range(15,20),range(2,5),
				range(6,1000), range(1,2),
				Loot.of(1, Item.Ectoplasm, Range.of(2, 6))),
	 

	 SUCCUBUS("SUCCUBUS",-17,range(40,60),range(20, 25),
		range(45,1000), range(10,12),
		Loot.of(1, Item.Helm, Range.of(1,1)),
		Loot.of(5, Item.Gold, Range.of(10,20))),
	 
	 DARKKNIGHT("DARK KNIGHT",-18,range(50,80),range(20,30),
				range(35,1000), range(8,20),

				Loot.of(1, Item.Shield, Range.of(1,1)),
				Loot.of(1, Item.Armour, Range.of(1,1)),
				Loot.of(1, Item.Sword, Range.of(1,1))),
	 
	 DEMON("DEMON",-19,range(50, 100),range(30, 50),
		range(60,1000), range(30,50),
		Loot.of(3, Item.Key, Range.of(2,3)),
		Loot.of(1, Item.Diamond, Range.of(1,1))),
		
	 DEVIL("DEVIL",-24,range(200, 200),range(20, 300),
				range(65,1000), range(30,50),
				Loot.of(1, Item.Crown, Range.of(1,1))),
	 KEYMASTER("KEY MASTER",-25,range(25, 35),range(10, 15),
				range(45,1000), range(15,20),
				Loot.of(1, Item.Key, Range.of(3,5))),
	 
	 EVILEYE("EVIL EYE",-26,range(20, 27),range(8, 13),
				range(35,60), range(15,18),				
				Loot.of(3, Item.Stick, Range.of(3,5)),
				Loot.of(1, Item.Clover, Range.of(2,3))),
	 
	 ENT("ENT",-20,range(30, 150),range(10,20),
				range(35,55), range(20,22),
				Loot.of(5, Item.Rejuvenant, Range.of(2,4)),
				Loot.of(3, Item.BagOfGold, Range.of(2,3))),
		
	 KITTEN("LOST KITTEN",-28,range(2, 3),range(5, 6),
				range(40,41), range(10,15),				
				Loot.of(3, Item.BagOfGold, Range.of(3,4))),


	 MAGE("MAGE",-9,range(45,55),range(30,60),
		range(55,1000), range(15,30),
		Loot.of(5, Item.BigPotion, Range.of(2,2)),
		Loot.of(2, Item.Elixir, Range.of(1,1)),
		Loot.of(3, Item.Scroll, Range.of(2,4))),	 	 
	 ;
	
	
	public String name;
	public int sprite;
	public Range maxHpRange;
	public Range defaultattack;
	public Range levels;
	public Range gold;
	public Loot[] loots;
	
	private MonsterDef(String name, int sprite, Range maxHpRange, Range attack, Range levels, Range gold, Loot... loots) {
		this.name = name;
		this.sprite = sprite;
		this.maxHpRange = maxHpRange;
		this.defaultattack = attack;
		this.levels = levels;
		this.gold = gold;
		this.loots = loots;
	}
	
	private static Range range(int min, int max)
	{
		return Range.of(min, max);
	}
	
	public boolean hasLoot(Item item)
	{
		for (Loot loot : loots) {
			if(loot.item == item) return true;
		}
		return false;
	}
	
}
