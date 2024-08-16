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
	
	/*
 	log = { lines = { {0, ""}, {15, "Welcome to", 6, " Doors of Doom", 15, "!  by", 8,
	" MSX"}, {14, "Fight your way deep into the dungeon!"}, {0, ""},
	{15, "High score: ", 5, pmem(0)}, }, add = function (self, lin)
	table.remove(self.lines, 1) table.insert(self.lines, lin) end,
	print = function(self, x, y) for i = 1, #self.lines do local tokens = self.lines[i]
	local sy = y + (i - 1) * 7 local sx = x richPrint(tokens, sx, sy) end end, } 
  	*/
}
