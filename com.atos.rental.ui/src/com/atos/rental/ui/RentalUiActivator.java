package com.atos.rental.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUiActivator extends AbstractUIPlugin implements
		RentalUIConstants {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.atos.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalUiActivator plugin;

	private Map<String, Palette> paletteManager = new HashMap<String, Palette>();

	/**
	 * The constructor
	 */
	public RentalUiActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		readPluggin();

	}

	private void readPluggin() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();

		for (IConfigurationElement e : reg
				.getConfigurationElementsFor("com.atos.ui.color.property")) {

			Palette palette;
			try {
				palette = new Palette(e.getAttribute("id"),
						e.getAttribute("name"),
						(IColorProvider) e.createExecutableExtension("Class"));
				paletteManager.put(e.getAttribute("id"), palette);

			} catch (InvalidRegistryObjectException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUiActivator getDefault() {
		return plugin;
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		Bundle b = FrameworkUtil.getBundle(getClass());

		reg.put(IMG_CUSTOMER_KEY,
				ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER_KEY)));
		reg.put(IMG_RENTAL_KEY,
				ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL_KEY)));
		reg.put(IMG_RENTAL_OBJECT_KEY, ImageDescriptor.createFromURL(b
				.getEntry(IMG_RENTAL_OBJECT_KEY)));
		reg.put(IMG_AGENCY_KEY,
				ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY_KEY)));
	}

}
