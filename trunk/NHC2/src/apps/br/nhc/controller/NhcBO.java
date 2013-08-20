package apps.br.nhc.controller;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;
import apps.br.nhc.model.domain.DataItem;
import apps.br.nhc.model.domain.PersonItem;
import apps.br.nhc.model.domain.Matrix;

public class NhcBO {
	
	private static NhcBO instance;
	
	private Map<String, DataItem> itemListDB = new HashMap<String, DataItem>();
	private Map<String, PersonItem> buddyListDB = new HashMap<String, PersonItem>();
//	private Hashtable<String, Integer> ItemIDs = null;
	
	private Matrix matrix = new Matrix();
	
	public static NhcBO getInstance() {
		
		if(instance == null) {
			
			instance = new NhcBO();
		}
		
		return instance;
	}

//	public void setMatrix() {
//
//		if (Matrix == null) {
//			
//			Log.d("CALC INSTANCE", "Starting Matrix...");
//			
//			if ((this.getItemListDB() != null) && (this.getBuddyListDB() != null)) {
//				
//				int item_size = this.getItemListDB().size();
//				int buddy_size = this.getBuddyListDB().size();
//				
////				Matrix = new Matrix[item_size][buddy_size];
//
//				for (int x = 0; x < item_size; x++) {
//					
//					for (int y = 0; y < buddy_size; y++) {
//						Matrix.put(key, value) = false;
//					}
//				}
//			}
//		}
//	}
	
	public void setMatrix(final String item, final String person) {
		
		if(matrix.get(item) == null) {
			matrix.put(item, new HashMap<String, Boolean>());
		}
		
		matrix.get(item).put(person, true);
	}
	
	public void unsetMatrix(final String item, final String person) {
		
		if(matrix.get(item) != null) {
			
			matrix.get(item).remove(person);
			
			if(matrix.get(item).size() == 0) {
				matrix.remove(item);
			}
		}
		
	}
	
	
	public Map<String, DataItem> getItemListDB() {
		return itemListDB;
	}
	public void setItemListDB(Map<String, DataItem> itemListDB) {
		this.itemListDB = itemListDB;
	}

	public Map<String, PersonItem> getBuddyListDB() {
		return buddyListDB;
	}
	public void setBuddyListDB(Map<String, PersonItem> buddyListDB) {
		this.buddyListDB = buddyListDB;
	}
	
}
