package apps.br.nhc.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import apps.br.nhc.R;
import apps.br.nhc.model.domain.PersonItem;

public class ListItemBuddyAdapter extends BaseAdapter {

	private final LayoutInflater inflater;
	private transient List<PersonItem> list;

	public ListItemBuddyAdapter(Context context, List<PersonItem> list) {

		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public PersonItem getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, final View convertView, ViewGroup parent) {

		View view = convertView;

		if (convertView == null) {

			view = inflater.inflate(R.layout.main_screen_list_person_item, parent, false);
		}

		PersonItem item = getItem(position);

		TextView txvName = (TextView) view.findViewById(R.id.person_list_name);
		TextView txvTotal = (TextView) view.findViewById(R.id.person_list_total);
		TextView txvTotal10 = (TextView) view.findViewById(R.id.person_list_total_plus_10);

		txvName.setText(item.getName());
		txvTotal.setText(String.valueOf(item.getTotal()));
		txvTotal10.setText(String.valueOf(item.getTotalPlus10()));

		return view;
	}

	public void setList(List<PersonItem> list) {
		this.list = list;
	}

	public List<PersonItem> getList() {
		return list;
	}

}
