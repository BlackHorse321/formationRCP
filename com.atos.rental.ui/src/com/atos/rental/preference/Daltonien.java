package com.atos.rental.preference;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class Daltonien implements IColorProvider {

	public Daltonien() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		// TODO Auto-generated method stub
		return Display.getCurrent().getSystemColor(SWT.COLOR_RED
				);

	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

}
