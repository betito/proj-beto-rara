package apps.br.nhc.controller.activity.subactivity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import apps.br.nhc.R;
import apps.br.nhc.controller.NhcBO;
import apps.br.nhc.controller.activity.MainActivity;
import apps.br.nhc.model.domain.ItensListSelect;
import apps.br.nhc.view.adapter.ListMergeAdapter;

public class MergeSubActivity extends SubActivity implements OnClickListener, OnItemClickListener {

	private String personName;
	
	private TextView tvxDesc;
	private Button butSalvar;
	private ListView itemList;
	
	private ListMergeAdapter listMergeAdapter;
	
	public MergeSubActivity(Activity activity, View viewRootSub, final String personName) {
		super(activity, viewRootSub);
		
		this.personName = personName;
		
		tvxDesc = (TextView) activity.findViewById(R.id.merge_item_person_desc);
		butSalvar = (Button) activity.findViewById(R.id.merge_item_person_close);
		itemList = (ListView) activity.findViewById(R.id.merge_item_person_list);
		
		tvxDesc.setText(String.format(tvxDesc.getText().toString(), personName));
		
		butSalvar.setOnClickListener(this);
		
		List<ItensListSelect> list = prepareListOfItem();
		
		itemList.setAdapter(listMergeAdapter = new ListMergeAdapter(activity, list));
		
		itemList.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		case R.id.merge_item_person_close:
			
			((MainActivity) activity).closeMergeAct(viewRootSub);
			
			break;

		default:
			break;
		}
		
	}
	
	/**
	 * Prepare list of itens
	 * 
	 * @param iniString
	 * @return
	 */
	private List<ItensListSelect> prepareListOfItem() {

		List<ItensListSelect> list = new ArrayList<ItensListSelect>();

		ItensListSelect item;

		for (String key : NhcBO.getInstance().getItemListDB().keySet()) {

			if(NhcBO.getInstance().getMatrix().get(key) != null && NhcBO.getInstance().getMatrix().get(key).contains(personName)) {
				item = new ItensListSelect(key, true);
			}
			else {
				item = new ItensListSelect(key, false);
			}
			
			list.add(item);
		}

		return list;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.e("CALC", "ItemBuddyItemClickListener::onItemClick");

		List<ItensListSelect> listItens = listMergeAdapter.getList();

		final ItensListSelect item = listItens.get(position);
//		final ItensListSelect item = new ItensListSelect(listItens.get(
//				position).getItem(),
//				(listItens.get(position).isSelected() ? false : true));

		item.setSelected(item.isSelected() ? false : true);
		
//		listItens.set(position, item);

		Log.i("NHC", "ITEM: " + item.getItem());
		Log.i("NHC", "BUDDY: " + personName);
		Log.i("NHC", "select: " + item.isSelected());

		if (listItens.get(position).isSelected()) {
			NhcBO.getInstance().setMatrix(item.getItem(), personName);
		} else {
			NhcBO.getInstance().unsetMatrix(item.getItem(), personName);
		}

//		listMergeAdapter.setList(listItens);

		listMergeAdapter.notifyDataSetChanged();
	}
	
}
