package com.github.msx80.doorsofdoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.msx80.doorsofdoom.anim.Animation;
import com.github.msx80.doorsofdoom.anim.AnimationManager;
import com.github.msx80.doorsofdoom.anim.Easing;
import com.github.msx80.doorsofdoom.dump.Dumpable;
import com.github.msx80.doorsofdoom.model.Craft;
import com.github.msx80.doorsofdoom.model.Effect;
import com.github.msx80.doorsofdoom.model.GameInterface;
import com.github.msx80.doorsofdoom.model.Item;
import com.github.msx80.doorsofdoom.model.Loot;
import com.github.msx80.doorsofdoom.model.Monster;
import com.github.msx80.doorsofdoom.model.MonsterDef;
import com.github.msx80.doorsofdoom.model.Place;
import com.github.msx80.doorsofdoom.model.Run;
import com.github.msx80.omicron.api.Game;
import com.github.msx80.omicron.api.Pointer;
import com.github.msx80.omicron.api.Sys;
import com.github.msx80.omicron.api.SysConfig;
import com.github.msx80.omicron.api.SysConfig.VirtualScreenMode;
import com.github.msx80.omicron.basicutils.Colors;
import com.github.msx80.omicron.basicutils.ShapeDrawer;
import com.github.msx80.omicron.basicutils.palette.Tic80;
import com.github.msx80.omicron.basicutils.text.TextDrawer;
import com.github.msx80.omicron.basicutils.text.TextDrawer.Align;
import com.github.msx80.omicron.basicutils.text.TextDrawerVariable;

public class DoorsOfDoom implements Game, GameInterface {
	// SOUND EFFECTS: https://juhanijunkala.com/ SubspaceAudio https://opengameart.org/content/512-sound-effects-8-bit-style
	// MUSIC: https://twitter.com/SBieliayev  https://www.youtube.com/channel/UCvjkkwGL7g092E1oV7IMffw
	
	private static final float MUSIC_VOLUME = 0.2f;
	private static final int LEVEL_MONSTER_GROWTH = 160; // minimal level at which monsters start to grow
	// private static final int MADNESS_DAMAGE = 5;
	public static final int WISDOM_DAMAGE = 50;
	public static final int EXIT_BONUS = 5000;
	public static final int CROWN_POINT = 200;
	public static final int SPRITE_DAMAGE = 25;
	public static final int BEE_DAMAGE = 10;
	
	public final int BUTTONS_X = 167; // 8 * 12 + 3;
	public final int STATS_X = 97; // 8 * 12 + 3;
	
	public final int EFFECTS_X = 142;
	public final int EFFECTS_Y = 50;
	public final int EFFECTS_SPACING = 9;
	
	
	public static Random r;
	
	private TextDrawer font = null;
	private TextDrawer smallFont = null;
	private TextDrawer bigFont = null;
	
	//int x = 100;
	//int y = 10;
	//int dir = 0;
	Pointer m;
	//boolean oldClick = false;
	int t = 0;
	
	//InventoryWidget invWidget = null;
	//CraftWidget craftWidget = null;
	Widget<?> currWidget;
	
	AnimationManager anims = new AnimationManager();
	
	Run run = new Run();
	
	Step step;
	Log log;
	
	PrintUtils p;
	
	ButtonWidget buttons;
	
	Boolean sound = null;
	Boolean music = null;
	Boolean cursor = null;
	
	Step INTRO;
	Step OPENING;
	Step OUTDOOR;
	Step INDOOR;
	Step SETTINGS;
	Step LOOT;
	Step DEATH;
	Step WIN;
	private int zoomsurf;
	private String buildId;
	
	private void actionNewGame() {
		Runnable newGame = () -> {
			doSound(15, 1f, 1f);
			log.add("");
			log.add(5, "--=======================================================--");
			log.add(15, "               You start a new game.");
			log.add(5, "--=======================================================--");
			log.add("");
			deleteSavestate();
			run.init();
			enterStep(OUTDOOR);
		};
		
		if(hasSavedState())
		{
			doSound(14, 1f, 1f);
			confirm("Erase current game?", newGame, () -> { doSound(6, 1f, 1f); log.add(15, "New game canceled."); });
		}
		else
		{
			newGame.run();
		}
		
	}
	
	private void unimplemented() {
		log.add(5, "Sorry, this is unimplemented.. :(");
	}
	
	private void doAttack() {
		attacksound();
		
		int dmg;
		if (run.pg.hasEffect(Effect.LUCKY)) {
			dmg = run.pg.attack.max;
		} else {
			dmg = run.pg.attack.random();
		}
		
		log.add(15,"You deal ", 6, dmg, 15, " damages to ", 5, run.monster.type.name, 15, "!");
		
		damageMonster(dmg, () -> {
			if (doEnemyTurn()) {
				// prevent calling of effect if player is dead
				endTurn();
			}
		});
		
		/*
  		damageMonster(dmg, function ()
    		end)
      		*/
	}
	
