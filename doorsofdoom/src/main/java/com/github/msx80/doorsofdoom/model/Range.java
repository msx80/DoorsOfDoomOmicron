package com.github.msx80.doorsofdoom.model;

import java.util.Objects;

import com.github.msx80.doorsofdoom.DoorsOfDoom;
import com.github.msx80.doorsofdoom.dump.DumpReader;
import com.github.msx80.doorsofdoom.dump.DumpWriter;
import com.github.msx80.doorsofdoom.dump.Dumpable;

public class Range implements Dumpable {
	public int min, max;
	
	public Range() {}
	
	public Range(int low, int high) {
		this.min = low;
		this.max = high;
	}
	
	public static Range of(int low, int high) {
		return new Range(low, high);
	}
	
	public String toString() {
		return min + "-" + max;
	}
	
	public int random() {
		if(max == min) return max;
		return DoorsOfDoom.r.nextInt(max - min + 1) + min;
	}
	
	public boolean contains(int v) {
		return v >= min && v <= max;
	}

	@Override
	public void dump(DumpWriter out) {
		out.dump(min);
		out.dump(max);
		
	}

	@Override
	public void load(DumpReader in) {
		min = in.loadInt();
		max = in.loadInt();
	}

	@Override
	public int hashCode() {
		return Objects.hash(max, min);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Range other = (Range) obj;
		return max == other.max && min == other.min;
	}
	
	
	
}

