package apps.br.nhc.controller.activity;

import java.util.List;

import apps.br.nhc.model.domain.PersonItem;

public class NhcBO {
	
	private static NhcBO instance;
	
//	private Hashtable<String, DataItem> itemList_DB = null;
	private List<PersonItem> buddyListDB = null;
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
	
	
	
	
}
