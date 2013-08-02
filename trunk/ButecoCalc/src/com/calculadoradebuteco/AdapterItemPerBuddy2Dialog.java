package com.calculadoradebuteco;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;

public class AdapterItemPerBuddy2Dialog extends BaseAdapter implements OnClickListener{

	private LayoutInflater mInflater;
	private ArrayList<ItemListView> itens;

	private Button btn_clear = null;
	private Button btn_close = null;

	public AdapterItemPerBuddy2Dialog(Context context,
			ArrayList<ItemListView> itens) {
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
			view = mInflater.inflate(R.layout.adapter_item_buddy_list_of_items,
					null);

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

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn_clear:
			Clear();
			break;
		case R.id.btn_save:
			SaveAndClose();
			break;

		}

	}

	private void SaveAndClose() {

		Log.i("BOTAO", "Salvar e Fechar.");

	}

	private void Clear() {

		Log.i("BOTAO", "Desmarcar todos.");

	}

}
