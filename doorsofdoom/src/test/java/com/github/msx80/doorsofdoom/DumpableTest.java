package com.github.msx80.doorsofdoom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

import com.github.msx80.doorsofdoom.dump.Dumpable;
import com.github.msx80.doorsofdoom.model.Effect;
import com.github.msx80.doorsofdoom.model.Item;
import com.github.msx80.doorsofdoom.model.Monster;
import com.github.msx80.doorsofdoom.model.MonsterDef;
import com.github.msx80.doorsofdoom.model.Run;

public class DumpableTest {

	@Test
	void test() {
		DoorsOfDoom.r = new Random(System.currentTimeMillis());
		
		Run run = new Run();
		run.init();
		run.pg.effects = null; // test null maps
		run.monster = new Monster(MonsterDef.BANSHEE, 1.5);

		String out = Dumpable.dumpToString(run);
		
		Run newRun = Dumpable.loadFromString(out, Run::new);
		
		assertEquals(run, newRun);
	}
	@Test
	void testMonster() {
		DoorsOfDoom.r = new Random(System.currentTimeMillis());
		
		Run run = new Run();
		run.init();
		for (Item i : Item.values()) {
			run.pg.inventoryAdd(i, 0);
		}
		for (Effect i : Effect.values()) {
			run.pg.addEffect(i);
		}
		run.monster = new Monster(MonsterDef.BANSHEE, 1.5);

		String out = Dumpable.dumpToString(run);
		
		Run newRun = Dumpable.loadFromString(out, Run::new);
		
		assertEquals(run, newRun);
	}
}
