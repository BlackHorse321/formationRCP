package com.atos.rental.ui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

public class AbstractPreferenceRentalInitializer extends
		AbstractPreferenceInitializer implements RentalUIConstants {

	public AbstractPreferenceRentalInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RentalUiActivator.getDefault().getPreferenceStore();
		store.setDefault(PREF_CUSTOMER_COLOR, StringConverter.asString(new RGB(255,0,0)));
		store.setDefault(PREF_RENTAL_COLOR, StringConverter.asString(new RGB(0,255,0)));
		store.setDefault(PREF_OBJECT_COLOR, StringConverter.asString(new RGB(0,0,255)));
	}

}
