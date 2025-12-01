package com.github.msx80.doorsofdoom.anim;

import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class AnimationManager {
	private LinkedList<Animation> animations = new LinkedList<Animation>();
	
	boolean fast = false;
	
	public void update() {
		if (animations.isEmpty()) return;
		
		boolean finished = animations.getFirst().advance();
		if (finished) {
			animations.removeFirst();
		}
	}
	/*
	public Animation add(Easing easing, int ttl, Consumer<Animation> onEnd,	Consumer<Animation> onUpdate) {
		return this.add(new Animation(easing, ttl, onEnd, onUpdate));
	}*/
	
	public Animation add(Easing easing, int ttl, Consumer<Animation> onEnd,	int start, int end, IntConsumer onUpdate) {
		return this.add(new Animation(easing, fast ? (int)(ttl /1.5) : ttl, onEnd, a -> {
			float f = ((float)end) * a.position + ((float)start) * (1f - a.position);
			onUpdate.accept((int)f); 
		}));
	}
	
	public Animation add(Animation a) {
		animations.add(a);
		return a;
	}
	
	public boolean isRunning() {
		return !animations.isEmpty();
	}

	public boolean isFast() {
		return fast;
	}

	public void setFast(boolean fast) {
		this.fast = fast;
	}
	
	
	
}
