package com.github.msx80.doorsofdoom;

import com.github.msx80.omicron.api.Sys;
import com.github.msx80.omicron.basicutils.Colors;
import com.github.msx80.omicron.basicutils.ShapeDrawer;
import com.github.msx80.omicron.basicutils.palette.Tic80;
import com.github.msx80.omicron.basicutils.text.TextDrawer;
import com.github.msx80.omicron.basicutils.text.TextDrawer.Align;

public class PrintUtils {
	
	private TextDrawer font;
	private TextDrawer bigFont;
	
	private int clr = Colors.fromHex("504030");
	
	public PrintUtils(TextDrawer font, TextDrawer bigFont) {
		this.font = font;
		this.bigFont = bigFont;
	}
	
	public int size(String s)
	{
		return font.width(s);
	}
	
	public void print(String string, int x, int y, int color, Align align) {
		Sys.color(Tic80.P[color]);
		font.print(string, x, y, align);
		Sys.color(Colors.WHITE);
	}
	
	public void drawBtn(int x, int y, int w, int h) {
		Sys.fill(0, x, y, w, h, clr);
		Sys.fill(0, x, y, 1, 1, Tic80.BLACK);
		Sys.fill(0, x + w - 1, y + h - 1, 1, 1, Tic80.BLACK);
		Sys.fill(0, x + w - 1, y, 1, 1, Tic80.BLACK);
		Sys.fill(0, x, y + h - 1, 1, 1, Tic80.BLACK);
		Sys.fill(0, x + 1, y, w - 2, 1, Tic80.LIGHT_GRAY);
		Sys.fill(0, x, y + 1, 1, h - 2, Tic80.LIGHT_GRAY);
	}
	
	public void richPrint(int sx, int sy, Object... tokens) {
		
	    	if ((tokens.length == 1) && (tokens[0] instanceof String)) {
	    		print((String) tokens[0], sx, sy, 15, Align.LEFT);
	    		return;
		}
		
	    	for (int i = 0; i < tokens.length; i += 2) {
			Integer c = (Integer) tokens[i];
			Object p1 = tokens[i + 1];
			
			int w = 0;
			if (c == -1) {
				spr((Integer)p1, sx, sy-1, 0);
				w = 8;
				//throw new RuntimeException("Sprite not supported yet in richprint");
			} else {
				String str = p1.toString();
				w = font.width(str);
				print(str,sx,sy,c, Align.LEFT);
			}
			
			sx = sx + w;
		}
		
		    /*if type(tokens)=="string" then
		      print(tokens,sx,sy,15,false,1,false)
		    else
		      for n=1,#tokens,2 do
		        local str = tokens[n+1]
		        local c = tokens[n]
		        local w
		        if c == -1 then
		        
		        else
		         w = print(str,sx,sy,c,false,1,false)
		        end
		        sx=sx+w
		      end
		    end*/
	}

	public void printBig(String string, int x, int y, int color, Align a) {
		Sys.color(Tic80.P[15]);
		bigFont.print(string, x + 1, y, a);
		bigFont.print(string, x - 1, y, a);
		bigFont.print(string, x, y + 1, a);
		bigFont.print(string, x, y - 1, a);
		
		bigFont.print(string, x + 1, y + 1, a);
		bigFont.print(string, x + 1, y - 1, a);
		bigFont.print(string, x - 1, y + 1, a);
		bigFont.print(string, x - 1, y - 1, a);
		
		Sys.color(Tic80.P[color]);
		bigFont.print(string, x, y, a);
		Sys.color(Colors.WHITE);
	}
	
	public void rectb(int x, int y, int w, int h, int c) {
		ShapeDrawer.outline( x, y, w, h, 0, Tic80.P[c]);
	}
	
	public void rect(int x, int y, int w, int h, int c) {
		Sys.fill(0, x, y, w, h, Tic80.P[c]);	
	}
	
	public void spr(int idx, int x, int y, int bgcol) {
		idx -= 256;
		int sx = (idx % 16) * 8;
		int sy = (idx / 16) * 8;
		
		Sys.draw(4, x, y, sx, sy, 8, 8, 0, 0);
	}
}
