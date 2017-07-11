package de.adeivt.graphics.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class Point2dColor extends Point2d   implements IListable{
	public static final Color DEFAULT_COLOR = Color.black;
	
  public Color c;

public Point2dColor() {
	super();
	this.c = DEFAULT_COLOR;
}
public Point2dColor(Color c) {
	super();
	this.c = c;
}
public Point2dColor(double x, double y) {
	super(x, y);
	this.c = DEFAULT_COLOR;
}
public Point2dColor(double x, double y, Color c) {
	super(x, y);
	this.c = c;
}
public  Point2dColor(double red, double green, double blue)  {
	super();
	int r = (int)(red*256);
	if(r>255)r=255;
	if(r<0)r=0;
	int g = (int)(green*256);
	if(g>255)g=255;
	if(g<0)g=0;
	int b = (int)(blue*256);
	if(b>255)b=255;
	if(b<0)b=0;
	this.c = new Color(r, g, b);
}
public  Point2dColor(double x, double y, double red, double green, double blue)  {
	super(x, y);
	int r = (int)(red*256);
	if(r>255)r=255;
	if(r<0)r=0;
	int g = (int)(green*256);
	if(g>255)g=255;
	if(g<0)g=0;
	int b = (int)(blue*256);
	if(b>255)b=255;
	if(b<0)b=0;
	this.c = new Color(r, g, b);
}
@Override
public String toString() {
	return "{x:" + x + ", y:" + y + ", c:" + ModelUtil.colorToString(c) + "}";
}
public String toJSONString() {
	return toString();
}
public static Point2dColor fromJSONString(String str) {
	JSONObject jso = new JSONObject(str);
	JSONObject col = (JSONObject)jso.get("c");
	return new Point2dColor((Double)jso.get("x"), (Double)jso.get("y"), new Color((Integer)col.get("r"), (Integer)col.get("g"), (Integer)col.get("b")));
}

public List<Object> toList() {
	ArrayList<Object> ret = new ArrayList<>();
	ret.add(this.x);
	ret.add(this.y);
	ret.add(this.c.getRed()/255.);
	ret.add(this.c.getGreen()/255.);
	ret.add(this.c.getBlue()/255.);
	return ret;
}

}
