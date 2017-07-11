package de.adeivt.graphics;

import java.awt.Dimension;

/**
 * die Klasse transformiert physische Koordinaten in die anzeige - Koordinaten
 * @author Denis Volkov
 *
 */
public class Phys2LayoutTransformer {
	double x1;
	double x2;
	double y1;
	double y2;
	int width;
	int height;
	double kx, ky;
	
	
	public Phys2LayoutTransformer(double x1, double x2, double y1, double y2, int width, int height) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.width = width;
		this.height = height;
		kx = width/(x2-x1);
		ky = height/(y2-y1);
	}
	
	public  Dimension transform(double x, double y) {
		int w = width-(int)((x-x1)*kx);
		int h = height- (int)((y-y1)*ky);
		return new Dimension(w, h);
	}
}
