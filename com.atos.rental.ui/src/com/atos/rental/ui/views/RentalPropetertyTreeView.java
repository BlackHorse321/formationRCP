package com.atos.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.atos.rental.core.RentalCoreActivator;
import com.atos.rental.ui.RentalUiActivator;
import com.opcoach.training.rental.RentalAgency;

public class RentalPropetertyTreeView extends ViewPart implements IPropertyChangeListener{
	
	private RentalProvider rentalProvider;
	private TreeViewer tv;
	
	
	public RentalPropetertyTreeView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		tv = new TreeViewer(parent);
		rentalProvider = new RentalProvider();
		
		tv.setContentProvider (rentalProvider);
		tv.setLabelProvider (rentalProvider);
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());
		tv.setInput(agencies);
		
		getSite().setSelectionProvider(tv);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		tv.refresh();
		
	}
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		RentalUiActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		this.getSite().getPage().removePropertyChangeListener(this);
	}

}
