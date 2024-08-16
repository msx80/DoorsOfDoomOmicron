package com.github.msx80.doorsofdoom;

public class Action {
	public final Richtext label;
	public final Runnable callback;
	
	public Action(String label, Runnable callback) {
		this.label = Richtext.of(label);
		this.callback = callback;
	}

	public Action(Richtext label, Runnable callback) {
		this.label = label;
		this.callback = callback;
	}

	@Override
	public String toString() {
		return label.toString() ;
	}
}
