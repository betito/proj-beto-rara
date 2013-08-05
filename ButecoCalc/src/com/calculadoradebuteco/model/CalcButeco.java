package com.calculadoradebuteco.model;

import java.util.Hashtable;

import android.util.Log;

import com.calculadoradebuteco.core.DataItem;

public class CalcButeco {

	private Hashtable<String, DataItem> itemList_DB = null;
	private Hashtable<String, Integer> buddyList_DB = null;
	private Hashtable<String, Integer> ItemIDs = null;
	private int Matrix[][] = null;
	private int BUDDY_ID_COUNT = 0;
	private int ITEM_ID_COUNT = 0;

	private static CalcButeco instance;

	public static CalcButeco getInstance() {

		if (instance == null) {
			instance = new CalcButeco();
		}

		return instance;
	}

	public Hashtable<String, Integer> getItemIDs() {
		return ItemIDs;
	}

	public void setItemIDs(Hashtable<String, Integer> itemIDs) {
		ItemIDs = itemIDs;
	}

	public Hashtable<String, DataItem> getItemListDB() {
		return itemList_DB;
	}

	public void setItemListDB(Hashtable<String, DataItem> itemListDB) {
		this.itemList_DB = itemListDB;
	}

	public Hashtable<String, Integer> getBuddyList_DB() {
		return buddyList_DB;
	}

	public void setBuddyList_DB(Hashtable<String, Integer> buddyList_DB) {
		this.buddyList_DB = buddyList_DB;
	}

	public int getBUDDY_ID_COUNT() {
		return BUDDY_ID_COUNT;
	}

	public void setBUDDY_ID_COUNT(int bUDDY_ID_COUNT) {
		BUDDY_ID_COUNT = bUDDY_ID_COUNT;
	}

	public void inc_BUDDY_ID_COUNT() {
		this.BUDDY_ID_COUNT++;
	}

	public void inc_ITEM_ID_COUNT() {
		this.ITEM_ID_COUNT++;
	}

	public int getITEM_ID_COUNT() {
		return ITEM_ID_COUNT;
	}

	public void setITEM_ID_COUNT(int iTEM_ID_COUNT) {
		ITEM_ID_COUNT = iTEM_ID_COUNT;
	}

	public int[][] getMatrix() {
		return Matrix;
	}

	public int getMatrixData(String lin, String col) {
		int l = ((Integer) (this.ItemIDs.get(lin)).intValue());
		int c = ((Integer) (this.buddyList_DB.get(col)).intValue());
		return Matrix[l][c];
	}

	public void setMatrix() {

		if (Matrix == null) {
			Log.d("CALC INSTANCE", "Starting Matrix...");
			if ((this.getItemListDB() != null)
					&& (this.getBuddyList_DB() != null)) {
				int item_size = this.getItemListDB().size();
				int buddy_size = this.getBuddyList_DB().size();
				Matrix = new int[item_size][buddy_size];

				for (int x = 0; x < item_size; x++) {
					for (int y = 0; y < buddy_size; y++) {
						Matrix[x][y] = 0;
					}
				}
			}
		}

	}

	public void checkMatrix(int posItem, int posBuddy) {

		this.Matrix[posItem][posBuddy] = 1;

	}

	public void uncheckMatrix(int posItem, int posBuddy) {

		this.Matrix[posItem][posBuddy] = 0;

	}

	public void clear() {

		this.BUDDY_ID_COUNT = 0;
		this.Matrix = null;
		if (this.buddyList_DB != null) {
			this.buddyList_DB.clear();
		}
		if (this.ItemIDs != null) {
			this.ItemIDs.clear();
		}
		if (this.itemList_DB != null) {
			this.itemList_DB.clear();
		}

		Log.i("CALCBUTECO", "All Clear...");

	}

}
