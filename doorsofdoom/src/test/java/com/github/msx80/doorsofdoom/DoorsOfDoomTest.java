package com.github.msx80.doorsofdoom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.github.msx80.doorsofdoom.model.Item;
import com.github.msx80.doorsofdoom.model.Loot;
import com.github.msx80.doorsofdoom.model.Monster;
import com.github.msx80.doorsofdoom.model.MonsterDef;
import com.github.msx80.doorsofdoom.model.Range;

class DoorsOfDoomTest {

	@Test
	void testCalcLoot() {
		
		Loot[] loots = new Loot[] {
			
				Loot.of(1, Item.Armour, Range.of(1, 1)),
				Loot.of(5, Item.Shield, Range.of(1, 1)),
				Loot.of(1, Item.Sword, Range.of(1, 1)),
				Loot.of(3, Item.Blood, Range.of(1, 1)),
		};
		
		DoorsOfDoom.r = new Random();
		Map<Item, Integer> res = new HashMap<>();
		for (int i = 0; i < 100000; i++) {
			
			Loot loot = DoorsOfDoom.calcLoot(loots);
			res.put(loot.item, res.getOrDefault(loot.item, 0) + 1);
		}
		
		testRes(loots, res);
	}

	
	private void testRes(Loot[] loots, Map<Item, Integer> res) {
		for (Loot loot : loots) {
			assertEquals(loot.probability, Math.round(res.get(loot.item).intValue() / 10000f ));
		}
	}

	@Test
	void testLevels() {
		for (int i = 0; i < 300; i++) {
			
			final int level = i;
			List<MonsterDef> eligibles = Stream.of(MonsterDef.values()).filter(m -> m.levels.contains(level)).sorted().collect(Collectors.toList());
			System.out.println("LEVEL: " + i + " : " + eligibles.size() + " -> " + eligibles);
		}
	}
	@Test
	void testLootByLevel() {
		for (int i = 0; i < 300; i++) {
			
			final int level = i;
			Set<Item> loots = Stream.of(MonsterDef.values()).filter(m -> m.levels.contains(level)).flatMap(m -> Stream.of(m.loots)).map(l -> l.item).collect(Collectors.toSet());
						
			System.out.println("LEVEL: " + i + " : " + loots.size() + " -> " + loots);
		}
	}
	@Test
	void testGrowt() {
		DoorsOfDoom.r = new Random();
		for (int i = 0; i < 300; i++) {
			Monster m = DoorsOfDoom.pumpMonster(i, MonsterDef.DARKKNIGHT);
			
			System.out.println("LEVEL: " + i + " : " + m);
		}
	}
	
	@Test
	void testItems() {
		DoorsOfDoom.r = new Random();
		for (Item it : Item.values()) {
			
			List<MonsterDef> eligibles = Stream.of(MonsterDef.values()).filter(m -> m.hasLoot(it)).sorted().collect(Collectors.toList());
			System.out.println("Item: " + it + " : " + eligibles.size() + " -> " + eligibles);
		}
	}
}
