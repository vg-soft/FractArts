package de.adeivt.graphics.controls;

import javax.swing.JTextField;

public class LisChkIntegerInterval  extends LisChkAbstractValue{
	 int from;
	 int to;
	public LisChkIntegerInterval(JTextField f, int from,  int to) {
		super(f);
		this.from = from;
		this.to = to;
	}


	@Override
	public boolean newValueIsValid(String newValue) {
		try {
		int v = Integer.parseInt(newValue);
		return v>=from && v<=to;
		}
		catch(Exception e) {
			// by any exception we return false:
			return false;
		}
	}
}
