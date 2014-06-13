package com.atos.rental.preference;

import org.eclipse.jface.viewers.IColorProvider;

public class Palette {

	private String id, name;
	private IColorProvider palette;
	
	
	public Palette(String id, String name, IColorProvider palette) {
		super();
		this.id = id;
		this.name = name;
		this.palette = palette;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IColorProvider getPalette() {
		return palette;
	}
	public void setPalette(IColorProvider palette) {
		this.palette = palette;
	}
	
	
}
