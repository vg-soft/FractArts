package de.adeivt.graphics.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CtrlCompactColorChooser  extends JPanel implements IValuableComponent{
	
	
	//====================================================================
	public static class MyActionListener implements ActionListener {
		CtrlCompactColorChooser ccc;
		public MyActionListener(CtrlCompactColorChooser ccc) {
			this.ccc = ccc;
			this.arrangeComponents();
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// toggle visibility
			ccc.cc.setVisible(!ccc.cc.isVisible());
			arrangeComponents();
		}
		public void arrangeComponents() {
			if(ccc.cc.isVisible()) {
			ccc.b.setText("set the choosen color");
//			ccc.setPreferredSize(ControlUtils.getPreferredSizeFromVisibleComponentsVertical(ccc, 0));
			}
			else {
				ccc.b.setBackground(ccc.cc.getColor());
				ccc.b.setText("choose another color");
//				ccc.setPreferredSize(ControlUtils.getPreferredSizeFromVisibleComponentsVertical(ccc, 10));
			}
//			ControlUtils.setPreferedHeight(ccc.getParent(), ccc.getPreferredSize());
//			ccc.f.pack();
		}
	}
	//========================================================================================
	JColorChooser cc;
	JButton b;
	public CtrlCompactColorChooser(String prompt) {
		this.setLayout(new FlowLayout());
		this.add(new JLabel(prompt));
		b = new JButton("choose color");
		cc = new JColorChooser();
		this.add(b);
		this.add(cc);
		this.setBorder(new LineBorder(Color.gray, 2));
		cc.setVisible(false);
		cc.setColor(Color.white);
		b.setBackground(cc.getColor());
		b.addActionListener(new MyActionListener(this));
	}
	
	@Override
	public Dimension getPreferredSize() {
		  return ControlUtils.getPreferredSizeWithCommonWidth(this, super.getPreferredSize());
	}
	

	@Override
	public Color getValue() {
		return this.cc.getColor();
	}

	@Override
	public void setValue(Object obj) throws ExcInconsistentType, ExcIllegalValue {
		if(! (obj instanceof Color)) {
			throw new ExcInconsistentType("Only " + Color.class.getName() + " is allowed as value for " + this.getClass().getName());
		}
		this.cc.setColor((Color)obj);
		((MyActionListener)this.b.getActionListeners()[0]).arrangeComponents();
		
	}
}
