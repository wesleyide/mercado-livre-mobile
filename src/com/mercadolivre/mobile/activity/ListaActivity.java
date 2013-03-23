package com.mercadolivre.mobile.activity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.example.mercadolivre.mobile.R;

public class ListaActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Integer nivel = getIntent().getExtras().getInt("nivel");
		if (nivel == null) 
			nivel = 1;

		String[] values;
		if (nivel == 1){ // Principal
			values = new String[] { "Anúncios", "Perguntas", "Vendas Ativas", "Vendas Finalizadas", "Dados dos Interessados", "MercadoEnvios" };
		}else if (nivel == 11){ // Anúncios
			values = new String[] { "Criar", "Ativos", "Pausados", "Finalizados" };
		}else if (nivel == 12){ // Perguntas
			values = new String[0];
		}else{
			values = new String[0];
		}

	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);

	    if (nivel >= 1 && nivel <= 1 + values.length){ // Principal
		    getListView().setOnItemClickListener(new OnItemClickListener() {
	            @SuppressLint("NewApi")
	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            	Intent myIntent = new Intent(ListaActivity.this, ListaActivity.class);
	    			Bundle bundle = new Bundle();
	    			bundle.putInt("nivel", position + 11);
	    			myIntent.putExtras(bundle);
	            	ListaActivity.this.startActivity(myIntent);
	            }
	        });
	    } else if (nivel >= 11 && nivel <= 11 + values.length){ // Anúncios
		    getListView().setOnItemClickListener(new OnItemClickListener() {
	            @SuppressLint("NewApi")
	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            	Intent myIntent;
	    			Bundle bundle = new Bundle();
	            	if (position == 0){ // Cadastrar
	            		myIntent = new Intent(ListaActivity.this, CadastroActivity.class);
		            	ListaActivity.this.startActivity(myIntent);
	            	}else if (position == 1){ // Ativos
	            		//myIntent = new Intent(ListaActivity.this, AnunciosActivity.class);
		            	//ListaActivity.this.startActivity(myIntent);
	            	}
	            }
	        });
	    }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
