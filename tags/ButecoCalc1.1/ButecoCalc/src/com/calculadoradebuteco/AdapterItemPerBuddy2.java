package com.calculadoradebuteco;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterItemPerBuddy2 extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<BuddyListView> itens;

	public AdapterItemPerBuddy2(Context context, ArrayList<BuddyListView> itens) {
		// Itens do listview
		this.itens = itens;
		// Objeto responsável por pegar o Layout do item.
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {

		ItemSuporte itemHolder;
		// se a view estiver nula (nunca criada), inflamos o layout nela.
		if (view == null) {
			// infla o layout para podermos pegar as views
			view = mInflater.inflate(R.layout.activity_item_per_buddy2, null);

			// cria um item de suporte para não precisarmos sempre
			// inflar as mesmas informacoes
			itemHolder = new ItemSuporte();
			itemHolder.txtTitle = ((TextView) view
					.findViewById(R.id.adapter_buddy));

			// define os itens na view;
			view.setTag(itemHolder);

		} else {
			// se a view já existe pega os itens.
			itemHolder = (ItemSuporte) view.getTag();
		}

		// pega os dados da lista
		// e define os valores nos itens.
		BuddyListView item = itens.get(position);
		itemHolder.txtTitle.setText(item.getBuddy());

		// retorna a view com as informações
		return view;

	}

	private class ItemSuporte {

		TextView txtTitle;

	}

}
