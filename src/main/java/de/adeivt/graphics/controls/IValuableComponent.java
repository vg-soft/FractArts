package de.adeivt.graphics.controls;

public interface IValuableComponent {
	public Object getValue();
	public void setValue(Object obj) throws ExcInconsistentType, ExcIllegalValue;
}
