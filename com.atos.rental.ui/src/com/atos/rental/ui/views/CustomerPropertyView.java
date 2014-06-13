package com.atos.rental.ui.views;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;

public class CustomerPropertyView extends ViewPart implements ISelectionListener{

	private Label rentedObjectLabel;
	
	public CustomerPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup= new Group(parent, SWT.NONE);
		GridData gd_infoGroup = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_infoGroup.widthHint = 376;
		gd_infoGroup.heightHint = 59;
		infoGroup.setLayoutData(gd_infoGroup);
		infoGroup.setText("Informations");
        infoGroup.setLayout(new GridLayout(2, false));
        
        rentedObjectLabel = new Label(infoGroup, SWT.NONE);
        rentedObjectLabel.setAlignment(SWT.CENTER);
        GridData gd = new GridData();
        gd.horizontalSpan = 2;
        gd.grabExcessHorizontalSpace = true;
        gd.horizontalAlignment=SWT.FILL;
        rentedObjectLabel.setLayoutData(gd);

    
        setLableAsDragSource (rentedObjectLabel);
            
        
	}
	
	public  void setLableAsDragSource(final Label label) {
		DragSource source = new DragSource(label, DND.DROP_MOVE|DND.DROP_COPY);
		
		//Define the transfer
		source.setTransfer(new Transfer[]{TextTransfer.getInstance()});
		//Add a drag listener
		source.addDragListener(new DragSourceAdapter(){
			@Override
			public void dragSetData(DragSourceEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.dataType)){
					event.data=label.getText();
				}
			}
		}
			
		);
		
		
	}

 void setRental(Customer c){
		rentedObjectLabel.setText(c.getDisplayName());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection){
			Object sel = ((IStructuredSelection) selection).getFirstElement();
			
			Customer cust = ((Customer) Platform.getAdapterManager().getAdapter(sel, Customer.class));
			
			if (cust != null){
				setRental(cust);
			}
		}
		
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}
	
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}
}
