package de.adeivt.graphics.controls;

import java.text.DecimalFormat;

import javax.swing.JTextField;

public class LisChkDoubleInterval  extends LisChkAbstractValue{
	 double from;
	 double to;
	 DecimalFormat fmt;
	public LisChkDoubleInterval(JTextField f, double from,  double to, DecimalFormat fmt) {
		super(f);
		this.from = from;
		this.to = to;
		this.fmt = fmt;
	}


	@Override
	public boolean newValueIsValid(String newValue) {
		try {
			double v = fmt.parse(newValue).doubleValue();
			return v>=from && v<=to;
		}
		catch(Exception e) {
			// by any exception we return false:
			return false;
		}
	}
}
