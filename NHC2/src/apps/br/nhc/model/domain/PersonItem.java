package apps.br.nhc.model.domain;

public class PersonItem {
	
	private String name;
	private double total;
	private double totalPlus10;
	
	public PersonItem(final String name) {
		
		this.name = name;
	}
	
	public PersonItem(final String name, final double total, final double totalPlus10) {
		
		this.name = name;
		this.total = total;
		this.totalPlus10 = totalPlus10;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
		
		totalPlus10 = total + (total * 10 / 100d);
	}

	public double getTotalPlus10() {
		return totalPlus10;
	}
	public void setTotalPlus10(double totalPlus10) {
		this.totalPlus10 = totalPlus10;
	}
	
	
	
}
