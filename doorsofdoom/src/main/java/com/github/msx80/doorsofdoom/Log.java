package com.github.msx80.doorsofdoom;

import java.util.LinkedList;

public class Log {

	public final LinkedList<Richtext> lines = new LinkedList<Richtext>();
	private PrintUtils p;

	public Log(PrintUtils p) {
		this.p = p;
		add("");
		add("");
		add("");
		add("");
		add("");
	}

	public void add(Object... tokens) {
		while (lines.size() >= 5) {
			lines.removeFirst();
		}
		lines.add(Richtext.of(tokens));
	}
	
	public void draw(int x, int y) {
		for (Richtext r : this.lines) {
			p.richPrint(x, y, r.tokens);
			y += 7;
		}	
	}

}
