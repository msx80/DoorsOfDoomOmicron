package com.github.msx80.doorsofdoom.model;

import java.util.function.Consumer;

import com.github.msx80.doorsofdoom.Log;
import com.github.msx80.doorsofdoom.PrintUtils;
import com.github.msx80.doorsofdoom.anim.Animation;
import com.github.msx80.doorsofdoom.anim.AnimationManager;

public interface GameInterface {
	Log getLog();
	PrintUtils getPrintUtils();
	Run getRun();
	AnimationManager getAnims();
	
	void animEnemy(String animText, int animColor, Consumer<Animation> onEnd);
	void animPG(String animText, int animColor, Consumer<Animation> onEnd);
	void damageMonster(int dmg, Runnable funMonsterStillAlive);
	void refreshCommands();
	void doSound(int soundNum, float volume, float pitch);
	void confirm(String question, Runnable onYes, Runnable onNo);
	void exitDungeon();
	
	
	default void executeCraft(Craft c) {
		this.doSound(13, 1f, 1f);
		
		Pg pg = this.getRun().pg;
		Item unequippedItem = null;
		
		for (Item i : c.getIngredients().keySet()) {
			boolean equipped = pg.isEquipped(i);
			int required = c.getIngredients().get(i);
			int remaining = pg.inventoryAdd(i, -required);
			
			if(equipped && remaining == 0)
			{
				// item was unequipped to do the craft.
				unequippedItem = i;
			}
			
		}
		
		Item craftedItem = c.getOutput();
		pg.inventoryAdd(craftedItem, c.getCount());
		
		String cnt = c.getCount() == 1 ? "":" (x"+c.getCount()+")";
		this.getLog().add(15, "You obtain ", 14, craftedItem.name, 7, cnt, 15, "!");

		if(unequippedItem != null)
		{
			if(craftedItem.equip == unequippedItem.equip)
			{
				// item was "upgraded", ie removed from the equipment to create another item which is also equippable on the same slot.
				// in this case we automatically equip the new item to avoid leaving the slot empty.
				
				// NB technically this algorithm work only if ONE item is unequipped, if more then one was, it might not catch the upgrade.
				// but there are no such crafts at the moment.
				// (otherwise we should keep a list of all unequipped items and check each one)
				pg.equip(craftedItem);
				this.getLog().add(15, "You equip it in place of ", 14, unequippedItem.name, 15, ".");
			}
		}
		

		
		this.refreshCommands();
	}

	
}
