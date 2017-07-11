package de.adeivt.graphics;


/**
 * the color must be less that 1. 
 * if they are more or equal than 1, we set them to 0.999999999
 * @author Denis Volkov
 *
 */

public final class PhysPoint {
	public double MAX_COLOR_VALUE = 0.999999999999;
	
	public static enum MAGNITUDE_STRATEGY {FIRST_MAGNITUDE, LINE_FIRST_COLOR, LINE_LAST_COLOR, RANDOM};
	// all phyisical coordinates run from 0 (inclusive) to 1 (exclusive)
	public double x;
	public double y;
	public double red;
	public double green;
	public double blue;
	public double magFrom, magTo; // what part of the whole way from starting point we have to go to this point (default:0.5)
									// there are two, because, we could draw the whole line (e.g. from 0.5 to 0.51)
	public MAGNITUDE_STRATEGY strategy;
	private double checkColor(double c) {
		return Math.min(c, MAX_COLOR_VALUE);
	}
	public PhysPoint(double x, double y, double r, double g, double b, double mag1, double mag2, MAGNITUDE_STRATEGY strat) {
		this.x=x;this.y=y;
		this.red=checkColor(r);
		this.green=checkColor(g);
		this.blue=checkColor(b);
		this.magFrom = mag1; this.magTo = mag2; this.strategy = strat;
	}
	public PhysPoint(double x, double y, double r, double g, double b, double mag1) {
		this.x=x;this.y=y;
		this.red=checkColor(r);
		this.green=checkColor(g);
		this.blue=checkColor(b);
		this.magFrom = mag1; this.magTo = mag1; this.strategy = MAGNITUDE_STRATEGY.FIRST_MAGNITUDE;
	}
	public PhysPoint(double x, double y, double r, double g, double b, double mag1, double mag2) {
		this.x=x;this.y=y;
		this.red=checkColor(r);
		this.green=checkColor(g);
		this.blue=checkColor(b);
		this.magFrom = mag1; this.magTo = mag1; this.strategy = MAGNITUDE_STRATEGY.LINE_FIRST_COLOR;
	}
	public PhysPoint(double x, double y, double r, double g, double b) {
		this.x=x;this.y=y;
		this.red=checkColor(r);
		this.green=checkColor(g);
		this.blue=checkColor(b);
		this.magFrom = 0.5; this.strategy = MAGNITUDE_STRATEGY.FIRST_MAGNITUDE;
	}
	public PhysPoint(double x, double y) {
		this.x=x;this.y=y;
		this.green=0.;this.red=0.;this.blue=0.;
		this.magFrom = 0.5; this.strategy = MAGNITUDE_STRATEGY.FIRST_MAGNITUDE;
	}
	@Override
	public String toString() {
		return "<" + this.x+"," + this.y + ">,c:{" + this.red + "," + this.green + "," + this.blue + "}";
	}
}
