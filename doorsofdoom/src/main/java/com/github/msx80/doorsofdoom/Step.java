package com.github.msx80.doorsofdoom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Step {
	private final Runnable onEnter;
	private final Runnable onExit;
	private final Callable<List<Action>> onActions;
	
	public Step(Runnable onEnter, Runnable onExit, Callable<List<Action>> onActions) {
		this.onEnter = onEnter;
		this.onExit = onExit;
		this.onActions = onActions;
	}
	
	public void onEnter() {
		if (onEnter != null) onEnter.run();
	}
	
	public void onExit() {
		if (onExit != null) onExit.run();
	}
	
	public List<Action> onActions() {
		if (onActions != null)
			try {
				return onActions.call();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		return new ArrayList<Action>();
	}
}
