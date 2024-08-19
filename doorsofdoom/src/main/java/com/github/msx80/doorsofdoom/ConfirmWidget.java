package com.github.msx80.doorsofdoom;

import java.util.Arrays;
import java.util.List;

import com.github.msx80.doorsofdoom.model.GameInterface;
import com.github.msx80.omicron.basicutils.ShapeDrawer;
import com.github.msx80.omicron.basicutils.palette.Tic80;

public class ConfirmWidget extends Widget<Action> {

	private static final int ITEM_HEIGHT = 20;
	GameInterface g;
	private PrintUtils p;
	private String question;
	private Runnable onYes;
	private Runnable onNo;

	public ConfirmWidget(GameInterface g, PrintUtils p, String question, Runnable onYes, Runnable onNo) {
		super(p.sys, 120 - 35, 40, 70, ITEM_HEIGHT * 2 + 1, ITEM_HEIGHT);
		
		this.question = question;
		this.onYes = onYes;
		this.onNo = onNo;
		this.p = p;
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
	protected List<Action> lines() {
		return Arrays.asList(
			new Action(Richtext.of("Yes"), onYes),
			new Action(Richtext.of("No"), onNo)
		);
	}

	@Override
	public void drawBackground(int x, int y) {
		p.sys.fill(0, x - 3, y - 11, w + 6, h + 12, Tic80.BLACK);
		
		ShapeDrawer.outline(sys, x - 2, y - 10, w + 4, h + 10, 0, Tic80.BROWN);
		p.richPrint(x, y - 8, 14, question);
	}

	@Override
	protected boolean selected(int idx, Action line) {
		line.callback.run();
		return false;
	}

	@Override
	protected boolean clickedOutside(int x, int y) {
		return false;
	}

	@Override
	public void drawForeground(int x, int y) {
		// TODO Auto-generated method stub
	}

	@Override
	public void drawItem(int ax, int ay, int idx, Action a) {
		p.drawBtn(ax,ay+1,70,ITEM_HEIGHT-2);
		p.richPrint(ax + 2, ay + 4, a.label.tokens);		
	}
}
