package de.adeivt.graphics.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import de.vg_soft.StringUtil;

public class CtrlDoubleValueChooser  extends JPanel implements IArrangableByText, IValuableComponent {
	String label;
	double minValue;
	double maxValue;
	int afterDecimalPoint;
	JTextField data;
	JScrollBar myScrollBar;
	DecimalFormat myFormat;
	public CtrlDoubleValueChooser(String label, double minValue, double maxValue, double defValue, int afterDecimalPoint) {
		this.label = label;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.afterDecimalPoint = afterDecimalPoint;
		//-----------------------
	    GridBagLayout gridbag = new GridBagLayout();
	    this.setLayout(gridbag);
        GridBagConstraints c = new GridBagConstraints();
        // strategy above: label
        // below: Text and horiz scrollBar
        c.gridx = c.gridy = 0;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(new JLabel(label), c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth=1;
//        c.fill = GridBagConstraints.HORIZONTAL;
        data = new JTextField();
        myFormat = new DecimalFormat("############0." + StringUtil.repoduceString("0", afterDecimalPoint));
        data.setText(myFormat.format(defValue));
        data.setPreferredSize(new Dimension(20+10*afterDecimalPoint, data.getPreferredScrollableViewportSize().height));
        data.setAlignmentX(RIGHT_ALIGNMENT);
        FocusListener lisIntervalChecker = new LisChkDoubleInterval(data, 0., 1., myFormat);
        FocusListener lisArranger = new LisOnFocusLostArranger(this);
        data.addFocusListener(new LisSequencedFocusListener(lisIntervalChecker, lisArranger));
        this.add(data, c);
        
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
//        c.fill = GridBagConstraints.HORIZONTAL;
       myScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 10000);
       myScrollBar.setPreferredSize(new Dimension(200, myScrollBar.getPreferredSize().height));
        myScrollBar.addAdjustmentListener(new AdjustmentListener() {
         	public void adjustmentValueChanged(AdjustmentEvent e) {
         		// 0->min, 10000->max
         		double v = minValue + (maxValue-minValue)*myScrollBar.getValue()/10000;
         		data.setText(myFormat.format(v));
         	}
        });
		myScrollBar.setValue((int)(myScrollBar.getMaximum()/(maxValue-minValue)*(defValue-minValue)));

        this.setBorder(new LineBorder(Color.black, 2));
        this.add(myScrollBar, c);
	}
	public JScrollBar getScrollBar() {
		return myScrollBar;
	}
	
	
	@Override
	public void arrange(String txt) {
		try {
			double v = myFormat.parse(txt).doubleValue();
			int sbVal = (int)(myScrollBar.getMaximum()/(maxValue-minValue)*(v-minValue));
			myScrollBar.setValue(sbVal);
		}
		catch(Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		  return ControlUtils.getPreferredSizeWithCommonWidth(this, super.getPreferredSize());
	}
	@Override
	public Double getValue() {
		try {
			return myFormat.parse(data.getText()).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
			return this.minValue;
		}
	}
	@Override
	public void setValue(Object obj) throws ExcInconsistentType, ExcIllegalValue {
		if(! (obj instanceof Number)) {
			throw new ExcInconsistentType("only Number types are allowed as values for " + this.getClass().getName());
		}
		double val = ((Number)obj).doubleValue();
		if(val<this.minValue || val>this.maxValue) throw new ExcIllegalValue("Value " + val + " is not between " + minValue + " and " + maxValue);
		this.data.setText(myFormat.format(val));
		myScrollBar.setValue((int)(myScrollBar.getMaximum()/(maxValue-minValue)*(val-minValue)));
	}


}
