package apps.br.nhc.model.domain;

public class DataItem {

	private int id;
	private String name;
	private double price;
	private int quant;

	public DataItem() {
		super();
	}
	
	public DataItem(int id, String name, String price, String quant) {
		this(id, name, Float.parseFloat(price), Integer.parseInt(quant));
	}

	public DataItem(int id, String name, double price, int quant) {
		super();
		
		this.id = id;
		this.name = name;
		this.price = price;
		this.quant = quant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double total() {
		return price * (double) quant;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
