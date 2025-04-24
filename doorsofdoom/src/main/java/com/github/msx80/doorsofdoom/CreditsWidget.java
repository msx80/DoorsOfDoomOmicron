package com.github.msx80.doorsofdoom;

import java.util.Arrays;
import java.util.List;

import com.github.msx80.doorsofdoom.model.GameInterface;
import com.github.msx80.omicron.api.Sys;
import com.github.msx80.omicron.basicutils.palette.Tic80;

public class CreditsWidget extends RichWidget {

	GameInterface g;
	
	public CreditsWidget(GameInterface g, PrintUtils p, int x, int y, int w, int h) {
		super(p, x, y, w, h);
		itemsHeight = 7;
		this.g = g;
	}

	@Override
	protected List<Richtext> lines() {
		return Arrays.asList(
			Richtext.of(12, "Doors Of Doom", 15, " - (c) 2024"),
			Richtext.of(""),
			Richtext.of(15, "An open source game by ", 14, "MSX"),
			Richtext.with("https://livellosegreto.it/@msx", 13, "https://livellosegreto.it/@msx"),
			Richtext.of(""),
			Richtext.with("https://github.com/msx80/DoorsOfDoomOmicron/", 13, "Sources on Github"),
			Richtext.of(""),
			Richtext.of(9, "Over 27 monsters!"),
			Richtext.of(12, "Over 64 items!"),		
			Richtext.of(9, "Find the sweetest loot!"),		
			Richtext.of(12, "Adventure into the"),
			Richtext.of(9, "Dungeon of Infinite Doors!"),
			Richtext.of(""),
			Richtext.of("Ported from a TIC-80 game by MSX"),
			Richtext.of(""),
			Richtext.of("Sounds by: Juhani Junkala"),
			Richtext.with("https://juhanijunkala.com/", 13, "https://juhanijunkala.com/"),
			Richtext.of(""),
			Richtext.of("Game Music by: RandomMind"),
			Richtext.with("https://www.youtube.com/@randommynd", 13, "https://www.youtube.com/@randommynd"),
			Richtext.of(""),
			Richtext.of("Win Music by: Vincent Fasano"),
			Richtext.with("https://www.vincentfasano.com/", 13, "https://www.vincentfasano.com/"),
			Richtext.of(""),
			Richtext.of("Beta Testing: ceonello, carlessa"),
			Richtext.of("Thanks to: ATP37, krushia"),
			Richtext.of(""),
			Richtext.of("Powered by: OMICRON game engine"),
			Richtext.with("https://github.com/msx80/Omicron", 13, "https://github.com/msx80/Omicron"),
			Richtext.of(""),
			Richtext.of("Powered by: LIBGDX"),
			Richtext.with("https://libgdx.badlogicgames.com/", 13, "https://libgdx.badlogicgames.com/"),
			Richtext.of(""),
			Richtext.of("Camel Heads lives on!"),
			Richtext.of(""),
			Richtext.of("Dedicato ai miei Stroppoletti")
			/*,
			Richtext.of("Over 27 monsters!"),
			Richtext.of("Over 64 items!"),		
			Richtext.of("Find the sweetest loot!"),		
			Richtext.of("Adventure into the"),
			Richtext.of("Dungeon of Infinite Doors!")
			*/
		); 
	}

	@Override
	public void drawBackground(int x, int y) {
		Sys.fill(0, x - 4, y - 4, w + 8, h + 5, Tic80.BLACK);
	}

	@Override
	public void drawForeground(int x, int y) {
		p.rectb(x - 4, y - 4, w + 8, h + 5, 14);
	}

	@Override
	protected boolean selected(int idx, Richtext line) {
		if (line.userdata != null) {
			g.getLog().add(15, "Opening url...");
			String res = (String)Sys.hardware("com.github.msx80.omicron.plugins.builtin.UrlOpenerPlugin", "OPEN", (String) line.userdata);
			g.getLog().add(15, res);
		}
		return true;
	}

	@Override
	protected boolean clickedOutside(int x, int y) {
		g.doSound(18, 1f, 1f);
		return false;
	}
}