	private void flee() {
		if(run.monster.type.unique)
		{
			doSound(6, 1f, 1f);
			log.add(6, "You cannot flee from this monster!!");
		}
		else
		{
			doSound(14, 1f, 1f);
			confirm("Sure?", () -> {
				doSound(16, 1f, 1f);
				log.add(15, "You flee from ", 5, run.monster.type.name, 15, "!");
				log.add(15, "You earned yourself a ", 14, Item.CowardToken.name, 15, "!");
				run.pg.inventoryAdd(Item.CowardToken, 1);
				enterStep(OUTDOOR);
			}, () -> {
				doSound(6, 1f, 1f);
				log.add(15, "You keep on fighting.");
			});
		}
	}
	
	private void attacksound() {
		doSound(4, 1f, 0.8f + r.nextFloat() * 0.4f);
	}
	
	void afterEnemyAction() {
		if (run.pg.hp <= 0) {
			log.add(5, run.monster.type.name, 15, " defeats you! ", 6, "YOU'RE DEAD!");
			enterStep(DEATH);
		}
		//else
		//enterStep(step.OURTURN);
	}
	
	private boolean doEnemyTurn() {
		String animText;
		int animColor;
		
		if (run.pg.hasEffect(Effect.SMOKE)) {
			doSound(5, 1f, 1f);
			log.add(5, run.monster.type.name, 15, " can't find you becouse of ", 9, Effect.SMOKE.name );
			animText = "MISSED";
			animColor = 11;
			
		} else if (run.pg.hasEffect(Effect.GHOSTLY) && (r.nextInt(100) > 50)) {
			doSound(5, 1f, 1f);
			log.add(5, run.monster.type.name, 15, " misses you becouse of ", 9, Effect.GHOSTLY.name);
			animText = "MISSED";
			animColor = 11;
			
		} else {
			attacksound();
			int dmg = run.monster.attack.random();
			
			boolean barrier = run.pg.hasEffect(Effect.BARRIER);
			if(barrier) { 
				dmg = dmg /2;
				log.add(9, "Magic Barrier", 15, " dispels half damage (", 6, ""+dmg, 15, ")!");
			}
			
			float blockedf = (float)dmg * (float)run.pg.armour / 100f;
			int blocked = (int)blockedf;
			float blockedRemainder = blockedf - blocked;
			run.pg.blockedRemainder+=blockedRemainder;
			
			if (run.pg.blockedRemainder > 1) {
			   run.pg.blockedRemainder -= 1f;
			   blocked ++;
			}
			
			int realdmg = Math.max(0, dmg - blocked);
			run.damage(run.pg, realdmg);
			run.shakePg = 20;
			
			log.add(5, run.monster.type.name, 15, " deals ",
				 6, dmg,
				 12, " (-" + blocked + ")",
				 15, " damages to you!"
				 );
			// sfx(1, 15, 15) // TODO
			animText = "-" + realdmg;
			animColor = 6;
		
		// anims:add(makeAnimRaisingString("-"..realdmg, 205, 50,6,afterEnemyAction));
		}
		
		Consumer<Animation> ca = a -> {
			afterEnemyAction();
		};
		animPG(animText, animColor, ca);
		
		return run.pg.hp > 0;
	}
	
	public void animPG(String animText, int animColor, Consumer<Animation> onEnd) {
		anims.add(Easing.LINEAR, 60, onEnd , 0,50, a -> {
			p.printBig(animText, STATS_X + 20, 50 - a / 2, animColor, Align.CENTER);
		});
	}
	
	void endTurn() {

		if (run.pg.hasEffect(Effect.HONEYED)) {
			log.add(15, "A ", 14, Item.Bee.name, 15, " arrives from who knows where.. ");
			run.pg.inventoryAdd(Item.Bee, 1);
		}
		
		
		if (run.pg.hasEffect(Effect.REGENERATION)) {
			log.add(15, "You regenerate ", 6, "2", 15, " hp!");
			run.damage(run.pg, -2);
		}
		
		if (run.pg.hasEffect(Effect.POISONED)) {
			log.add(15, "You're poisoned! ", 6, "-2", 15, " hp!");
			run.damage(run.pg, 2);
		}
		
		if (run.pg.hasEffect(Effect.MADNESS)) {
			int count = run.pg.effects.get(Effect.MADNESS);
			log.add(14, "Your mind deals you ", 6,"" + count, 15, " hp!");
			run.damage(run.pg, count);
		}
		
		run.pg.decEffects();
		refreshCommands();
	}
	
