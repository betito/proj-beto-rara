package apps.br.nhc.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.util.Pair;
import apps.br.nhc.model.domain.DataItem;
import apps.br.nhc.model.domain.PersonItem;

import apps.br.nhc.model.domain.Matrix;

public class CalcBill {

	private Map<String, PersonItem> personList = null;
	private Map<String, DataItem> itemList = null;
	private Matrix matrix = null;
	
	public CalcBill(Map<String, PersonItem> person, Map<String, DataItem> item, final Matrix matrix) {
		super();

		this.itemList = item;
		this.personList = person;
		this.matrix = matrix;
		
	}

	public Map<String, Double> calculateTotalPerItem() {

		Map<String, Double> output = new HashMap<String, Double>();

		for(Entry<String, DataItem> dataItem : itemList.entrySet()) {
			output.put(dataItem.getKey(), dataItem.getValue().total());
		}

		return output;
	}

	public Map<String, Integer> countAllBuddyPerItem() {

		Map<String, Integer> vectOccur = new HashMap<String, Integer>(this.itemList.size());

		for (String item : itemList.keySet()) {

			int occur = 0;

			for (String person : this.personList.keySet()) {
				
				if(matrix.get(item) != null && matrix.get(item).contains(person)) {
					occur++;
				}
			}

			vectOccur.put(item, occur);
		}

		return vectOccur;
	}

	public Pair<Double, Double> calculateIndividualBuddyAccountWithoutService() {

		double total = 0d;
		
		Map<String, Integer> vectOccur = countAllBuddyPerItem();
		Map<String, Double> vectTotalPerItem = calculateTotalPerItem();

		for (String person : personList.keySet()) {

			double personTotal = 0d;

			for (String item : this.itemList.keySet()) {
				
				if(matrix.get(item) != null && matrix.get(item).contains(person)) {
					
					Double totalPerItem = vectTotalPerItem.get(item);
					Integer countTotalPerItem = vectOccur.get(item);
					
					personTotal += totalPerItem / countTotalPerItem.doubleValue();
				}
			}
			
			total += personTotal;
			
			personList.get(person).setTotal(personTotal);
		}

		return Pair.create(total, total + (total * 10d / 100d));
	}

	public Map<String, PersonItem> getBuddy() {
		return personList;
	}
	public void setBuddy(Map<String, PersonItem> buddy) {
		personList = buddy;
	}

	public Map<String, DataItem> getItem() {
		return itemList;
	}
	public void setItem(Map<String, DataItem> item) {
		itemList = item;
	}

	public Matrix getMatrix() {
		return matrix;
	}
	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

}
