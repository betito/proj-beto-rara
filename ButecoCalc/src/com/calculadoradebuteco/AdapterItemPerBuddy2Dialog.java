package com.calculadoradebuteco;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

public class AdapterItemPerBuddy2Dialog extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<ItemListView> itens;

	public AdapterItemPerBuddy2Dialog(Context context, ArrayList<ItemListView> itens) {
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
			view = mInflater.inflate(R.layout.adapter_item_buddy_list_of_items, null);

			// cria um item de suporte para não precisarmos sempre
			// inflar as mesmas informacoes
			itemHolder = new ItemSuporte();
			itemHolder.txtTitle = ((CheckBox) view
					.findViewById(R.id.item_child_check));

			// define os itens na view;
			view.setTag(itemHolder);

		} else {
			// se a view já existe pega os itens.
			itemHolder = (ItemSuporte) view.getTag();
		}

		// pega os dados da lista
		// e define os valores nos itens.
		ItemListView item = itens.get(position);
		itemHolder.txtTitle.setText(item.getItem());

		// retorna a view com as informações
		return view;

	}

	private class ItemSuporte {

		CheckBox txtTitle;

	}

}
