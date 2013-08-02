package com.calculadoradebuteco.model;

import java.io.Serializable;

public class ItensListSelect implements Serializable {

	/**
	 * SERIAL
	 */
	private static final long serialVersionUID = 7511130250587554945L;
	
	private String item;
	private boolean selected;
	
	public ItensListSelect(final String item, final boolean selected) {
		
		this.item = item;
		this.selected = selected;
	}

	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
}
