package com.github.msx80.doorsofdoom;

import java.util.Arrays;

public class Richtext {

	public Object[] tokens;
	public Object userdata;

	public Richtext(Object... tokens) {
		this.tokens = tokens;
	}
	
	public static Richtext of(Object... tokens)
	{
		return new Richtext(tokens);
	}	
	public static Richtext with(Object userdata, Object... tokens)
	{
		Richtext r= new Richtext(tokens);
		r.userdata = userdata;
		return r;
	}
	
	public String toString()
	{
		return userdata == null ? "" : "["+userdata+"] "+Arrays.toString(tokens);
	}
}
