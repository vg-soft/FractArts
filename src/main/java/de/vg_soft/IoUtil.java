package de.vg_soft;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IoUtil {
	public static String loadFromFile(File f) throws IOException {
		StringBuilder b = new StringBuilder();
		try(BufferedReader r = new BufferedReader(new FileReader(f))) {
			String str;
			while( (str=r.readLine()) != null) {
				b.append(str);
			}
		}
		return b.toString();
	}
	
	public static void saveFile(File f, String data) throws IOException {
		try(BufferedWriter w = new BufferedWriter(new FileWriter(f))) {
			w.write(data);
		}
	}
}
