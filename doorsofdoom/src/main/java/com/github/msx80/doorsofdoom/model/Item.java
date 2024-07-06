package com.github.msx80.doorsofdoom.model;

import com.github.msx80.doorsofdoom.DoorsOfDoom;
import com.github.msx80.doorsofdoom.Richtext;
import com.github.msx80.doorsofdoom.model.Usable.UsableFunc;

public enum Item {
	Slingshot("Slingshot", 286, new String[]{"Throws rocks.", "If you have rocks."},  0, null, null, null, null),
	
	// weapons
	Claw("Claw", 330, new String[] {"Good to eat AND at", "fighting!", "", "Heal 6 hp."},0,Place.Left,Range.of(3,7),Usable.of("Eat",(i, g) -> foodHealing(i,g,6)),null),
	Bone("Bone", 318, new String[]{"Hit'em monkey", "style"}, 0, Place.Left, Range.of(4,7), null, null ),
	Mace("Mace", 288, new String[] {"Deadly if", "operated correcly."},0,Place.Left,Range.of(2,15),null,null),
	Stick("Stick", 307, new String[] {"Better than bare", "hands."}, 0, Place.Left, Range.of(5,6), null, null),
	Knife("Knife", 329, new String[] {"There's an ancient", "writing on it,", "it spells: IKEA."},0,Place.Left,Range.of(5,7),null,null),
	Fork("Fork", 281, new String[] {"Use the fork, Luke."},0,Place.Left,Range.of(6,9),null,null),
	Sword("Sword", 304, new String[] {"From the guys", "that brought you", "other swords"},0,Place.Left,Range.of(10,15),null,null),
	Fang("Fang", 334, new String[] {"Cursed.","Halve monster","strength."},0,Place.Left,Range.of(8,12),null, Usable.of("Throw",dart())),
	FlamingSword("Flaming Sword", 320, new String[] {"The badassery", "made sword."},0,Place.Left,Range.of(15,20),null,null),
	GhostSword("Ghost Sword", 336, new String[] {"Spooky and", "deadly."},0,Place.Left,Range.of(13,22),null,null),
	VenomSword("Venom Sword", 337, new String[] {"Be careful when", "you sheathe this."},0,Place.Left,Range.of(14,23),null,null),
	
	
	// legs
	Pants("Fancy Panties", 306, new String[] {"The last in fashon."}, 5, Place.Legs, null, null, null),
	Throusers("Throusers", 290, new String[] {"Sweet leather on", "your legs."}, 12, Place.Legs, null, null, null),
	Greaves("Greaves", 331, new String[] {"Iron shins"}, 16, Place.Legs, null, null, null),
	
