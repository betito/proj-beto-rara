package apps.br.nhc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.util.Pair;
import apps.br.nhc.model.CalcBill;
import apps.br.nhc.model.domain.DataItem;
import apps.br.nhc.model.domain.PersonItem;
import apps.br.nhc.model.domain.Matrix;

public class NhcBO {
	
	private static NhcBO instance;
	
	private Map<String, DataItem> itemListDB = new HashMap<String, DataItem>();
	private Map<String, PersonItem> buddyListDB = new HashMap<String, PersonItem>();
//	private Hashtable<String, Integer> ItemIDs = null;
	
	private Matrix matrix = new Matrix();
	
	private double totalBill;
	private double totalP10;
	
	public static NhcBO getInstance() {
		
		if(instance == null) {
			
			instance = new NhcBO();
		}
		
		return instance;
	}

	public void setMatrix(final String item, final String person) {
		
		if(matrix.get(item) == null) {
			matrix.put(item, new ArrayList<String>());
		}
		
		matrix.get(item).add(person);
	}
	
	public void unsetMatrix(final String item, final String person) {
		
		if(matrix.get(item) != null) {
			
			matrix.get(item).remove(person);
			
			if(matrix.get(item).size() == 0) {
				matrix.remove(item);
			}
		}
		
	}
	
	public void calcBill() {
		CalcBill calcBill = new CalcBill(buddyListDB, itemListDB, matrix);
		
		Pair<Double, Double> totals = calcBill.calculateIndividualBuddyAccountWithoutService();
		
		totalBill = totals.first;
		totalP10 = totals.second;
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

	public Matrix getMatrix() {
		return matrix;
	}
	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

	public double getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(double totalBill) {
		this.totalBill = totalBill;
	}

	public double getTotalP10() {
		return totalP10;
	}
	public void setTotalP10(double totalP10) {
		this.totalP10 = totalP10;
	}
	
	
}
