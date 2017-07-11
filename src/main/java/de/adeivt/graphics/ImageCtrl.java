package de.adeivt.graphics;

import java.awt.Color;
import java.util.List;

public class ImageCtrl {
	public PreparingCanvas myCanv;
	public List<PhysPoint> pointList;
	public List<Double> probabilities;
	public PhysPoint firstPoint;
	public int steps;
	
	
	private void drawPhysLine(double x1, double y1, double x2, double y2, double r, double g, double b) {
		int w = this.myCanv.getWidth();
		int h = this.myCanv.getHeight();
		this.myCanv.setLine((int)(x1*w),
							(int)((1.-y1)*h),
							(int)(x2*w),
							(int)((1.-y2)*h),
							new Color(
									(int)(r*256), 
									(int)(g*256),
									(int)(b*256)));
	}

	
	
	private PhysPoint findTargetByProbability() {
		double r = Math.random();
		int n = Math.min(this.pointList.size(), this.probabilities.size());
		for(int i=0;i<n;i++) {
			r-=this.probabilities.get(i);
			if(r<0) return this.pointList.get(i);
		}
		return this.pointList.get(this.pointList.size()-1);
	}
	
	public void generate() {
		this.myCanv.reset();
		PhysPoint curr = this.firstPoint;
		for(int i=0;i<this.steps;i++) {
			PhysPoint target = findTargetByProbability();
			// the first magnitude point must be calculated:
			PhysPoint point1  = new PhysPoint(curr.x+target.magFrom*(target.x-curr.x),
												curr.y+target.magFrom*(target.y-curr.y),
												curr.red + target.magFrom*(target.red-curr.red),
												curr.green + target.magFrom*(target.green-curr.green),
												curr.blue + target.magFrom*(target.blue-curr.blue));
			PhysPoint point2 = null;
			if(target.strategy!=PhysPoint.MAGNITUDE_STRATEGY.FIRST_MAGNITUDE) {
				// we  must calculate the second point
				 point2  = new PhysPoint(curr.x+target.magTo*(target.x-curr.x),
						curr.y+target.magTo*(target.y-curr.y),
						curr.red + target.magTo*(target.red-curr.red),
						curr.green + target.magTo*(target.green-curr.green),
						curr.blue + target.magTo*(target.blue-curr.blue));
			}
			switch(target.strategy) {
			case FIRST_MAGNITUDE:
				drawPhysLine(point1.x, point1.y, point1.x, point1.y, point1.red, point1.green, point1.blue);
				curr = point1;
				break;
			case LINE_FIRST_COLOR:
				drawPhysLine(point1.x, point1.y, point2.x, point2.y, point1.red, point1.green, point1.blue);
				curr = point1;
				break;
			case LINE_LAST_COLOR:
				drawPhysLine(point1.x, point1.y, point2.x, point2.y, point2.red, point2.green, point2.blue);
				curr = point1;
				break;
			case RANDOM:
				double r = Math.random();
				curr = new PhysPoint(point1.x+r*(point2.x-point1.x),
						point1.y+r*(point2.y-point1.y),
						point1.red+r*(point2.red-point1.red),
						point1.green+r*(point2.green-point1.green),
						point1.blue+r*(point2.blue-point1.blue)
						);
				drawPhysLine(curr.x, curr.y, curr.y, curr.y, curr.red, curr.green, curr.blue);
				break;
			}
		}
	} // end of generate()
	
	
	
}
