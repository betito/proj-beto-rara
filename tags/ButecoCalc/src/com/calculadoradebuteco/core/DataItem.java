package com.calculadoradebuteco.core;

public class DataItem {

	int Id = 0;
	String Name = "";
	float Price = 0;
	int Quant = 0;

	public DataItem() {
		super();
	}
	
	public DataItem(int id, String name, String price, String quant) {
		super();
		Id = id;
		Name = name;
		Price = Float.parseFloat(price);
		Quant = Integer.parseInt(quant);
	}

	public DataItem(int id, String name, float price, int quant) {
		super();
		Id = id;
		Name = name;
		Price = price;
		Quant = quant;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public float total() {
		return (Price * (float) Quant);
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public int getQuant() {
		return Quant;
	}

	public void setQuant(int quant) {
		Quant = quant;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
