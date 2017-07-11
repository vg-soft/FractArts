package de.adeivt.graphics.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class Point2dColorMagnitude extends Point2dColor   implements IListable{
	public static final double DEFAULT_MAGNITUDE = 0.5;
	
	public double mag;
	
	public Point2dColorMagnitude() {
		super();
		this.mag = DEFAULT_MAGNITUDE;
	}
	public Point2dColorMagnitude(double x, double y) {
		super(x, y);
		this.mag = DEFAULT_MAGNITUDE;
	}

	public Point2dColorMagnitude(double x, double y, Color c) {
		super(x, y, c);
		this.mag = DEFAULT_MAGNITUDE;
	}

	public Point2dColorMagnitude(double x, double y, double red, double green, double blue) {
		super(x, y, red, green, blue);
		this.mag = DEFAULT_MAGNITUDE;
	}
	public Point2dColorMagnitude(Color c) {
		super(c);
		this.mag = DEFAULT_MAGNITUDE;
	}
	public Point2dColorMagnitude(Color c, double mag) {
		super(c);
		this.mag = mag;
	}
	public Point2dColorMagnitude(double mag) {
		super();
		this.mag = mag;
	}
	public Point2dColorMagnitude(double x, double y, double mag) {
		super(x, y);
		this.mag = mag;
	}
	public Point2dColorMagnitude(double x, double y, Color c, double mag) {
		super(x, y, c);
		this.mag = mag;
	}

	public Point2dColorMagnitude(double x, double y, double red, double green, double blue, double mag) {
		super(x, y, red, green, blue);
		this.mag = mag;
	}
	@Override
	public String toString() {
		return "{x:" + x + ", y:" + y + ", c:" + ModelUtil.colorToString(c) + ", mag:" + this.mag + "}";
	}
	public String toJSONString() {
		return toString();
	}
	
	public static Point2dColorMagnitude fromJSONString(String str) {
		JSONObject jso = new JSONObject(str);
		JSONObject col = (JSONObject)jso.get("c");
		return new Point2dColorMagnitude(((Number)jso.get("x")).doubleValue(), 
				((Number)jso.get("y")).doubleValue(), 
				new Color((Integer)col.get("r"), (Integer)col.get("g"), (Integer)col.get("b")), (Double)jso.get("mag"));
	}
	
	public List<Object> toList() {
		ArrayList<Object> ret = new ArrayList<>();
		ret.add(this.x);
		ret.add(this.y);
		ret.add(this.c.getRed()/255.);
		ret.add(this.c.getGreen()/255.);
		ret.add(this.c.getBlue()/255.);
		ret.add(this.mag);
		return ret;
	}

	
	
}