	// head
	Helm("Helm", 309, new String[] {"Protect your", "brain cells, your", "most powerful", "weapon."}, 15, Place.Head, null, null, null),
	Cap("Cap", 301, new String[] {"Keep your scalp", "warm and safe."}, 8, Place.Head, null, null, null),
	Tricorn("Tricorn", 333, new String[] {"Classic pirate", "headware"}, 13, Place.Head, null, null, null),
	Key("Key", 319, new String[]{"They open doors.", "Be careful not to", "run out of them."}, 0, null, null, null, null ),
	Cheese("Cheese", 317, new String[]{"Stinky and delicious.","","Heal 4 hp."},0,null, null, Usable.of("Eat", (i, g) -> foodHealing(i,g,4)), null),
	Blood("Blood", 265,  new String[]{"It's always good","to bring some","around."},0,null,null,null,null),
	Leather("Leather", 297, new String[] {"Ready to be", "stitched"},0,null,null,null,null), 
	Venom("Venom", 266, new String[] {"Handle with care."},0,null,null,null,null),
	Gold("Gold", 264, new String[] {"The precious metal","everybody wants."},0,null,null,null,null),
	Phlogiston("Phlogiston", 302, new String[] {"The heart and","soul of fire."},0,null,null,null,null),
	Ectoplasm("Ectoplasm", 298, new String[] {"Gooey and powerful."},0,null,null,null,null),
	Diamond("Diamond", 303, new String[] {"So so precious.", "", "100 Points each."},0,null,null,null,null),
	Crown("Crown", 321, new String[] {"The Crown was only", "heard of in the", "wildest legends.", "", "1000 Points each."},0,null,null,null,null),
	Shield("Shield", 305, new String[] {"Heavy metal!"},30,Place.Right,null,null,null),
	SmallPotion("Potion, Small", 293, new String[] {"Deliciously","refreshing!", "", "Heal 10 hp."},0,null,null,null,Usable.of("Drink",(a,b )-> potionHealing(a,b,10))),
	MediumPotion("Potion, Medium", 310, new String[] {"Double the fun!", "", "Heal 20 hp."},0,null,null,null,Usable.of("Drink",(a,b )-> potionHealing(a,b,20))),
	BigPotion("Potion, Big", 294, new String[] {"So much healing!", "", "Heal 100 hp."},0,null,null,null,Usable.of("Drink",(a,b )-> potionHealing(a,b,100))),
	Tomato("Tomato", 296, new String[] {"Juicy!","","Heal 6 hp."}, 0, null, null, Usable.of("Eat",(i, g) -> foodHealing(i,g,6)), null),
	MintLeaf("Mint Leaf", 313, new String[] {"Always kill with", "a fresh breath"}, 0, null, null, null, null),
	Shirt("Shirt", 295, new String[] {"The very basic in", "combat protection."}, 8, Place.Body, null, null, null),
	Clover("Clover", 324, new String[] {"This one has","four leaves.","", "Always max damage."}, 0, null, null, null, Usable.of("Prime", addEffect(Effect.LUCKY, "You activate the clover and feel lucky!"))),
	Spinach("Spinach can", 299, new String[] {"The strength of","a sailor!","", "+5 Strength."}, 0, null, null, Usable.of("Eat", addEffect(Effect.MUSCLES, "You eat the whole can and you're READY", "TO RUMBLE!!")), null),
	Buckler("Buckler", 308, new String[] {"Actually the", "bottom of a","beer barrel."}, 20, Place.Right, null, null, null),
	Stockade("Stockade", 283, new String[] {"Unorthodox but", "effective."}, 15, Place.Right, null, null, null),
	Rejuvenant("Rejuvenant", 282, new String[] {"Heal 2 hp per","turn while active."}, 0, null, null, Usable.of("Drink", addEffect(Effect.REGENERATION, "You feel your body slowly fixing itself.")), null),
	SmokeBomb("Smoke Bomb", 311, new String[] {"Creates an", "impenetrable fog."}, 0, null, null, null, Usable.of("Throw", addEffect(Effect.SMOKE, "You hide in the smoke..", "Monsters will have an hard time", "finding you.."))),
	Magnet("Magnet", 326, new String[] {"A magnet for","gold!","", "Max gold loot."}, 0, null, null, Usable.of("Wield", addEffect(Effect.MAGNETIC, "You'll find even the most hidden gold", "piece with this!")), null),
	CowardToken("Coward Token", 327, new String[] {"A memento that you","once fled a monster","", "-15 Points each."}, 0, null, null, null, null),
	BagOfGold("Bag of Gold", 328, new String[] {"By the weight,","it should be about","20 to 40 gold", "pieces"}, 0, null, null, Usable.of("Open", bagOfGold()), null),
	Bread("Bread", 280, new String[] {"Just a little stale", "", "Heal 5 hp."}, 0, null, null, Usable.of("Eat",(i, g) -> foodHealing(i,g,5)), null),
	Hamburger("Tasty Hamburger", 314, new String[] {"The healthy snack","of choice for fine","adventurers.","", "Heal 50 and", "gives strength."}, 0, null, null, Usable.of("Eat",(i, g) -> hamburger(i,g,50)), null),
	Gelatin("Yummy Gelatin", 323, new String[] {"It's made of bones,","don't you know?","So tasty!","", "Heal 50 hp."}, 0, null, null, Usable.of("Eat",(i, g) -> foodHealing(i,g,50)), null),
	Elixir("Elixir", 292, new String[] {"The strength of","a lion, bottled.","", "+10 max hp."}, 0, null, null, Usable.of("Drink",elixir()), null),
	Rock("Rock", 315, new String[] {"You can sling this", "If you have a","slingshot."}, 0, null, null, null, Usable.of("Sling",sling())),
	Armour("Plate Armour", 289, new String[] {"Now we are talking.", "Plate is great."}, 20, Place.Body, null, null, null),
	Jacket("Jacket", 291, new String[] {"Fine leather", "jacket, purchased", "from a pirate dude."}, 15, Place.Body, null, null, null),
	Scroll("Scroll", 325, new String[] {"Suck all life from","an enemy and gives","it to you.", "(except demons)"}, 0, null, null, null, Usable.of("Recite",scroll())),
	Dart("Poison Dart", 285, new String[] {"Halve monster","strength."}, 0, null, null, null, Usable.of("Throw",dart())),
	EctoDrink("EctoDrink", 300, new String[] {"Makes you ghostly."}, 0, null, null, Usable.of("Drink", addEffect(Effect.GHOSTLY, "You feel a bit inconsistent after having","the drink.")), null),
	Mint("Mint", 322, new String[] {"Killer fresh", "breath!", "", "Halven monster HP"}, 0, null, null, null, Usable.of("Chew",mint())),
	Bomb("Bomb", 312, new String[] {"Kaboom!", "", "Deals 40 damages."}, 0, null, null, null, Usable.of("Throw",bomb()))
	;
	
	
	public final String name;
	public final int sprite;
	public final String[] flavour;
	public final int armour;
	public final Place equip;
	public final Range attack;
	public final Usable usable;
	public final Usable combat;

