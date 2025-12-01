package com.github.msx80.doorsofdoom.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.github.msx80.doorsofdoom.Richtext;

public class Craft {

	public static final int KEY_COST = 15;
	public static final int GROUP_PURCHASE = 5;
	
	
	private Item output;
	private int count;
	private Map<Item, Integer> ingredients;
	
	
	public static final List<Craft> visible(Pg pg) {
		return ALL.stream().filter(c -> c.visibleBy(pg)).collect(Collectors.toList());
	}
	
	private boolean visibleBy(Pg pg) {
		for (Item i : ingredients.keySet()) {
			int required = ingredients.get(i);
			if (pg.getInvCount(i) < required) return false;
		}
		
		return true;
	}

	public static final List<Craft> ALL = Arrays.asList(
		Craft.of(Item.Key, Item.Gold, KEY_COST),
		Craft.of(GROUP_PURCHASE, Item.Key, Item.Gold, KEY_COST*GROUP_PURCHASE),
		Craft.of(Item.SmallPotion, Item.Gold, 10),
		Craft.of(Item.MediumPotion, Item.Gold, 19),
		Craft.of(GROUP_PURCHASE, Item.MediumPotion, Item.Gold, 19*GROUP_PURCHASE),
		Craft.of(Item.Knife, Item.Gold, 30),
		Craft.of(Item.Diamond, Item.Gold, 100),
		Craft.of(Item.Dart, Item.Venom, 4),
		Craft.of(Item.Elixir, Item.Blood, 10),
		Craft.of(Item.EctoDrink, Item.Ectoplasm, 5, Item.MintLeaf, 1),
		Craft.of(Item.Mint, Item.MintLeaf, 4),
		Craft.of(Item.Cap, Item.Leather, 2),
		Craft.of(Item.Throusers, Item.Leather, 3, Item.Pants, 1),
		Craft.of(Item.Jacket, Item.Leather, 4, Item.Shirt, 1),
		//Craft.of(Item.Diamond, Item.Pants, 3, Item.Shirt, 3),
		
		Craft.of(Item.BagOfGold, Item.Pants, 4),
		Craft.of(Item.BagOfGold, Item.Shirt, 4),
		
		Craft.of(Item.Gelatin, Item.Bone, 4),
		Craft.of(Item.Stockade, Item.Stick, 5),
		Craft.of(Item.Slingshot, Item.Pants, 1, Item.Stick, 2),
		Craft.of(Item.FlamingSword, Item.Phlogiston, 15, Item.Sword, 1),
		Craft.of(Item.GhostSword, Item.Ectoplasm, 15, Item.Sword, 1),
		Craft.of(Item.VenomSword, Item.Venom, 15, Item.Sword, 1),
		Craft.of(Item.GoldenSword, Item.VenomSword, 1, Item.GhostSword, 1, Item.FlamingSword, 1, Item.Scroll, 1),
		
		Craft.of(Item.Hamburger, Item.Bread, 1, Item.Tomato, 1, Item.Cheese, 1),
		Craft.of(Item.Bomb, Item.Phlogiston, 2, Item.Ectoplasm, 3),
		Craft.of(Item.BarrierPotion, Item.Ectoplasm, 1, Item.Venom, 1, Item.Leather, 1),
		Craft.of(Item.Duranium, Item.Fur, 1, Item.Slime, 1, Item.Phlogiston, 1),
		
		Craft.of(Item.DuraniumHelm, Item.Duranium, 1, Item.Helm, 1),
		Craft.of(Item.DuraniumArmour, Item.Duranium, 2, Item.Armour, 1),
		Craft.of(Item.DuraniumShield, Item.Duranium, 2, Item.Shield, 1),
		Craft.of(Item.DuraniumChausses, Item.Duranium, 2, Item.Greaves, 1)
	);

	public Craft(int count, Item output, Map<Item, Integer> ingredients) {
		this.output = output;
		this.count = count;
		this.ingredients = ingredients;
	}
	
	public Item getOutput() {
		return output;
	}
	
	public int getCount() {
		return count;
	}

	public Map<Item, Integer> getIngredients() {
		return ingredients;
	}
	public static final Craft of(Item output, Object... ingredients) {
		return Craft.of(1, output, ingredients);
	}
	
	public static final Craft of(int count, Item output, Object... ingredients) {
		Map<Item, Integer> ing = new HashMap<Item, Integer>();
		
		for (int i = 0; i < ingredients.length; i += 2) {
			Item in = (Item) ingredients[i];
			Integer qty = (Integer) ingredients[i + 1];
			ing.put(in, qty);
		}
		
		return new Craft(count, output, ing);
	}

	public Richtext toRichtext() {
		ArrayList<Object> o = new ArrayList<Object>();
		o.add(-1);
		o.add(output.sprite);
		o.add(-2);
		o.add(1);
		o.add(15);
		o.add(" = ");
		o.add(-2);
		o.add(1);
		
		for (Entry<Item, Integer> e : ingredients.entrySet()) {
			o.add(-1);
			o.add(e.getKey().sprite);
			if(ingredients.size()>3)
			{
				o.add(-2);
				o.add(2);
			}
			o.add(15);
			o.add(" " + e.getValue() + " ");
			if(count>1)
			{
				o.add(7);
				o.add("  x"+count+"");
			}
		}
		
		return Richtext.with(this, o.toArray());
	}
}
