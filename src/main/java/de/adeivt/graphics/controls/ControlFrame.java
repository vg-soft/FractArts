package de.adeivt.graphics.controls;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import de.adeivt.graphics.ShowingCanvas;
import de.adeivt.graphics.model.Model;
import de.adeivt.graphics.model.Point2d;
import de.adeivt.graphics.model.Point2dColorMagnitude;

public class ControlFrame extends JFrame {
	
	public static final String STANDARD_MODEL_STRING = "{}";
	
	public CtrlButtonLoad butLoad;
	public CtrlButtonSave butSave;
	public CtrlIntInputWithCheck frameWidth;
	public CtrlIntInputWithCheck frameHeight;
	public CtrlIntInputWithCheck deltaFrameToCanvas;
	public CtrlCompactColorChooser canvasBgColor;
	public CtrlXDimPoint startPoint2D;
	public CtrlArray pointsWithColorMagnitude;
	public CtrlArray probabilities;
	public CtrlIntInputWithCheck stepCount;
	public JButton butGen;
	public JButton butExp;

	private ShowingCanvas showingCanvas;
	
	public ShowingCanvas getShowingCanvas() {
		return showingCanvas;
	}
	public BufferedImage getImage() {
		return this.showingCanvas.getBufImg();
	}




	private JScrollPane scrollPane;
	
	//********************************************************************************************
	@SuppressWarnings("unchecked")
	public Model convertToModel() {
		Model m = new Model();
		m.frameWidth = this.frameWidth.getValue();
		m.frameHeight = this.frameHeight.getValue();
		m.deltaFrameToCanvas = this.deltaFrameToCanvas.getValue();
		m.canvasBgColor = this.canvasBgColor.getValue();
		m.startPoint = new Point2d(this.startPoint2D.getValue(0), this.startPoint2D.getValue(1));
		m.magnitudePoints = new ArrayList<Point2dColorMagnitude>();
		List<Object> pValues = this.pointsWithColorMagnitude.getValue();
		for(Object obj: pValues) {
			List<Double>data = (List<Double>)obj;
			m.magnitudePoints.add(new Point2dColorMagnitude(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5)));
		}
		m.probabilities =  new ArrayList<>();
		for(Object obj: this.probabilities.getValue()) {
			m.probabilities.add((Double)obj);
		}
		m.stepCount = this.stepCount.getValue();
		return m;
	} // end of convertToModel()
	//********************************************************************************************

	
	
	
	//********************************************************************************************
	public void initFromString(String mStr) throws ExcInconsistentType, ExcIllegalValue {
		initFromModel(new Model(mStr));
	}
	//********************************************************************************************

	
	
	//********************************************************************************************
	public void initFromModel(Model m) throws ExcInconsistentType, ExcIllegalValue {
		Box pan = new Box(BoxLayout.Y_AXIS);

		this.butLoad = new CtrlButtonLoad("LOAD CONFIGURATION", this);
		pan.add(this.butLoad);
		
		this.butSave = new CtrlButtonSave("SAVE CONFIGURATION", this);
		pan.add(this.butSave);
		

		this.frameWidth = new CtrlIntInputWithCheck("Frame Width", 1000, 10, 10000);
		pan.add(this.frameWidth);
		this.frameWidth.setValue(m.frameWidth);
		
		this.frameHeight = new CtrlIntInputWithCheck("Frame Height (10-10000)", 1000, 10, 10000);
		pan.add(this.frameHeight);
		this.frameHeight.setValue(m.frameHeight);
		
		this.deltaFrameToCanvas = new CtrlIntInputWithCheck("Delta Frame-Canvas (0-100)", 20, 0, 100);
		pan.add(this.deltaFrameToCanvas);
		this.deltaFrameToCanvas.setValue(m.deltaFrameToCanvas);
		
		this.canvasBgColor = new CtrlCompactColorChooser("Canvas Background Color");
		pan.add(this.canvasBgColor);
		this.canvasBgColor.setValue(m.canvasBgColor);
		
		this.startPoint2D = Fact2DPointFactory.instance.createElement("Start Point");
		pan.add(this.startPoint2D);
		this.startPoint2D.setValue(0, m.startPoint.x);
		this.startPoint2D.setValue(1, m.startPoint.y);
		
		// all points with color
		this.pointsWithColorMagnitude = new CtrlArray(this, "Magnitude Points", "Point ${CNT}", Fact2DPointWithColorMagnitudeFactory.instance, m.magnitudePoints.size());
		this.pointsWithColorMagnitude.setValue(m.magnitudePoints);
		pan.add(this.pointsWithColorMagnitude);
		
		// probabilities - array
		this.probabilities = new CtrlArray(this, "Magnitude Point Probabilities", "Probability ${CNT}", FactDoubleValueChooserFactory.instance, m.probabilities.size());
		pan.add(this.probabilities);
		this.probabilities.setValue(m.probabilities);
		
		// Step count
		this.stepCount = new CtrlIntInputWithCheck("Step Count (1-2000000000)", 1000, 1, 2000000000);
		pan.add(this.stepCount);
		this.stepCount.setValue(m.stepCount);
		
		// Button generate:
		this.butGen = new CtrlButtonGenerate("GENERATE", this);
		pan.add(this.butGen);
		
		// Button export:
		this.butExp = new CtrlButtonExport("EXPORT", this);

		pan.add(this.butExp);

		
		if(this.scrollPane!=null) this.remove(this.scrollPane);
		scrollPane = new JScrollPane(pan, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
		this.setSize(ControlUtils.getCommonPreferedWidth()+50, 1000);
		
		SwingUtilities.updateComponentTreeUI(this);
		this.invalidate();
		this.validate();
		this.repaint();
	}
	//********************************************************************************************


	
	
	//********************************************************************************************
	public ControlFrame() throws ExcInconsistentType, ExcIllegalValue {
	  super("Fractale examaples");
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  this.initFromModel(new Model());
	}
	//********************************************************************************************




	//********************************************************************************************
	public void setCanvas(ShowingCanvas canvas) {
		this.showingCanvas = canvas;
	}
	//********************************************************************************************




	//********************************************************************************************
	public ShowingCanvas getCanvas() {
		return this.showingCanvas;
	}
	//********************************************************************************************


}
