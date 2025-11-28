package com.github.msx80.doorsofdoom.dump;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DumpReader {
	Supplier<String> in;
	String lh = null;
		
	public DumpReader(Supplier<String> in) 
	{
		super();
		this.in = in;
	}
	
	public int loadInt()
	{
		return Integer.parseInt(next());
	}
	
	public float loadFloat()
	{
		return Float.parseFloat(next());
	}
	
	public String loadString()
	{
		if(nextIsNull()) return null;
		return next();
	}
	
	public <T extends Dumpable> T loadDumpable(Supplier<T> newInstance)
	{
		if(nextIsNull()) return null;

		T c = newInstance.get();
		c.load(this);
		return c;
	}
	
	public boolean nextIsNull()
	{
		String x = next();
		boolean isnull = x.equals(DumpWriter.NULLOBJ);
		if (!isnull) {
			lh = x; // don't consume if it's not null
		}  
		return isnull;
	}
	
	private String next() {
		if(lh != null)
		{
			String x = lh;
			lh = null;
			return x;
		}
		return in.get();
	}
	public <K, V> Map<K,V> loadMap( Class<K> keyClass, Class<V> valueClass) 
	{
		if(nextIsNull()) return null;
		
		int size = loadInt();
		Map<K,V> map = new HashMap<K, V>(size);
		
		for (int i = 0; i < size; i++) 
		{
			K valK = parseVal(keyClass);
			V valV = parseVal(valueClass);
			map.put(valK, valV);
		}
		return map;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <E> E parseVal(Class<E> cls) {
		if(nextIsNull()) return null;
		
		if(cls.isEnum())
		{
			return (E) getInstance( next(), (Class<Enum>) cls);
		}
		if(cls == Integer.class)
		{
			return (E)(Integer)loadInt();
		}
		if(cls == String.class)
		{
			return (E) loadString();
		}
		throw new RuntimeException("Unexpected class "+cls);
	}
	public static <T extends Enum<T>> T getInstance(final String value, final Class<T> enumClass) {
	     return Enum.valueOf(enumClass, value);
	 }
	public <T extends Enum<T>> T loadEnum(Class<T> class1) {
		if(nextIsNull()) return null;
		return getInstance(next(), class1);
	}
	public boolean loadBoolean() {
		return Boolean.parseBoolean(next());
	}
}
