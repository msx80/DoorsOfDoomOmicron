package com.github.msx80.doorsofdoom.anim;

import java.util.function.Consumer;

public class Animation {
	
	Easing easing;
	
	int ttl; // in frames
	int frame; // current frame
	public float position; // position, post easing
	float percentage; // percentage of completition, pre easing 
	
	Consumer<Animation> onEnd;
	Consumer<Animation> onUpdate; // called each frame

	public Animation(Easing easing, int ttl, Consumer<Animation> onEnd, Consumer<Animation> onUpdate) {
		this.easing = easing;
		this.ttl = ttl;
		this.frame = 0;
		this.position = 0f;
		this.percentage = 0f;
		this.onEnd = onEnd;
		this.onUpdate = onUpdate;
	}
	
	public boolean finished() {
		return frame >= ttl;
	}
	
	public boolean advance() {
		onUpdate.accept(this);
		
		this.frame++;
		percentage = (float)frame / (float)ttl;
		position = easing.fun.apply(percentage);
		
		// update stuff
		
		if (finished()) {
			if (onEnd != null) onEnd.accept(this);
			
			return true;
		} else {
			return false;
		}
	}
}