	void killMonster() {
		Loot l = calcLoot(run.monster.type.loots);
		int q = l.qty.random();
		run.lootItem = l.item;
		run.lootQty = q;
		
		run.gold = run.pg.hasEffect(Effect.MAGNETIC) ? run.monster.type.gold.max : run.monster.type.gold.random();
		run.monster = null;
		run.kills += 1;
	}
	
	public void damageMonster(int dmg, Runnable funMonsterStillAlive) {
		run.damage(run.monster, dmg);
		run.shake = 20;
		 
		Consumer<Animation> ca = a -> {
			if (run.monster.hp <= 0) {
				doSound(2, 1f, 1f);
				
				log.add(15, "You defeated ", 6, run.monster.type.name, 15, "!");
				killMonster();
				endTurn();
				if (run.pg.hp <= 0) {
					log.add(5, "You suffered too much! ", 6, "YOU'RE DEAD!");
					enterStep(DEATH);
				}
				else
				{
					enterStep(LOOT);
				}
			} else {
				if (funMonsterStillAlive != null) funMonsterStillAlive.run();
			}	
		};
		
		String animText = "-" + dmg;
		int animColor = 6;
		animEnemy(animText, animColor, ca);
	}
	
	public void animEnemy(String animText, int animColor, Consumer<Animation> onEnd) {
		anims.add(Easing.LINEAR, 60,onEnd , 0,50, a->{
	        	//Sys.draw(2, a ,100   ,64,0,8,8,0,0);
			p.printBig(animText, 45, 50-a/2, animColor, Align.CENTER);
	        });
	}
	
