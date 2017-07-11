package de.adeivt.graphics.controls;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
/**
 * the class takes a sequence of FocusListener - Objects and
 * dispatches the event IN THE ORDER of the objects. 
 * A normal listener mechanism does not garantee, that
 * the listener get their events in the correct order
 * @author Denis Volkov
 *
 */
public class LisSequencedFocusListener implements FocusListener {
	List<FocusListener> lst;
	
	public LisSequencedFocusListener(FocusListener...lisArr) {
		lst = new ArrayList<>();
		for(FocusListener lis: lisArr) {
			lst.add(lis);
		}
	}
	@Override
	public void focusGained(FocusEvent e) {
		for(FocusListener lis: lst) {
			lis.focusGained(e);
		}
	}
	@Override
	public void focusLost(FocusEvent e) {
		for(FocusListener lis: lst) {
			lis.focusLost(e);
		}
	}
}
