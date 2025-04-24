package com.github.msx80.doorsofdoom;

public abstract class RichWidget extends Widget<Richtext> {

	PrintUtils p;
	
	public RichWidget(PrintUtils p, int x, int y, int w, int h) {
		super(x, y, w, h, 11);
		this.p = p;
	}
	
	@Override
	public void drawItem(int ax, int ay, int idx, Richtext r) {
		p.richPrint(ax, ay, r.tokens);
	}
}
