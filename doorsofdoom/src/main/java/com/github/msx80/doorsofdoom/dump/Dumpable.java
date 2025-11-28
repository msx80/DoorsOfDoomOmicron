package com.github.msx80.doorsofdoom.dump;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This is a simple mechanism for persisting game state. GSon and reflection doesn't work on TeaVM so i made this, it's a bit more crude but it works.
 * It's based on strings so it can be written easily
 */
public interface Dumpable {

	void dump(DumpWriter out);
	void load(DumpReader in);
	
	public static String dumpToString(Dumpable d) {
		StringWriter s = new StringWriter();
		PrintWriter pw = new PrintWriter(s);
		pw.flush();
		d.dump(new DumpWriter(pw::println));
		String out = s.getBuffer().toString();
		return out;
	}
	
	public static <T extends Dumpable> T loadFromString(String out, Supplier<T> newCls) 
	{
		Iterator<String> lines = Stream.of(out.split("\n")).iterator();
		Supplier<String> sup = () -> lines.hasNext() ? lines.next() : null;
		
		T newRun = new DumpReader(sup).loadDumpable(newCls);
		return newRun;
	}

	
}
