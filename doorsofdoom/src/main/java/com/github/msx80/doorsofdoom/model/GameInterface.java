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
	public void doSound(int soundNum, float volume, float pitch);
}
