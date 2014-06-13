package com.atos.rental.preference;

import java.util.Map;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.atos.rental.ui.RentalUIConstants;
import com.atos.rental.ui.RentalUiActivator;

public class RentalColorChoice extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage, RentalUIConstants {

	public RentalColorChoice() {
		super(GRID);
		setPreferenceStore(RentalUiActivator.getDefault().getPreferenceStore());
		setDescription("Rental Color Choice");
	}

	

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}


	@Override
	protected void createFieldEditors(){
		Map<String, Palette> palettes = RentalUiActivator.getDefault().getPaletteManager();
		
		String[][] comboValues = new String[palettes.size()][2];
		int i =0;
		for (Palette p : palettes.values()){
			comboValues[i][0]= p.getName();
			comboValues[i][1]= p.getId();
			i++;
		}
		
		addField(new ComboFieldEditor(PREF_PALETTE,"Palette couleur", comboValues, getFieldEditorParent()));
	}	
	
	
}
