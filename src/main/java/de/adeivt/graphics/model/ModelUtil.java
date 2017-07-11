package de.adeivt.graphics.model;

import java.awt.Color;
import java.util.List;

public class ModelUtil {
	public static String colorToString(Color c) {
		return c==null?"null":"{r:" + c.getRed() + ", g:" + c.getGreen() + ", b:" + c.getBlue() + "}";
	}
	
	public static String listToString(List<?> lst) {
		StringBuilder b = new StringBuilder();
		String del = "[";
		for(Object el: lst) {
			b.append(del).append(el);
			del = "\n,";
		}
		return b.append("\n]").toString();
	}
}
