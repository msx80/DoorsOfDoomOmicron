package com.github.msx80.doorsofdoom;

import java.util.List;
import java.util.stream.Collectors;

import com.github.msx80.doorsofdoom.model.GameInterface;
import com.github.msx80.doorsofdoom.model.Item;
import com.github.msx80.doorsofdoom.model.Pg;
import com.github.msx80.omicron.api.Sys;
import com.github.msx80.omicron.basicutils.Geometry;
import com.github.msx80.omicron.basicutils.SurfUtils;
import com.github.msx80.omicron.basicutils.text.TextDrawer.Align;

public class InventoryWidget extends RichWidget {
	
	private Pg pg;
	private Item selected = null;
	private int zoomsurf;
	
	private int btnx, btny, btnw, btnh;
	private GameInterface g;
	
	public InventoryWidget(GameInterface g, int zoomsurf, PrintUtils p, int x, int y, int w, int h) {
		super(p, x, y, w, h);
		
		this.zoomsurf = zoomsurf;
		this.g = g;
		this.pg = g.getRun().pg;
		
		btnx = 24 + x + 130;
		btny = 2 + y;
		btnw = 60;
		btnh = 13;
	}

	private void displayItem(Item item, int x, int y) {
		//p.spr(item.sprite,x,y,-1);
		sys.draw(zoomsurf, x, y, 0, 0, 16, 16, 0,0);
		int yy = y + 20;
		p.richPrint(x, yy, 5, item.name);
		yy += 10;
		
		/*
  		if item.usable then
    			rectb(24 + x, 7 + y, 50, 9, 14)
       			print("Z TO USE", 28 + x, 9 + y, 6)
	  		richPrint({10, "- Usable: "..item.usable.name }, x, yy, 15, false, 1, false)
     			yy = yy + 7
		end
	   
    		if item.combat then
      			richPrint({10, "- Combat: "..item.combat.name }, x, yy, 15, false, 1, false)
	 		yy = yy + 7
    		end
      		*/
		
		if (item.attack != null) {
			p.richPrint(x, yy, 10, "- Attack: " + item.attack.min + "-" + item.attack.max);
			yy += 7;
		}
		
		if (item.armour != 0) {
			String sgn = item.armour > 0 ?  "+" : "";
			p.richPrint(x, yy, 10, "- Armour: " + sgn + item.armour);
			yy += 7;
		}
		
		if (item.equip != null) {
			if (pg.isEquipped(item)) {
				// p.rectb(24 + x, 7 + y, 70, 9, 14);
				// p.print("Z TO REMOVE", 28 + x, 9 + y, 6, Align.LEFT);
				p.drawBtn(btnx, btny, btnw, btnh);
				p.print("REMOVE", 24 + 30 + x, 6 + y, 15, Align.CENTER);
			} else {
				// p.rectb(24 + x, 7 + y, 60, 9, 14);
				p.drawBtn(btnx, btny, btnw, btnh);
				p.print("EQUIP", 24+30+x, 6+y, 15, Align.CENTER);
			}
			
			p.richPrint(x, yy, 10, "- Equip: " + item.equip);
			yy += 7;
		}
		
		yy += 2;
		
		for (String l : item.flavour) {
			p.richPrint(x, yy, 15, l);
			yy += 7;
		}
	}

	@Override
	protected List<Richtext> lines() {
		return pg
			.inventory
			.keySet()
			.stream()
			.sorted((a, b) -> a.name.compareTo(b.name))
			// .filter(i -> i.equip != null)
			.map(i -> Richtext.with(i, -1, i.sprite, 15, " " + i.name, 14, " [" + pg.getInvCount(i) + "]"))
			.collect(Collectors.toList());
	}
	
	@Override
	protected boolean selected(int idx, Richtext line) {
		// System.out.println(line);
		selected = (Item) line.userdata;
		updZoom(selected);
		return true;
	}
	
	private void updZoom(Item i) {
		int idx = i.sprite - 256;
		int sx = (idx % 16) * 8;
		int sy = (idx / 16) * 8;
		SurfUtils.zoom(sys, 4, zoomsurf, sx, sy, 0, 0, 8, 8, 2);
	}
	
	@Override
	protected boolean clickedOutside(int x, int y) {
		if (x >= 222 && y <= 16) {
			g.doSound(18, 1f, 1f);
			return false;
		}
		
		if (selected == null) return true;
		
		if (Geometry.inRect(x, y, btnx, btny, btnw, btnh)) {
			if (pg.isEquipped(selected)) {
				pg.unequip(selected);
				g.doSound(9, 1f, 1f);
				g.getLog().add(15, "You remove ", 14, selected.name);
			} else {
				pg.equip(selected);
				g.doSound(8, 1f, 1f);
				g.getLog().add(15, "You equip ", 14, selected.name);
			}
			
			invalidateAndReload();
			return true;
		}
		return true;
	}

	@Override
	public void drawForeground(int x, int y) {
		// TODO Auto-generated method stub
		x -= 2;
		y -= 2;
		p.rectb(x, y, 130, 12 * 8, 1);
		p.rectb(x + 129, y, 111, 12 * 8, 1);
		
		if (selected != null) {
			displayItem(selected, 132 + x, y + 2);
		}
		
		sys.draw(4, x + 222, y + 2, 0, 112, 16, 16, 0, 0);
	}

	@Override
	public void drawBackground(int x, int y) {
		x -= 2;
		y -= 2;
		p.rect(x, y, 240, 12 * 8, 0);
	}
}
