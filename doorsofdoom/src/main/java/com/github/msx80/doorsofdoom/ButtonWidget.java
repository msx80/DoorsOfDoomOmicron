package com.github.msx80.doorsofdoom;

import java.util.ArrayList;
import java.util.List;

public class ButtonWidget extends Widget<Action> {

	private static final int BUTTON_Y = 14;
	private List<Action> pulsanti = new ArrayList<>();
	PrintUtils p;
	
	public ButtonWidget(PrintUtils p, int x, int y, int w, int h) {
		super(p.sys, x, y, w, h, BUTTON_Y);
		this.p = p;
	}

	public void set(List<Action> pulsanti) {
		this.pulsanti = pulsanti;
		invalidateAndReload();
	}

	@Override
	protected List<Action> lines() {
		return pulsanti;
	}

	@Override
	public void drawBackground(int x, int y) {
		// p.sys.fill(0, x, y, w+3, h, Tic80.BLUE_GRAY);
	}

	@Override
	public void drawForeground(int x, int y) {}

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
	public void drawItem(int ax, int ay, int idx, Action a) {
		p.drawBtn(ax,ay,70,13);
		p.richPrint(ax+2,ay+4,a.label.tokens);
	}
}