	Item(String name, int sprite, String[] flavour, int armour, Place equip, Range attack, Usable usable, Usable combat)
	{
		this.name = name;
		this.sprite = sprite;
		this.flavour = flavour;
		this.armour = armour;
		this.equip = equip;
		this.attack = attack;
		this.usable = usable;
		this.combat = combat;
	}
	private static UsableFunc bagOfGold() {
		return (i, g) -> {
			int num = DoorsOfDoom.r.nextInt(21)+20;
			g.getLog().add(15, "You open ",14, i.name, 15, " and find ", 13, num+"", 15, " gold!");
			g.getRun().pg.inventoryAdd(i, -1);
			g.getRun().pg.inventoryAdd(Item.Gold, num);
		};
	}

	private static UsableFunc mint()
	{
		return (i, g) -> {
			g.damageMonster(g.getRun().monster.hp/2, null);
			g.getLog().add(15, "You chew ",14, i.name, 15, " and then blow freezing air!");
			g.getRun().pg.inventoryAdd(i, -1);
		};
	}			
	private static UsableFunc dart()
	{
		return (i, g) -> {
			g.getLog().add(15, "You throw ",14, i.name, 15, "! Monster weakened!");
			g.animEnemy("Halved!", 11, null);
			g.getRun().monster.attack.min /= 2;
			g.getRun().monster.attack.max /= 2;
			g.getRun().pg.inventoryAdd(i, -1);
		};
	}		
	private static UsableFunc scroll()
	{
		return (i, g) -> {
			if (g.getRun().monster.type == MonsterDef.DEVIL)
			{
				g.getLog().add(15, "Can't use on demons!");
				g.getLog().add(15, "Do you even read descriptions?");
			}
			else
			{
				 int dmg = g.getRun().monster.hp;
				 g.getRun().damage(g.getRun().pg, -dmg);
				 g.animPG("+"+dmg, 6, (a) -> {
					 g.damageMonster(dmg, null);
				 });
				 g.getLog().add(15, "You leech ",5, dmg, 15, " hp from ",6,g.getRun().monster.type.name,15," !");
				 g.getRun().pg.inventoryAdd(i, -1);
			}
		  
		};
	}	
	private static UsableFunc sling()
	{
		return (i, g) -> {
			if (g.getRun().pg.getInvCount(Item.Slingshot) > 0)
			{
				g.getRun().pg.inventoryAdd(i, -1);
			    g.damageMonster(15, null);
			    g.getLog().add(15, "You sling ",14, i.name, 15, " and deal ",6,"15",15," damage !");
			}
			else
			{
				g.getLog().add(15, "You have no ",14, Item.Slingshot.name, 15, " to sling...");
			}
		  
		};
	}	
	
	private static UsableFunc bomb()
	{
		return (i, g) -> {
				g.getRun().pg.inventoryAdd(i, -1);
			    g.damageMonster(40, null);
			    g.getLog().add(15, "You throw ",14, i.name, 15, " and deal ",6,"40",15," damage !");
		  
		};
	}
	
	private static UsableFunc elixir()
	{
		return (i, g) -> {
			  g.getLog().add(15, "You drink ",14, i.name, 15, "! You feel stronger!");
			  g.animPG("+10 Max!", 6, null);
			  g.getRun().pg.incMaxHp(10);
			  g.getRun().pg.inventoryAdd(i, -1);
		};
	}
	
	private static UsableFunc addEffect(Effect e, String... logs)
	{
		return (i, g) -> {
			g.getRun().pg.addEffect(e);
			g.getRun().pg.inventoryAdd(i, -1);
			for(String r : logs)
			{
				g.getLog().add(14, r);
			}
			g.getLog().add(15,"Effect ",9,e.name,15," started!");
		};
	}

	private static void foodHealing(Item item, GameInterface g, int hp)
	{
		g.getLog().add(15, "You eat ",14, item.name, 15, "! ", 6, "+"+hp);
		g.getLog().add(15, "  You feel much better now.");
		g.getRun().pg.inventoryAdd(item, -1);
		g.getRun().damage(g.getRun().pg, -hp);
		g.animPG("+"+hp, 6, null);
	}
	
	private static void hamburger(Item item, GameInterface g, int hp)
	{
		foodHealing(item, g, hp);
		g.getRun().pg.addEffect(Effect.MUSCLES);
		g.getLog().add(15, "  You feel stronger!");
		
	}
	private static void potionHealing(Item item, GameInterface g, int hp)
	{
		g.getLog().add(15, "You drink ",14, item.name, 15, "! ", 6, "+"+hp);
		g.getLog().add(15, "  You feel much better now.");
		g.getRun().pg.inventoryAdd(item, -1);
		g.getRun().damage(g.getRun().pg, -hp);
		g.animPG("+"+hp, 6, null);
	}
}
