package apps.br.nhc.view.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import apps.br.nhc.R;
import apps.br.nhc.model.domain.ItensListSelect;

public class ListMergeAdapter extends BaseAdapter {

	private final LayoutInflater inflater;
	private transient List<ItensListSelect> list;

	public ListMergeAdapter(final Context context, final List<ItensListSelect> list) {

		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public ItensListSelect getItem(final int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(final int position) {
		return position;
	}

	@Override
	public View getView(int position, final View convertView, final ViewGroup parent) {

		View view = convertView;

		if (convertView == null) {

			view = inflater.inflate(R.layout.merge_list_item, parent, false);
		}

		ItensListSelect item = getItem(position);

		TextView itemName = (TextView) view.findViewById(R.id.merge_list_item_name);

		itemName.setText(item.getItem());

		int bgColor;
		if (item.isSelected()) {
			bgColor = Color.parseColor("#00775F");
		} else {
			bgColor = Color.parseColor("#00779F");
		}

		view.setBackgroundColor(bgColor);

		return view;
	}

	public void setList(final List<ItensListSelect> list) {
		this.list = list;
	}

	public List<ItensListSelect> getList() {
		return list;
	}

}