	public void init() {
		
		Sys.trace("Initalizing Door of Doom");
		/*
		// experiment with calculated spawn windows.
		// normalize monsters levels
		for (int i = 0; i < MonsterDef.values().length; i++) {
			MonsterDef.values()[i].levels = Range.of(i*5-15, i*5+24-15);
		}
		*/
		
		//String platform = (String) Sys.hardware("com.github.msx80.omicron.plugins.builtin.PlatformPlugin", "PLATFORM", null);
		//cursor = platform.contains("DESKTOP");
		DoorsOfDoom.r = new Random(Sys.millis());
		
		try {
			Sys.hardware("com.github.msx80.omicron.plugins.builtin.StatePlugin", "ON_RESUME", (Runnable )this::onResume);
			Sys.hardware("com.github.msx80.omicron.plugins.builtin.StatePlugin", "ON_PAUSE", (Runnable )this::onSuspend);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		buildId = "Build: "+new String(Sys.binfile(1));
		Sys.trace(buildId);
		zoomsurf = Sys.newSurface(128, 128);
		font = new TextDrawerVariable(1, 6, 6, 3);
		smallFont = new TextDrawerVariable(7, 4, 6, 2); //, 4);
		bigFont = new TextDrawerVariable(8, 16, 16, 3); //, 14);
		
		p = new PrintUtils(font, bigFont);
		log = new Log(p);
		
		buttons = new ButtonWidget(p, BUTTONS_X, 10, 73, 86);
		run.init();
		if (musicOn()) Sys.music(1, MUSIC_VOLUME, true);
		Callable<List<Action>> introOptions = () -> {
			List<Action> list = new ArrayList<>();
			list.add(new Action("New Game!", this::actionNewGame));
			list.add(new Action("Settings", () ->  {doSound(14, 1f, 1f); enterStep(SETTINGS);}));
			if(hasSavedState())
			{
				list.add(new Action("Continue!", this::restoreFromSavestate));
			}
			return list;
			
		};
		
		INTRO = new Step(() -> {
			run.init();
			log.add(15, "Welcome to", 6, " Doors of Doom", 15, "! by", 8, " MSX");
			log.add(14, "Fight your way deep into the dungeon!");
			log.add(14, "Can you find your way out?");
			log.add(0, "");
			log.add(15, "High score: ", 5, "" + getHighScore());
			
		}, null, introOptions);
		
		SETTINGS = new Step(() -> {
     		// log.add(15, "Set your settings!");
		}, null, () -> Arrays.asList(
			new Action("Sound: " + (soundOn() ? "ON" : "OFF"), () -> {toggleSound(); doSound(14, 1f, 1f); refreshCommands();}),
			new Action("Music: " + (musicOn() ? "ON" : "OFF"), () -> {toggleMusic(); refreshCommands();}),
			new Action("Cursor: " + (cursorOn() ? "ON" : "OFF"), () -> {toggleCursor(); refreshCommands();}),
			new Action("Credits", () -> { currWidget = new CreditsWidget(this, p, 4, 4, 240 - 8, 91); }),
			new Action("Back", () ->  {enterStep(INTRO);} )
		));
		
		OPENING = new Step(() -> {
			doSound(6, 1f, 1f);
			run.pg.inventoryAdd(Item.Key, -1);
			run.level++;
			run.monster = chooseMonster();
			run.lootItem = null;
			run.lootQty = 0;
			
			anims.add(Easing.QUADRATIC_IN, 40, a -> {enterStep(INDOOR);}, 0, 255, a -> {
				// Sys.draw(2, a, 100, 64, 0, 8, 8, 0, 0);
				Sys.color(Colors.from(255, 255, 255, a));
				drawMonsterSprite(run.monster.type.sprite);
				Sys.color(Colors.WHITE);
			});
		}, () -> {
			log.add(15, "-----------------------");
			log.add(15, "You open the door and find ", 5, run.monster.type.name, 15, "!");
		}, () -> Arrays.asList());
		
		OUTDOOR = new Step(null, null, () -> {
			List<Action> list = new ArrayList<>();
			
			list.add(new Action("Open Door!", this::openDoor));
			
			list.add(new Action("Inventory", () -> {
				currWidget  = new InventoryWidget(this, zoomsurf, p, 2, 2, 127, 92);
				doSound(14, 1f, 1f);
			}));
			
			list.add(new Action("Buy/Craft", () -> {
				if (Craft.visible(run.pg).size() > 0) {
					log.add(15, "You have ", 11, ""+run.pg.getInvCount(Item.Gold), 14, " Gold.");
					currWidget  = new CraftWidget(this, p, 75, 16, 92, 78);
					doSound(14, 1f, 1f);
				} else {
					log.add(15, "You can't craft anything yet!");
					doSound(10, 1f, 1f);
				}
			}));
			
			List<Item> keys = new ArrayList<>( run.pg.inventory.keySet() );
			Collections.sort(keys);
			
			for (Item i : keys) {
				if (i.usable != null) {
					list.add(new Action(Richtext.of(-1, i.sprite, 15, " " + i.usable.command, 14, " [" + run.pg.getInvCount(i) + "]"), () -> this.useItem(i)));
				}
			}
			
			return list;
		});
		
		INDOOR = new Step(null, null, () -> {
			List<Action> list = new ArrayList<>();
			list.add(new Action("Attack", this::doAttack));
			list.add(new Action("Flee", this::flee));
			
			
			List<Item> keys = new ArrayList<>( run.pg.inventory.keySet() );
			Collections.sort(keys);
			
			for (Item i : keys) {
				if (i.combat != null) {
					list.add(new Action(Richtext.of(-1, i.sprite, 15, " " + i.combat.command, 14, " [" + run.pg.getInvCount(i) + "]"), () -> this.useCombatItem(i)));
				}
			}
			
			return list;
		});
		
		LOOT = new Step(null, null, () -> Arrays.asList(
			new Action("Grab Loot!", this::grabLoot)
		));
		
		DEATH = makeDeathStep();
		
		WIN = makeWinStep();
		
		enterStep(INTRO);
	}

	private void onSuspend()
	{
		Sys.trace("Paused received!");
		// unspool animations to get to a finite state
		while(anims.isRunning())
		{
			anims.update();
			Sys.trace("Animations running");
		}
		Sys.trace("Done!");
		
		if(step == OPENING || step == INDOOR || step == OUTDOOR || step == LOOT)
		{
			Sys.trace("Saving config..");
			
			String s = Dumpable.dumpToString(run);
			Sys.mem("savestate", stepToString(step)+s);
		}
		else if (step == INTRO || step == SETTINGS)
		{
			Sys.trace("suspending from intro or settings, should not delete the savestate");
		}
		else
		{
			Sys.trace("Cleaning suspend");
			deleteSavestate();
		}
		
		
	}
	
	private void onResume()
	{
		Sys.trace("Resume received!");
		if(step == OPENING || step == INDOOR || step == OUTDOOR || step == LOOT)
		{
			// no need to resume, the game is already running
			Sys.trace("Game already running, no resume");
			// ensure we remove any save
			deleteSavestate();
		}
		else
		{
			// if the application somehow restarted, we can resume
			restoreFromSavestate();
		}
	}

	private void deleteSavestate()
	{
		Sys.trace("Deleting savestate");
		Sys.mem("savestate", null);
	}
	private void restoreFromSavestate() {
		String saveState = Sys.mem("savestate");
		if(saveState != null && !saveState.isEmpty())
		{
			doSound(15, 1f, 1f);
			step = stringToStep(saveState.substring(0, 1));
			String dump = saveState.substring(1);
			if(dump.startsWith("{"))
			{
				log.add("Sorry, old format.. :( cannot reload game.");
			}
			else
			{
				run = Dumpable.loadFromString(dump, Run::new);
				deleteSavestate();
				refreshCommands();
	
				log.add("");
				log.add(5, "--=======================================================--");
				log.add(15, "         You resume your previous game.");
				log.add(5, "--=======================================================--");
				log.add("");
			}
			
			
		}
	}
	
	private boolean hasSavedState()
	{
		String saveState = Sys.mem("savestate");
		return saveState != null && !saveState.isEmpty();
		
	}
	

	private String stepToString(Step step)
	{
		if(step == OUTDOOR) return "T";
		if(step == INDOOR) return "I";
		if(step == OPENING) return "O";
		if(step == LOOT) return "L";
		throw new RuntimeException("wrong step! ");
	}
	
	private Step stringToStep(String s)
	{
		if(s.equals("T")) return OUTDOOR;
		if(s.equals("I")) return INDOOR;
		if(s.equals("O")) return OPENING;
		if(s.equals("L")) return LOOT;
		
		throw new RuntimeException("wrong step! "+s);
	}
	

	private Step makeWinStep() {
		
		Runnable onEnter = () -> {
			Sys.stopMusic();
			Sys.music(2, MUSIC_VOLUME, false);
			//doSound(19, 0.8f, 1f);
			
			doFinalScoreAnimation(true);
		
			
		};
		
		return new Step( onEnter, null, () -> Arrays.asList(
			new Action("Play Again", () -> {
				Sys.stopMusic();
				if (musicOn()) Sys.music(1, MUSIC_VOLUME, true);
				enterStep(INTRO);
			})
		));
	}	
	
	private Step makeDeathStep() {
		Runnable onEnter = () -> {
			deleteSavestate();
			Sys.stopMusic();
			doSound(19, 0.8f, 1f);
			
			// play sound!
			doFinalScoreAnimation(false);
			// this.animPG(, 6, null);
		};
		
		return new Step( onEnter, null, () -> Arrays.asList(
			new Action("Try Again", () -> { 
				if (musicOn()) Sys.music(1, MUSIC_VOLUME, true);
				enterStep(INTRO);
			})
		));
	}

	private void doFinalScoreAnimation(boolean win) {
		anims.add(Easing.LINEAR, 240, a -> {
			if (run.score() > getHighScore()) {
				if(!win) doSound(17, 0.8f, 1f); // no jingle for winscreen or it covers the music
				log.add(11, "!! NEW HIGH SCORE !!");
				log.add(15, "YOUR NEW HIGH SCORE IS: ", 13, "" + run.score());
				setHighScore(run.score());
				anims.add(Easing.LINEAR, 240, null, 0, 50, x -> {
					p.printBig("NEW HIGH SCORE!", 120, 50 - x / 4, 11, Align.CENTER);
				});
			}
		}, 0,50, a -> {
			p.printBig(win?"YOU WON!!":"YOU'RE DEAD!!", 120, 50 - a / 4, win?11:6, Align.CENTER);
		});
	}
	
	private long getHighScore() {
		String s = Sys.mem("highscore");
		if ((s == null) || s.equals("")) s = "0";
		return Long.parseLong(s);
	}
	
	private void setHighScore(long hs) {
		Sys.mem("highscore", "" + hs);	
	}
	
	private boolean soundOn() {
		if (sound == null) sound = !"OFF".equals(Sys.mem("SOUND"));
		return sound;
	}
	
	private void toggleSound() {
		sound = !soundOn();
		Sys.mem("SOUND", sound ? "ON" : "OFF");
	}
	
	private boolean musicOn() {
		if (music == null) music = !"OFF".equals(Sys.mem("MUSIC"));
		return music;
	}
	
	
	private boolean cursorOn() {
		if (cursor == null) cursor = !"OFF".equals(Sys.mem("CURSOR"));
		return cursor;
	}
	
	private void toggleMusic() {
		music = !musicOn();
		Sys.mem("MUSIC", music ? "ON" : "OFF");
		if (music) {
			Sys.music(1, MUSIC_VOLUME, true);
		} else {
			Sys.stopMusic();
		}
	}
	
	private void toggleCursor() {
		cursor = !cursorOn();
		Sys.mem("CURSOR", cursor ? "ON" : "OFF");
	}
	
	void openDoor() {
		if (run.pg.getInvCount(Item.Key) == 0) {
			
			boolean keyWarning = run.pg.getInvCount(Item.Gold) >= Craft.KEY_COST || run.pg.getInvCount(Item.BagOfGold)>0 ;
			
			if(keyWarning)
			{
				confirm("No keys! Continue?", () -> {
					dieOfKeys();
				}, () -> {
					doSound(6, 1f, 1f);
					log.add(15, "Go purchase some keys..");
				});

			}
			else
			{
				dieOfKeys();
			}
		} else {
			enterStep(OPENING);
		}
	}

	private void dieOfKeys() {
		log.add(5,"NO MORE KEYS!", 15, " You can not continue and");
		log.add(15," soon die of starvation, lost");
		log.add(15," in the dungeon!");
		
		enterStep(DEATH);
	}
	
	private void useItem(Item i) {
		doSound(11, 1f, 1f);
		i.usable.callback.use(i, this);
		refreshCommands();
	}
	
	private void useCombatItem(Item i) {
		doSound(12, 1f, 1f);
		i.combat.callback.use(i, this);
		refreshCommands();
	}
	
	public void doSound(int soundNum, float volume, float pitch) {
		if (soundOn()) Sys.sound(soundNum, volume, pitch);	
	}
	
	void grabLoot() {
		doSound(7, 1f, 1f);
		log.add(15, "You pick up ", 9,run.lootQty + "x ", 14, run.lootItem.name);
		run.pg.inventoryAdd(run.lootItem, run.lootQty);
		run.pg.inventoryAdd(Item.Gold, run.gold);
		enterStep(OUTDOOR);
	}
	
	void enterStep(Step newStep) {
		if (step!=null) step.onExit();
		t = 0; // reset global counter
		step = newStep;
		step.onEnter();
		refreshCommands();
	}
	
	private Monster chooseMonster() {
		// if(true) return new Monster(MonsterDef.TROLL);
		List<MonsterDef> eligibles = Stream.of(MonsterDef.values()).filter(m -> m.levels.contains(run.level)).collect(Collectors.toList());
		
		List<MonsterDef> uniques = eligibles.stream().filter(e -> e.unique).collect(Collectors.toList());
		if(uniques.size()>1)
		{
			throw new RuntimeException("Too many unique monsters for level "+run.level+": "+uniques);
		}
		
		MonsterDef md;
		
		if(uniques.isEmpty())
		{
			// no uniques, pick at random
			md = eligibles.get(r.nextInt(eligibles.size()));
		}
		else
		{
			// there's a unique monster for this level, pick it.
			md = uniques.get(0);
		}
		
		if(run.level <= LEVEL_MONSTER_GROWTH)
		{
			return new Monster(md);
		}
		else
		{
			return pumpMonster(run.level, md);
		}
	}

	protected static Monster pumpMonster(int level, MonsterDef md) {
		int diff = level - LEVEL_MONSTER_GROWTH;
		double delta = diff / 50.0; // in 50 levels, monsters will be twice as strong
		double mult = 1.0 + delta;
		if(level<LEVEL_MONSTER_GROWTH) mult = 1.0;
		return new Monster(md, mult);
	}
	
	protected static Loot calcLoot(Loot[] loots) {
		if (loots.length == 0) {
			return null;
		} else if (loots.length == 1) {
			return loots[0];
		}
		
		int tot = Stream.of(loots).mapToInt(l -> l.probability).sum();
		
		int v = r.nextInt(tot);
		
		for (int i = 0; i < loots.length; i++) {
			v = v - loots[i].probability;
			if (v<0) {
				return loots[i];
			}
		}
		
		throw new RuntimeException("No loot selected?! "+v);
	}
	
	public void render() {
		Sys.clear(Tic80.BLACK);
		// font.center("Doors of doom", 100, 10);
		
		if(step == WIN)
		{
			Sys.draw(9, 0, 0, 0, Math.max(0,192-(t/10)), 96, 96, 0, 0);
		}
		
		// the door
		boolean open = step == INDOOR || step == OPENING|| step == LOOT || step == WIN;
		Sys.draw(open ? 2 : 3, 0, 0, 0, 0, 96, 96, 0, 0);
		
		drawGame();
		
		//RectangleDrawer.outline(sys, x, y, w, h, sheet, color);
		
		if (step == INDOOR) {
			drawMonster(run.monster);
		} else if (step == LOOT) {
			p.spr(Item.Gold.sprite, 38, 30, -1);
			p.print("x" + run.gold, 48, 32, 15, Align.LEFT);
			p.print("Gold", 50, 42, 15, Align.CENTER); // TODO printc
			
			p.spr(run.lootItem.sprite, 38, 60, -1);
			p.print("x" + run.lootQty, 48, 62, 15, Align.LEFT);
			if(run.lootItem.name.contains(" "))
			{
				String[] tok = run.lootItem.name.split(" ", 2); 
				p.print(tok[0], 50, 72, 15, Align.CENTER);
				p.print(tok[1], 50, 80, 15, Align.CENTER);
			}
			else
			{
				p.print(run.lootItem.name, 50, 72, 15, Align.CENTER);
			}
		}
	
		// richPrint(10, 10, 12, "ciao", 13, " a", 14, " tutti");
		if (!anims.isRunning()) buttons.draw();
		
		Sys.fill(0, BUTTONS_X, 0, 70, 10, Tic80.BLACK);
		p.print("NOW WHAT?", BUTTONS_X + 8, 1, 6, Align.LEFT);
		
		Sys.fill(0, 0, 8 * 12 + 4, 240, 36, Tic80.DARK_RED);
		log.draw(1, 8 * 13 + 4 - 7);
		
		if (currWidget != null) currWidget.draw();
		
		Sys.fill(0, 0, 8 * 12, 240, 1, Tic80.BLACK);
		Sys.fill(0, 0, 8 * 12 + 1, 240, 1, Tic80.BROWN);
		Sys.fill(0, 0, 8 * 12 + 2, 240, 1, Tic80.DARK_RED);
		Sys.fill(0, 0, 8 * 12 + 3, 240, 1, Tic80.BLACK);
		
		if (step == INTRO) {
			printSmall(buildId, 202, 90, 1, Align.CENTER);
		}
		
		
		anims.update();
		
		// mouse pointer
		if (cursorOn()) Sys.draw(4, m.x(), m.y(), 120, 120, 8, 8, 0, 0);
	}
	
	private void drawMonster(Monster m) {
		drawMonsterSprite(m.type.sprite);
		
		printSmall(m.hp + "/" + m.maxHp, 86, 90, 6, Align.RIGHT);
		printSmall(m.attack.min + "-" + m.attack.max, 10, 90, 5);
	}
	
	private void drawMonsterSprite(int sprite) {
		int idx = -1 - sprite;
		int sx = (idx % 4) * 32;
		int sy = (idx / 4) * 32;
		int ax = 0;
		int ay = 0;
		
		if (run.shake > 0) {
			ax = r.nextInt(4) - 2;
			ay = r.nextInt(4) - 2;
			run.shake --;
		}
		
		Sys.draw(5, 30 + ax, 50 + ay, sx, sy, 32, 32, 0,0);
	}
	
	private void drawGame() {
		ShapeDrawer.outline(STATS_X,0,66,12*8, 0, Tic80.P[1]);
		// widget:draw(8*12+3, 10)
		
		p.spr(279, STATS_X, 0, 0);
		p.spr(279, STATS_X + 58, 0, 0);
		p.spr(279, STATS_X, 88, 0);
		p.spr(279, STATS_X + 58, 88, 0);
		
		printStats(STATS_X + 2, 8);
		p.print("Stats", STATS_X + 20, 2, 15, Align.LEFT);
		// p.print("Equip:", STATS_X + 2, 8 + 32, 15, Align.LEFT);
		
		int cx = STATS_X+3;
		int cy = 50;
		drawEquip(cx, cy);
	}
	
	private void drawEquip(int cx, int cy) {
		int ax = 0;
		int ay = 0;
		
		if (run.shakePg > 0) {
			ax = r.nextInt(4) - 2;
			ay = r.nextInt(4) - 2;
			run.shakePg --;
		}
		
		cx = cx + ax;
		cy = cy + ay;
		
		p.rectb(cx + 14, cy, 12, 12, 8);
		p.rectb(cx, cy + 16, 12, 12, 8);
		p.rectb(cx + 14, cy + 14, 12, 12, 8);
		p.rectb(cx + 28, cy + 16, 12, 12, 8);
		p.rectb(cx + 14, cy + 28, 12, 12, 8);
		
		for (Place p : Place.values()) {
			int sprite = p.defaultSprite;
			Item i = run.pg.equip.get(p);
			
			if (i != null) sprite = i.sprite;
			
			this.p.spr(sprite,cx+p.x, cy+p.y, 8);
		}
		
		drawEffects();
	}

	private void drawEffects() {
		int ex = EFFECTS_X;
		int ey = EFFECTS_Y;
		
		for (Entry<Effect, Integer> e : run.pg.effects.entrySet()) {
			
			p.spr(e.getKey().sprite, ex, ey, 8);
			p.print(e.getValue().toString(), ex + 9, ey + 1, 15, Align.LEFT);
			ey = ey + EFFECTS_SPACING;
		}
	}
	
	private void printSmall(String text, int x, int y, int color, Align align) {
		Sys.color(Tic80.P[color]);
		smallFont.print(text, x, y, align);
		Sys.color(Tic80.P[15]);
	}
	
	private void printSmall(String text, int x, int y, int color) {
		Sys.color(Tic80.P[color]);
		smallFont.print(text, x, y, Align.LEFT);
		Sys.color(Tic80.P[15]);
	}
	
	private void printStats(int x, int y) {
		boolean blink = ( (run.pg.hp < 10) || (run.pg.hp<run.pg.maxHp/3) ) && !(run.pg.hp == 0) ;
		
		if ((!blink) || ((t / 15) % 2 == 0)) {
			printSmall("Life", x, y, 6);
			printSmallRight(run.pg.hp + "/" + run.pg.maxHp, x + 63, y, 6);
		}
		
		printSmall("Attack", x, y + 6, 5);
		printSmall("Armour", x, y + 12, 12);
		printSmall("Level", x, y + 24, 10);
		printSmall("Score", x, y + 30, 13);
		
		int nk = run.pg.getInvCount(Item.Key);
		
		if ((nk > 10) || ((t / 15) % 2 == 0)|| (run.pg.hp == 0)) {
			printSmall("Keys", x, y + 18, 9);
			printSmallRight(nk + "", x + 63, y + 18, 9);
		}
		
		printSmallRight(run.pg.attack.toString(), x + 63, y + 6, 5);
		printSmallRight(run.pg.armour + "", x + 63, y + 12, 12);
		printSmallRight(run.level + "", x + 63, y + 24, 10);
		printSmallRight(run.score() + "", x + 63, y + 30, 13);

	}

	private void printSmallRight(String text, int x, int y, int color) {
		int w = smallFont.width(text);				
		printSmall(text, x - w, y, color);
	}

	public void confirm(String question, Runnable onYes, Runnable onNo) {
		currWidget = new ConfirmWidget(this, p, question, onYes, onNo);
	}

	private boolean inside(Pointer m, int x1, int y1, int x2, int y2)
	{
		return m.x()>=x1 &&
				m.x()<x2 &&
				m.y()>=y1 &&
				m.y()<y2;
	}
	public boolean update() {
		m = Sys.pointers()[0];
		
		
		if (currWidget != null) {
			if (!currWidget.update(m)) currWidget = null;
		/*
  		} else if (craftWidget != null) {
			if (!craftWidget.update(m)) craftWidget = null;
  		*/
		} else {
			// if (m.btn[0] && !oldClick) {
			
			if(m.btnp(0) && step == OUTDOOR && m.x() <= 70 ) {
				
				if(inside(m, 30 ,50, 50, 65))
				{
					System.out.println("TOC TOC");
				}
				
			}
			
			if(m.btnp(0) && m.x()>=142 && m.x()<=160 && m.y()>50)
			{
				clickOnEffect(m.x()-142, m.y()-50);
			}
			
			
			if (!anims.isRunning()) {
				// doSound(1, 1f, r.nextFloat()*1.5f+0.5f);
				buttons.update(m);
			}
			// }
			
			//oldClick = m.btn[0];
		}
		
		t++;
		return true;
	}
	
	private void clickOnEffect(int x, int y) {
		int idx = y/9;
		int numEffects = run.pg.effects.size();
		if(idx < numEffects)
		{
			Effect e = new ArrayList<>(run.pg.effects.entrySet()).get(idx).getKey();
			log.add( /*-1, e.sprite, 15, " ", */ e.positive ?  11 : 6, e.name, 15, ":");
			log.add(15, e.desc);
		}
		
		
	}

	@Override
	public SysConfig sysConfig() {
		return new SysConfig(240, 136, VirtualScreenMode.FILL_SIDE, "Doors of Doom", "doorsofdoom");
	}
	
	@Override
	public Log getLog() {
		return log;
	}
	
	@Override
	public PrintUtils getPrintUtils() {
		return p;
	}
	
	@Override
	public Run getRun() {
		return run;
	}
	
	@Override
	public AnimationManager getAnims() {
		return anims;
	}
	
	@Override
	public void refreshCommands() {
		buttons.set(step.onActions());	
	}
	
	@Override
	public boolean loop() {
		boolean x = update();
		render();
		return x;
	}

	@Override
	public void exitDungeon() {
		run.exited = true;
		log.add(15, "");
		log.add(7, "       --=======  ", 15, "!! GAME  FINISHED !!",7,"  ======--    ");
		log.add(15, "");
		log.add(15, "You head toward the exit, tired but");
		log.add(15, "victorious. You fought well, warrior.");
		log.add(14, "Your final score is: ", 8, ""+run.score());
		enterStep(WIN);
		
	} 
}
