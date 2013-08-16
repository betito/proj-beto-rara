package apps.br.nhc.controller;

import java.util.ArrayList;
import java.util.List;

import apps.br.nhc.model.domain.DataItem;
import apps.br.nhc.model.domain.PersonItem;

public class NhcBO {
	
	private static NhcBO instance;
	
	private List<DataItem> itemListDB = new ArrayList<DataItem>();
	private List<PersonItem> buddyListDB = new ArrayList<PersonItem>();
//	private Hashtable<String, Integer> ItemIDs = null;
	
	public static NhcBO getInstance() {
		
		if(instance == null) {
			
			instance = new NhcBO();
		}
		
		return instance;
	}

	public List<PersonItem> getBuddyListDB() {
		return buddyListDB;
	}

	public void setBuddyListDB(List<PersonItem> buddyListDB) {
		this.buddyListDB = buddyListDB;
	}

	public List<DataItem> getItemListDB() {
		return itemListDB;
	}
	public void setItemListDB(List<DataItem> itemListDB) {
		this.itemListDB = itemListDB;
	}
	
	
	
	
}
