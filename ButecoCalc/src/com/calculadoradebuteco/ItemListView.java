package com.calculadoradebuteco;

public class ItemListView {

	private String Item;

	public ItemListView() {
		this("");
	}

	public ItemListView(String item) {
		super();
		Item = item;
	}

	public String getItem() {
		return Item;
	}

	public void setItem(String item) {
		Item = item;
	}

}
