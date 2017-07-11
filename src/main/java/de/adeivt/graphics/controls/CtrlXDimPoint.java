package de.adeivt.graphics.controls;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.adeivt.graphics.model.IListable;

/* It is an 2d - dimensional point with color. The dimensions are the Parameter (String Array) */
public class CtrlXDimPoint extends JPanel implements IValuableComponent{
	public CtrlXDimPoint(String name, String...dimLabels) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = c.gridy = 0;
		this.add(new JLabel(name), c);
		for(String l: dimLabels) {
			c.gridy++;
			CtrlDoubleValueChooser sb = new CtrlDoubleValueChooser(l, 0., 1., 0.5, 4);
			this.add(sb, c);
		}
	}
	public double getValue(int n) {
		// label is skipped
		return ((CtrlDoubleValueChooser)this.getComponent(n+1)).getValue();
	}
	public void setValue(int n, double val) throws ExcIllegalValue, ExcInconsistentType{
		((CtrlDoubleValueChooser)this.getComponent(n+1)).setValue(val);
	}

	
	public static CtrlXDimPoint gen2DPointWithColor(String name) {
		return new CtrlXDimPoint(name, "x", "y", "red", "green", "blue");
	}
	
	public static CtrlXDimPoint gen2DPointWithColorMagnitude(String name) {
		return new CtrlXDimPoint(name, "x", "y", "red", "green", "blue", "magnitude");
	}
	
	public static CtrlXDimPoint gen2DPoint(String name) {
		return new CtrlXDimPoint(name, "x", "y");
	}
	
	@Override
	public Dimension getPreferredSize() {
		  return ControlUtils.getPreferredSizeWithCommonWidth(this, super.getPreferredSize());
	}
	
	@Override
	public List<Double> getValue() {
		List<Double> ret = new ArrayList<>();
		for(int i=0;i<this.getComponentCount();i++) {
			// we begin with 1, because we skip the label
			if( (this.getComponents()[i]) instanceof CtrlDoubleValueChooser) {
				ret.add( ((CtrlDoubleValueChooser)this.getComponents()[i]).getValue() );
			}
		}
		return ret;
	}
	@Override
	public void setValue(Object obj) throws ExcInconsistentType, ExcIllegalValue {
		List<Object> lst = null;
		
		if(obj instanceof IListable) {
			lst = ((IListable)obj).toList();
		}
		else {
			if( !(obj instanceof List) && !(obj.getClass().isArray()) ) {
				throw new ExcInconsistentType("value can be only a list or an array for " + this.getClass().getName());
			}
		}
		
		List<Object> values = new ArrayList<>();
		if(lst != null) values.addAll(lst);
		else if(obj instanceof List) values.addAll((List<?>)obj);
		else {
			Object[] arr = (Object [])obj;
			for(Object el: arr) values.add(el);
		}
		int n = Math.min(values.size(), this.getComponentCount()-1);
		for(int i=0;i<n;i++) {
			// we begin with 1, because we skip the label
			 ((CtrlDoubleValueChooser)this.getComponents()[i+1]).setValue(values.get(i));
		}
	}

}
