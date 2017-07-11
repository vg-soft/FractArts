package de.adevit;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;


public class JsonTest {

	@Test
	public void jsonTest()  {
		Map<String, Object> j1 = new HashMap<>();
		Map<String, Object> j2 = new HashMap<>();
		j2.put("key11", "blabla");
		j2.put("key12", 123);
		j1.put("key1", j2);
		j1.put("key2",  "hallo world");
		j1.put("key3", 123.456);
		Object [] intArr = {1, 2, 3, 4, 5, "hallo", "nochmal", .0001, null, "ENDE"};
		j1.put("key4",  intArr);
		JSONObject jo = new JSONObject(j1);
		System.out.println(jo.toString(4));
	}
}
