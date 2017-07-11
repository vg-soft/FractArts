package de.adeivt.graphics;

import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import de.adeivt.graphics.model.Model;
import de.adeivt.graphics.model.Point2d;
import de.adeivt.graphics.model.Point2dColorMagnitude;

public class ImageGenerator {
	
	private ShowingCanvas sCanvas;
	private Model model;
	
	public ImageGenerator(Model m) {
		this.model = m;
		
	}
	public ShowingCanvas getCanvas() {
		return this.sCanvas;
	}
	
	private PhysPoint point2dToPhysPoint(Point2d p) {
		return new PhysPoint(p.x, p.y, 0.5, 0.5, 0.5, 0.5);
	}
	private PhysPoint point2dColorMagnitudeToPhysPoint( Point2dColorMagnitude p) {
		return new PhysPoint(p.x, p.y, p.c.getRed()/255. , p.c.getGreen()/255. , p.c.getBlue()/255., p.mag); 
	}
	
	public void generate() {
		try {
		ImageCtrl ctrl = new ImageCtrl();
		ctrl.firstPoint = point2dToPhysPoint(model.startPoint);
		ctrl.myCanv = new PreparingCanvas(model.frameWidth-model.deltaFrameToCanvas
				,model.frameHeight-model.deltaFrameToCanvas
				,model.canvasBgColor);
		ctrl.pointList = new ArrayList<PhysPoint>();
		for(Point2dColorMagnitude src: model.magnitudePoints) {
			ctrl.pointList.add(this.point2dColorMagnitudeToPhysPoint(src));
		}
		ctrl.probabilities = model.probabilities;
		ctrl.steps = model.stepCount;
	    ctrl.generate();
	    
	     sCanvas = new ShowingCanvas(ctrl.myCanv);
	    
		JFrame mainFrame = new JFrame("Fractale examaples");
	      mainFrame.setSize(model.frameWidth,model.frameHeight);
	      //mainFrame.setLayout(new GridLayout(3, 1));
	      mainFrame.setLayout(new FlowLayout());
	      mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	      mainFrame.add(sCanvas);
	      mainFrame.setVisible(true);
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
	}	
}
