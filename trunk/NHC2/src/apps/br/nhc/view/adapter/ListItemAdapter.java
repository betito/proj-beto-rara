package apps.br.nhc.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import apps.br.nhc.R;
import apps.br.nhc.model.domain.DataItem;

public class ListItemAdapter extends BaseAdapter {

	private final LayoutInflater inflater;
	private transient List<DataItem> list;

	public ListItemAdapter(Context context, List<DataItem> list) {

		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public DataItem getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, final View convertView, final ViewGroup parent) {

		View view = convertView;

		if (convertView == null) {
			view = inflater.inflate(R.layout.add_item_list_item, parent, false);
		}

		DataItem item = getItem(position);

		TextView txvName = (TextView) view.findViewById(R.id.item_list_name);
		TextView txvPrice = (TextView) view.findViewById(R.id.item_list_price);
		TextView txvQtd = (TextView) view.findViewById(R.id.item_list_qtd);

		txvName.setText(item.getName());
		txvPrice.setText(String.valueOf(item.getPrice()));
		txvQtd.setText(String.valueOf(item.getQuant()));

		return view;
	}

	public void setList(List<DataItem> list) {
		this.list = list;
	}

	public List<DataItem> getList() {
		return list;
	}

}
