package com.calculadoradebuteco.core;

import java.util.Enumeration;
import java.util.Hashtable;

public class CalcularConta {

	private Hashtable<String, Integer> Buddy = null;
	private Hashtable<String, Integer> Item = null;
	Hashtable<String, DataItem> ItemD = null;
	private Float Service = null;
	private int Matrix[][] = null;
	int X_ = 1;

	CalcularConta() {
		super();
		this.Buddy = new Hashtable<String, Integer>(10);
		this.Item = new Hashtable<String, Integer>(10);
		this.ItemD = new Hashtable<String, DataItem>(10);
		this.Service = new Float(10.0);
		this.Matrix = new int[10][10];
	}

	public CalcularConta(Hashtable<String, Integer> buddy,
			Hashtable<String, Integer> item, Hashtable<String, DataItem> itemd,
			int matrix[][]) {
		super();

		this.Item = item;
		this.Buddy = buddy;
		this.ItemD = itemd;
		this.Matrix = matrix;
		this.Service = new Float(10.0);

	}

	public Hashtable<String, Float> calculateTotalPerItem() {

		Hashtable<String, Float> output = new Hashtable<String, Float>();

		for (Enumeration<String> en = this.Item.keys(); en.hasMoreElements();) {
			String item = (String) en.nextElement();
			DataItem datai = this.ItemD.get(item);
			// System.out.printf("%s\t[%.2f]\n", item, datai.total());
			output.put(item, new Float(datai.total()));

		}

		return output;
	}

	public Hashtable<String, Integer> countAllBuddyPerItem() {

		Hashtable<String, Integer> vectOccur = new Hashtable<String, Integer>(
				this.Item.size());

		for (Enumeration<String> eni = this.Item.keys(); eni.hasMoreElements();) {

			String item = (String) eni.nextElement();
			int i = this.Item.get(item);
			int occur = 0;

			for (Enumeration<String> enj = this.Buddy.keys(); enj
					.hasMoreElements();) {
				String buddy = (String) enj.nextElement();
				int j = this.Buddy.get(buddy);

				if ((Matrix[i][j]) == X_) {
					occur++;
				}
			}

			vectOccur.put(item, new Integer(occur));

		}

		for (Enumeration<String> eni = vectOccur.keys(); eni.hasMoreElements();) {
			String item = eni.nextElement();
			int occur = vectOccur.get(item);
			// System.out.printf("%s [%d]\n", item, occur);
		}

		return vectOccur;

	}

	public Hashtable<String, Float> calculateIndividualBuddyAccountWithoutService() {

		Hashtable<String, Float> vectAccount = new Hashtable<String, Float>(
				this.Buddy.size());
		Hashtable<String, Integer> vectOccur = countAllBuddyPerItem();
		Hashtable<String, Float> vectTotalPerItem = calculateTotalPerItem();

		for (Enumeration<String> eni = this.Buddy.keys(); eni.hasMoreElements();) {

			String buddy = (String) eni.nextElement();
			int i = this.Buddy.get(buddy);
			float occur = 0;
			int cont = 0;

			// System.out.println(buddy);

			for (Enumeration<String> enj = this.Item.keys(); enj
					.hasMoreElements();) {
				String item = (String) enj.nextElement();
				int j = this.Item.get(item);

				if ((Matrix[j][i]) == X_) {
					Float totalPerItem = vectTotalPerItem.get(item);
					Integer countTotalPerItem = vectOccur.get(item);
					occur += (totalPerItem.floatValue() / (float) (countTotalPerItem
							.floatValue()));
					// System.out.println(item + " = " +
					// totalPerItem.floatValue());
					cont++;
				}
			}

			vectAccount.put(buddy, new Float(occur));

		}

		System.out.println("Total WITHOUT SERVICE");
		
		float total = 0;
		
		for (Enumeration<String> eni = vectAccount.keys(); eni
				.hasMoreElements();) {
			String item = eni.nextElement();
			Float occur = vectAccount.get(item);
			total += occur.floatValue();
			System.out.printf("%s [%.2f]\n", item, occur.floatValue());
		}
		
		System.out.printf(">>  Total = %.2f\n\n", total);

		return vectAccount;

	}

	public Hashtable<String, Float> calculateIndividualBuddyAccountWithService() {

		Hashtable<String, Float> vectAccount = calculateIndividualBuddyAccountWithoutService();

		System.out.println("Total WITH SERVICE");
		
		float total = 0;
		for (Enumeration<String> eni = vectAccount.keys(); eni
				.hasMoreElements();) {
			String item = eni.nextElement();
			Float occur = (float) (vectAccount.get(item) * (1.0 + (this
					.getService() / 100)));
			total += occur.floatValue();
			vectAccount.put(item, occur);
			System.out.printf("%s [%.2f]\n", item, occur.floatValue());
		}

		System.out.printf(">>  Total = %.2f\n\n", total);
		
		return vectAccount;

	}

	private int countOccurOfItem(String item) {

		int output = 0;
		int pos = this.Buddy.get(item).intValue();

		for (int i = 0; i < this.Matrix[pos].length; i++) {
			if (Matrix[pos][i] == X_) {
				output++;
			}
		}

		return output;
	}

	public float calculateTotalWithService() {

		float output = 0;

		for (Enumeration<String> en = this.Item.keys(); en.hasMoreElements();) {
			String item = (String) en.nextElement();
			DataItem datai = this.ItemD.get(item);
			output += datai.total();

		}

		output = (float) (output * (1.0 + (float) this.getService() / 100));

		System.out.printf("Total with service = [%.2f]\n", output);

		return output;
	}

	public float calculateTotalWithoutService() {

		float output = 0;

		for (Enumeration<String> en = this.Item.keys(); en.hasMoreElements();) {
			String item = (String) en.nextElement();
			DataItem datai = this.ItemD.get(item);
			output += datai.total();

		}

		System.out.printf("Total without service = [%.2f]\n", output);

		return output;
	}

	public Hashtable<String, Integer> getBuddy() {
		return Buddy;
	}

	public void setBuddy(Hashtable<String, Integer> buddy) {
		Buddy = buddy;
	}

	public Hashtable<String, Integer> getItem() {
		return Item;
	}

	public void setItem(Hashtable<String, Integer> item) {
		Item = item;
	}

	public Hashtable<String, DataItem> getItemD() {
		return ItemD;
	}

	public void setItemD(Hashtable<String, DataItem> itemD) {
		ItemD = itemD;
	}

	public void setService(Float service) {
		Service = service;
	}

	public float getService() {
		return Service.floatValue();
	}

	public void setService(float service) {
		Service = Float.valueOf(service);
	}

	public int[][] getMatrix() {
		return Matrix;
	}

	public void setMatrix(int[][] matrix) {
		Matrix = matrix;
	}

}
