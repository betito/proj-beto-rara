package com.calculadoradebuteco.model;

import java.util.Hashtable;

import com.calculadoradebuteco.core.DataItem;

public class CalcButeco {

	private Hashtable<String, DataItem> itemListDB = null;
	private Hashtable<String, Integer> buddyList_DB = null;

	private static CalcButeco instance;

	public static CalcButeco getInstance() {

		if (instance == null) {
			instance = new CalcButeco();
		}

		return instance;
	}

	public Hashtable<String, DataItem> getItemListDB() {
		return itemListDB;
	}

	public void setItemListDB(Hashtable<String, DataItem> itemListDB) {
		this.itemListDB = itemListDB;
	}

	public Hashtable<String, Integer> getBuddyList_DB() {
		return buddyList_DB;
	}

	public void setBuddyList_DB(Hashtable<String, Integer> buddyList_DB) {
		this.buddyList_DB = buddyList_DB;
	}

}
