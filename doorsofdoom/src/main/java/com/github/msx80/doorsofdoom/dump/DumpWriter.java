package com.github.msx80.doorsofdoom.dump;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class DumpWriter {
	
	static final String NULLOBJ = "--NULL--";
	Consumer<String> out;
	
	public DumpWriter(Consumer<String> out) {
		super();
		this.out = out;
	}
	
	public void dump(Object o)
	{
		if(o == null)
		{
			out.accept(NULLOBJ);
		}
		else if(o instanceof Dumpable)
		{
			((Dumpable) o).dump(this);
		}
		else if(o instanceof Map)
		{
			dumpMap((Map<?, ?>) o, this);
		}
		else {
			out.accept(o.toString());
		}
	}
	
	private static void dumpMap(Map<?,?> map, DumpWriter out)
	{
		out.dump(map.size());
		for (Entry<?, ?> e : map.entrySet()) {
			out.dump(e.getKey());
			out.dump(e.getValue());
		}
	}
	
}
