package com.atos.rental.preference;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

import com.atos.rental.ui.RentalUIConstants;
import com.atos.rental.ui.RentalUiActivator;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;

public class DefaultPalette implements IColorProvider, RentalUIConstants {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof Customer){
			
			return getColor(RentalUiActivator.getDefault().getPreferenceStore().getString(PREF_CUSTOMER_COLOR));
     		
		}
		if (element instanceof Rental){
			return getColor(RentalUiActivator.getDefault().getPreferenceStore().getString(PREF_RENTAL_COLOR));
     		
		}
		if (element instanceof RentalObject){
			
		
		return getColor(RentalUiActivator.getDefault().getPreferenceStore().getString(PREF_OBJECT_COLOR));
	
		}

		return null;
	}
	@Override
	public Color getBackground(Object element) {

		return getColor(StringConverter.asString(new RGB(255,255,255)));
	}
	
	
	private Color getColor(String rgbKey){
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		
		Color col = colorRegistry.get(rgbKey);
		if (col ==null){
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = colorRegistry.get(rgbKey);
		}
		
		return col;
	}
}
