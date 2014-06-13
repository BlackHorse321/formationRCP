package com.atos.rental.ui.views;

import java.util.Collection;
import java.util.Map;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.atos.rental.preference.Palette;
import com.atos.rental.ui.RentalUIConstants;
import com.atos.rental.ui.RentalUiActivator;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements
		ITreeContentProvider, RentalUIConstants, IColorProvider {

	private static final Object[] EMPTY_RESULT = new Object[0];

	public String getText(Object element) {
		if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		} else if (element instanceof Customer){
			return ((Customer) element).getDisplayName();
		}else if (element instanceof RentalObject){
			return ((RentalObject) element).getName();
		}
		
		return super.getText(element);
	}
	
	@Override
	public Image getImage(Object element) {
		ImageRegistry reg = RentalUiActivator.getDefault().getImageRegistry();
		if (element instanceof RentalAgency) {
			return reg.get(IMG_AGENCY_KEY);
		} else if (element instanceof Customer){
			return reg.get(IMG_CUSTOMER_KEY);
		}else if (element instanceof RentalObject){
			return reg.get(IMG_RENTAL_OBJECT_KEY);
		}
		
		return reg.get(IMG_RENTAL_KEY);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
		Object[] result = null;

		if (inputElement instanceof Collection<?>) {
			return ((Collection<?>) inputElement).toArray();
		}
		return (result == null) ? EMPTY_RESULT : result;
	}

	@Override
	public Object[] getChildren(Object parentElement) {

		if (parentElement instanceof RentalAgency) {
			RentalAgency a = (RentalAgency) parentElement;
			return new Node[] { new Node(CUSTOMER_NAME, a),
					new Node(CUSTOMER_LOCATION, a), new Node(LOCATION_NAME, a) };

		}
		if (parentElement instanceof Node) {
			return ((Node) parentElement).getChildren();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {

		if ((element instanceof Customer) || (element instanceof Rental)
				|| (element instanceof RentalObject)) {
			return false;
		}

		return true;
	}

	class Node {
		private String label;
		private RentalAgency a;

		public Node(String l, RentalAgency ap) {

			label = l;
			a = ap;
		}

		public Object[] getChildren() {
			if (label == CUSTOMER_NAME) {
				return a.getCustomers().toArray();
			}
			if (label == CUSTOMER_LOCATION) {
				return a.getRentals().toArray();
			}
			if (label == LOCATION_NAME) {
				return a.getObjectsToRent().toArray();
			}
			return EMPTY_RESULT;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return label;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((a == null) ? 0 : a.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (a == null) {
				if (other.a != null)
					return false;
			} else if (!a.equals(other.a))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}

		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}
		
		
	}

	@Override
	public Color getForeground(Object element) {
		
		Map<String, Palette> palettes = RentalUiActivator.getDefault().getPaletteManager();
		
		String idPal = RentalUiActivator.getDefault().getPreferenceStore().getString(PREF_PALETTE);
		Palette p = palettes.get(idPal);
		
		return p.getPalette().getForeground(element);
		
	}
	@Override
	public Color getBackground(Object element) {

		Map<String, Palette> palettes = RentalUiActivator.getDefault().getPaletteManager();
		
		String idPal = RentalUiActivator.getDefault().getPreferenceStore().getString(PREF_PALETTE);
		Palette p = palettes.get(idPal);
		
		return p.getPalette().getBackground(element);
	}
	


}
