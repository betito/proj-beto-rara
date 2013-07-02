package com.calculadoradebuteco.core;

import java.util.Hashtable;

public class RunCalcularConta {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Hashtable<String, Integer> Buddy = new Hashtable<String, Integer>(8);
		Hashtable<String, Integer> Item = new Hashtable<String, Integer>(8);
		Hashtable<String, DataItem> ItemD = new Hashtable<String, DataItem>(8);
		int Matrix[][] = new int[10][10];
		int X_ = 1;

		for (int x = 0; x < Item.size(); x++) {
			for (int y = 0; y < Buddy.size(); y++) {
				Matrix[x][y] = 0;
			}
		}

		int id = 0;
		Buddy.put("A", new Integer(id++));
		Buddy.put("B", new Integer(id++));
		Buddy.put("C", new Integer(id++));
		Buddy.put("D", new Integer(id++));
		Buddy.put("E", new Integer(id++));
		Buddy.put("F", new Integer(id++));
		Buddy.put("G", new Integer(id++));
		Buddy.put("H", new Integer(id++));

		id = 0;
		Item.put("cerveja", new Integer(id++));
		Item.put("tropeiro simples", new Integer(id++));
		Item.put("batata", new Integer(id++));
		Item.put("caipirinha", new Integer(id++));

		ItemD.put("cerveja", new DataItem(5, 10));
		ItemD.put("tropeiro simples", new DataItem(18, 1));
		ItemD.put("batata", new DataItem(13, 1));
		ItemD.put("caipirinha", new DataItem(8, 3));

		String item = "cerveja";

		Matrix[getInt(Item.get(item))][getInt(Buddy.get("A"))] = X_;
		Matrix[getInt(Item.get(item))][getInt(Buddy.get("B"))] = X_;
		Matrix[getInt(Item.get(item))][getInt(Buddy.get("C"))] = X_;
		Matrix[getInt(Item.get(item))][getInt(Buddy.get("F"))] = X_;
		Matrix[getInt(Item.get(item))][getInt(Buddy.get("G"))] = X_;
		Matrix[getInt(Item.get(item))][getInt(Buddy.get("H"))] = X_;

		item = "tropeiro simples";
		Matrix[getInt(Item.get(item))][getInt(Buddy.get("A"))] = X_;
		Matrix[getInt(Item.get(item))][getInt(Buddy.get("B"))] = X_;
		Matrix[getInt(Item.get(item))][getInt(Buddy.get("C"))] = X_;

		item = "batata";
		Matrix[getInt(Item.get(item))][getInt(Buddy.get("G"))] = X_;
		Matrix[getInt(Item.get(item))][getInt(Buddy.get("H"))] = X_;

		item = "caipirinha";
		Matrix[getInt(Item.get(item))][getInt(Buddy.get("D"))] = X_;
		Matrix[getInt(Item.get(item))][getInt(Buddy.get("E"))] = X_;
		Matrix[getInt(Item.get(item))][getInt(Buddy.get("H"))] = X_;

		CalcularConta cc = new CalcularConta(Buddy, Item, ItemD, Matrix);

//		cc.calculateTotalPerItem();
//		System.out.println("--------------------");
//		cc.calculateTotalWithService();
//		System.out.println("--------------------");
//		cc.calculateTotalWithoutService();
//		System.out.println("--------------------");
//		cc.countAllBuddyPerItem();
//		System.out.println("--------------------");
//		cc.calculateIndividualBuddyAccountWithoutService();
//		System.out.println("--------------------");
		cc.calculateIndividualBuddyAccountWithService();

	}

	private static int getInt(Integer data) {
		return data.intValue();
	}

}
