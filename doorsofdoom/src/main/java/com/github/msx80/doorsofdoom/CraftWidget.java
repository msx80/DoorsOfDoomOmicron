package com.github.msx80.doorsofdoom;

import java.util.List;
import java.util.stream.Collectors;

import com.github.msx80.doorsofdoom.model.Craft;
import com.github.msx80.doorsofdoom.model.GameInterface;
import com.github.msx80.doorsofdoom.model.Item;
import com.github.msx80.omicron.api.Sys;
import com.github.msx80.omicron.basicutils.palette.Tic80;
import com.github.msx80.omicron.basicutils.text.TextDrawer.Align;

public class CraftWidget extends RichWidget {

	GameInterface g;

	public CraftWidget(GameInterface g, PrintUtils p, int x, int y, int w, int h) {
		super(p, x, y, w, h);
		this.g = g;
	}

	/*
 	local s = {-1, CRAFTS[i].output.spr, 15, " = "}

	for k,v in pairs(CRAFTS[i].ingredients) do
		table.insert(s, -1)
		table.insert(s, k.spr)
	    	table.insert(s, 15)
		table.insert(s, " "..v.." ")
	*/
	
	@Override
	protected List<Richtext> lines() {
		return Craft
			.visible(g.getRun().pg)
			//.ALL
			.stream().map(
				c -> c.toRichtext()
			).collect(Collectors.toList());
	}

	@Override
	public void drawBackground(int x, int y) {
		Sys.fill(0, x - 4, y - 10, w + 6, h + 12, Tic80.BLACK);
	}

	@Override
	public void drawForeground(int x, int y) {
		Sys.fill(0, x - 4, y - 11, w + 6, 9, Tic80.DARK_RED);
		p.print("Craft what?", x + 12, y - 9, 15, Align.LEFT);
		p.rectb(x - 4, y - 11, w + 6, h + 13, 14);
	}

	@Override
	protected boolean selected(int idx, Richtext line) {
		g.doSound(13, 1f, 1f);
		Craft c = (Craft) line.userdata;
		
		for (Item i : c.getIngredients().keySet()) {
			int required = c.getIngredients().get(i);
			g.getRun().pg.inventoryAdd(i, -required);
		}
		
		g.getRun().pg.inventoryAdd(c.getOutput(), c.getCount());
		String cnt = c.getCount() == 1 ? "":" (x"+c.getCount()+")";
		g.getLog().add(15, "You obtain ", /*-1, c.getOutput().sprite, */ 14, c.getOutput().name, 7, cnt, 15, "!");
		g.refreshCommands();
		return false;
	}

	@Override
	protected boolean clickedOutside(int x, int y) {
		g.doSound(18, 1f, 1f);
		return false;
	}
}
