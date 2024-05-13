package com.github.msx80.doorsofdoom.model;

import com.github.msx80.doorsofdoom.DoorsOfDoom;

public class Range {
	
	public int min, max;

	public Range(int low, int high) {
		this.min = low;
		this.max = high;
	}
	
	public static Range of(int low, int high)
	{
		return new Range(low, high);
	}
	
	public String toString()
	{
		return min+"-"+max;
	}

	public int random() {
		if(max==min) return max;
		return DoorsOfDoom.r.nextInt(max-min+1)+min;
	}

	public boolean contains(int v) {
		
		return v >= min && v<= max;
	}
	
}
