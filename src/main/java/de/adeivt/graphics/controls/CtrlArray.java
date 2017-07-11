package de.adeivt.graphics.controls;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CtrlArray extends JPanel implements IArrangableByText, IValuableComponent {

	
	public static final String PLACEHOLDER = "${CNT}";
	String name;
	String elementNameTemplate;
	IFactoryOfComponents factory;
	int cnt;
	Component revalidatable;
	List<JComponent> compList;
	JComponent elCont;
	public CtrlArray(Component revalidatable, String name, String elementNameTemplate, IFactoryOfComponents factory, int startCnt) {
		this.name = name;
		this.elementNameTemplate = elementNameTemplate;
		this.cnt = startCnt;
		this.factory = factory;
		this.revalidatable = revalidatable;
		//--------------------------
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = c.gridy = 0;
		CtrlIntInputWithCheck inp = new CtrlIntInputWithCheck(name, 1, 0, 20);
		inp.t.addFocusListener(new LisOnFocusLostArranger(this));
		inp.t.setText("" + this.cnt);
		this.add(inp,  c);
		elCont = createElementContainer();
		c.gridy++;
		this.add(elCont, c);
		arrangeArray();
	}
	private JComponent createElementContainer() {
		JPanel ret = new JPanel();
		ret.setLayout(new GridBagLayout());
		return ret;
	}
	/**
	 * this method changes the number of elements in the array
	 */
	 void arrangeArray() {
		 int currCnt = this.elCont.getComponentCount();
		 if(this.cnt<currCnt) {
			 // we remove the last ones:
			 for(int i=currCnt-1;i>=this.cnt;i--) {
				 this.elCont.remove(i);
			 }
		 }
		 else if(this.cnt>currCnt) {
			 // new element:
			 GridBagConstraints c = new GridBagConstraints();
			 c.gridx = 0;
			 for(int i=currCnt;i<this.cnt;i++) {
				 JComponent comp = this.factory.createElement(this.elementNameTemplate.replace(PLACEHOLDER, ""+i));
				 c.gridy = i;
				 this.elCont.add(comp, c);
			 }
		 }
		 this.revalidatable.revalidate();

	} // end of arrangeArray()
	@Override
	public void arrange(String txt) {
		System.out.println("ARRANGING auf " + txt);
		this.cnt = Integer.parseInt(txt);
		this.arrangeArray();
	}
	
	@Override
	public Dimension getPreferredSize() {
		  return ControlUtils.getPreferredSizeWithCommonWidth(this, super.getPreferredSize());
	}

	@Override
	public List<Object> getValue() {
		List<Object> ret = new ArrayList<>();
		JPanel myPanel = (JPanel)this.getComponent(1);
		for(int i=0;i<myPanel.getComponentCount();i++) {
			// we begin with 1, because we skip the label
			  ret.add( ((IValuableComponent)myPanel.getComponents()[i]).getValue() );
		}
		return ret;
	}
	@Override
	public void setValue(Object obj) throws ExcInconsistentType, ExcIllegalValue {
		if( !(obj instanceof List) && !(obj.getClass().isArray()) ) {
			throw new ExcInconsistentType("value can be only a list or an array for " + this.getClass().getName());
		}
		List<Object> values = new ArrayList<>();
		if(obj instanceof List) values.addAll((List<?>)obj);
		else {
			Object[] arr = (Object [])obj;
			for(Object el: arr) values.add(el);
		}
		JPanel myPanel = (JPanel)this.getComponent(1);
		int n = Math.min(values.size(), myPanel.getComponentCount());
		for(int i=0;i<n;i++) {
			// we begin with 1, because we skip the label
			 ((IValuableComponent)myPanel.getComponents()[i]).setValue(values.get(i));
		}
	}


}
