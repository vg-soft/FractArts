package de.vg_soft;

public class StringUtil {
	public static String repoduceString(String orig, int count) {
		StringBuilder b = new StringBuilder();
		for(int i=0;i<count;i++) b.append(orig);
		return b.toString();
	}
}
