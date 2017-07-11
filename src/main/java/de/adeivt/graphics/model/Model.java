package de.adeivt.graphics.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Model {
  public int frameWidth;
  public int frameHeight;
  public int deltaFrameToCanvas;
  public Color canvasBgColor;
  public Point2d startPoint;
  public List<Point2dColorMagnitude> magnitudePoints;
  public List<Double> probabilities;
  public int stepCount;
  
  //****************************************************************************************************
  public Model(String str) {
	  initFromJSON(new JSONObject(str));
  }
  //****************************************************************************************************

  
  
  //****************************************************************************************************
  public Model(JSONObject jso) {
	  initFromJSON(jso);
  }
  //****************************************************************************************************

  
  
  //****************************************************************************************************
  public Model() {
	  this("{"
	  		+ "frameWidth:1000, frameHeight:1000, deltaFrameToCanvas:50, canvasBgColor:{r:255, g:255, b:255}, "
			+ " startPoint: {x:0, y:0},   "
			+ " magnitudePoints: [{x:0, y:0, c:{r:255, g:0, b:0}, mag:0.5},{x:1, y:0, c:{r:0, g:255, b:0}, mag:0.5},{x:0, y:1, c:{r:0, g:0, b:255}, mag:0.5}], "
			+ " probabilities: [0.333333, 0.333333, 0.333334], "
			+ " stepCount: 1000000 "
			+ "}"
	  		);
  }
  //****************************************************************************************************

  
  
  //****************************************************************************************************
  @Override
  public String toString() {
	  StringBuilder b = new StringBuilder();
	  b.append("{\n");
	  b.append("  frameWidth:" + frameWidth + "\n");
	  b.append(" ,frameHeight:" + frameHeight + "\n");
	  b.append(" ,deltaFrameToCanvas:" + deltaFrameToCanvas + "\n");
	  b.append(" ,canvasBgColor:" + ModelUtil.colorToString(canvasBgColor) + "\n");
	  b.append(" ,startPoint:" + startPoint + "\n");
	  b.append(" ,magnitudePoints:" + ModelUtil.listToString(magnitudePoints) + "\n");
	  b.append(" ,probabilities:" + ModelUtil.listToString(probabilities) + "\n");
	  b.append(" ,stepCount:" + stepCount + "\n");
	  b.append("}\n");
	  return b.toString();
  }
  //****************************************************************************************************

  
  
  //****************************************************************************************************
  public String toJSONString() {
	  return this.toString();
  }
  //****************************************************************************************************

  
  
  //****************************************************************************************************
  public void initFromJSON(JSONObject jso) {
	  this.frameWidth = (Integer)jso.get("frameWidth");
	  this.frameHeight = (Integer)jso.get("frameHeight");
	  this.deltaFrameToCanvas = (Integer)jso.get("deltaFrameToCanvas");
	  JSONObject col = (JSONObject)jso.get("canvasBgColor");
	  this.canvasBgColor = new Color((Integer)col.get("r"), (Integer)col.get("g"), (Integer)col.get("b"));
	  this.startPoint = Point2d.fromJSONString(((JSONObject)jso.get("startPoint")).toString());
	  JSONArray magPoints = (JSONArray)jso.get("magnitudePoints");
	  this.magnitudePoints = new ArrayList<>();
	  for(int i=0;i<magPoints.length();i++) {
		  Point2dColorMagnitude p = Point2dColorMagnitude.fromJSONString( ((JSONObject)magPoints.get(i)).toString() );
		  this.magnitudePoints.add(p);
	  }
	  this.probabilities = new ArrayList<>();
	  JSONArray probs = jso.getJSONArray("probabilities");
	  for(int i=0;i<probs.length();i++) {
		  this.probabilities.add((Double)(probs.get(i)));
	  }
	  this.stepCount = (Integer)jso.get("stepCount");
  }
  //****************************************************************************************************

  
}
