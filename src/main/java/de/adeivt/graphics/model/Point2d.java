package de.adeivt.graphics.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class Point2d  implements IListable{
	public double x;
	public double y;
	public Point2d() {}
	public Point2d(double x, double y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "{x:" + x + ", y:" + y + "}";
	}
	public String toJSONString() {
		return toString();
	}
	public static Point2d fromJSONString(String jso) {
		JSONObject j = new JSONObject(jso);
		return new Point2d(((Number)j.get("x")).doubleValue(), ((Number)j.get("y")).doubleValue());
	}
	
	public List<Object> toList() {
		ArrayList<Object> ret = new ArrayList<>();
		ret.add(this.x);
		ret.add(this.y);
		return ret;
	}
}
