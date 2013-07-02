package com.calculadoradebuteco.core;

public class DataItem {

	float Price = 0;
	int Quant = 0;

	public DataItem() {
		super();
	}

	public DataItem(float price, int quant) {
		super();
		Price = price;
		Quant = quant;
	}

	public float total (){
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

}
