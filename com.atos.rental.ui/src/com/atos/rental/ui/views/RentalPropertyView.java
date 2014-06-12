package com.atos.rental.ui.views;

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

import com.opcoach.training.rental.Rental;

public class RentalPropertyView extends ViewPart implements ISelectionListener{

	private Label rentedObjectLabel;
	private Label lblJohn;
	private Label lblDu;
	private Label startDate;
	private Label stopDate;
	
	public RentalPropertyView() {
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
        
        Label lblLou = new Label(infoGroup, SWT.NONE);
        GridData gd_lblLou = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_lblLou.widthHint = 145;
        lblLou.setLayoutData(gd_lblLou);
        lblLou.setText("Lou\u00E9 \u00E0 :");
        
        lblJohn = new Label(infoGroup, SWT.NONE);
        GridData gd_lblJohn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_lblJohn.widthHint = 416;
        lblJohn.setLayoutData(gd_lblJohn);
        lblJohn.setText("john");
        
        Group grpDatesDeLocation = new Group(parent, SWT.NONE);
        GridData gd_grpDatesDeLocation = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gd_grpDatesDeLocation.heightHint = 48;
        grpDatesDeLocation.setLayoutData(gd_grpDatesDeLocation);
        grpDatesDeLocation.setText("Dates de location");
        GridLayout gl_grpDatesDeLocation = new GridLayout(2, false);
        gl_grpDatesDeLocation.verticalSpacing = 2;
        grpDatesDeLocation.setLayout(gl_grpDatesDeLocation);
        
        lblDu = new Label(grpDatesDeLocation, SWT.NONE);
        GridData gd_lblDu = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_lblDu.widthHint = 129;
        lblDu.setLayoutData(gd_lblDu);
        lblDu.setText("Du :");
        
        startDate = new Label(grpDatesDeLocation, SWT.NONE);
        startDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        startDate.setText("15/03/2011");
        
        Label lblAu = new Label(grpDatesDeLocation, SWT.NONE);
        lblAu.setText("au :");
        
        stopDate = new Label(grpDatesDeLocation, SWT.NONE);
        stopDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        stopDate.setText("22/03/2011");

    
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

 void setRental(Rental r){
		rentedObjectLabel.setText(r.getRentedObject().getName());
		lblJohn.setText(r.getCustomer().getDisplayName());
		startDate.setText(r.getStartDate().toString());
		stopDate.setText(r.getEndDate().toString());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection){
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			
			if (obj instanceof Rental){
				setRental((Rental)obj);
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
