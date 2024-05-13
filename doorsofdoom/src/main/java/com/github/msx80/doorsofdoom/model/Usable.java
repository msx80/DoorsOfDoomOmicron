package com.github.msx80.doorsofdoom.model;

public class Usable {

	public interface UsableFunc{
	
		public void use(Item item, GameInterface game);
	}
	
	public final String command;
	public final UsableFunc callback;

	
	public Usable(String command, UsableFunc callback) {
		this.command = command;
		this.callback = callback;
	}


	public static final Usable of(String command, UsableFunc callback)
	{
		return new Usable(command, callback);
	}
}
