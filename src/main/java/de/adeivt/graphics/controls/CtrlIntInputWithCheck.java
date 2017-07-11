package de.adeivt.graphics.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CtrlIntInputWithCheck  extends JPanel implements IValuableComponent{
	JTextField t;
	int minV;
	int maxV;
	public CtrlIntInputWithCheck(String prompt, int defV, int minV, int maxV) {
		this.minV = minV;
		this.maxV = maxV;
		this.setLayout(new GridLayout(2, 1));
		this.add(new JLabel(prompt));
		 t = new JTextField();
//		t.setPreferredSize(new  Dimension(100, 30));
		t.setText("" + defV);
		t.setBorder(new LineBorder(Color.gray, 2));
		t.addFocusListener(new LisChkIntegerInterval(t, minV, maxV));
		this.add(t);
	}
	
	@Override
	public void setValue(Object obj)  throws ExcInconsistentType, ExcIllegalValue {
		if(!(obj instanceof Number)) {
			throw new ExcInconsistentType("Only number type is allowed as value for " + this.getClass().getName());
		}
		int val = ((Number)obj).intValue();
		if(val<minV) throw new ExcIllegalValue(val + " must be greater or equal than " + minV);
		if(val>maxV) throw new ExcIllegalValue(val + " must be less or equal than " + maxV);
		this.t.setText("" + val);
	}
	
	@Override
	public Integer getValue() {
		return Integer.parseInt(t.getText());
	}
	
	
	@Override
	public Dimension getPreferredSize() {
		  return ControlUtils.getPreferredSizeWithCommonWidth(this, super.getPreferredSize());
	}


}
