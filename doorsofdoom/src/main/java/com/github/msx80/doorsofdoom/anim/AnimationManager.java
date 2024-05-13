package com.github.msx80.doorsofdoom.anim;

import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class AnimationManager {
	private LinkedList<Animation> animations = new LinkedList<Animation>();
	
	
	public void update()
	{
		if ( animations.isEmpty() ) return;
		boolean finished = animations.getFirst().advance();
		if(finished)
		{
			animations.removeFirst();
		}
		
	}
	public Animation add(Easing easing, int ttl, Consumer<Animation> onEnd,	Consumer<Animation> onUpdate)
	{
		return this.add(new Animation(easing, ttl, onEnd, onUpdate));
	}
	public Animation add(Easing easing, int ttl, Consumer<Animation> onEnd,	int start, int end, IntConsumer onUpdate )
	{
		return this.add(new Animation(easing, ttl, onEnd, a -> {
			float f = ((float)end)*a.position + ((float)start)*(1f-a.position);
			onUpdate.accept(  (int) f ); 
		}));
	}
	public Animation add(Animation a)
	{
		animations.add(a);
		return a;
	}
	public boolean isRunning() {
		
		return !animations.isEmpty();
	}
}
