package de.adeivt.graphics;

import java.util.Arrays;
import java.util.List;

public class TestTrunc {
	public static void main(String [] arg) {
		List<Double> dl = Arrays.asList(0., 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0);
		
		for(Double d: dl) {
			System.out.println(d + " -> " + ((int)(d.doubleValue())));
		}
	}
}
