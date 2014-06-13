package com.atos.rental.adapters.customer;

import org.eclipse.core.runtime.IAdapterFactory;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;

public class RentalCustomerAdaptor implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		Customer result = null;
		
		if ((adaptableObject instanceof Rental) && (adapterType == Customer.class)){
			result = ((Rental)adaptableObject).getCustomer();
		}
		
		
		return result;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] { Customer.class };
	}

}
