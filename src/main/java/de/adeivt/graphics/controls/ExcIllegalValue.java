package de.adeivt.graphics.controls;

public class ExcIllegalValue extends Exception {
	public ExcIllegalValue() {
		super();
	}

	public ExcIllegalValue(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExcIllegalValue(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcIllegalValue(Throwable cause) {
		super(cause);
	}

	public ExcIllegalValue(String txt) {
		super(txt);
	}
}
