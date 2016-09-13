package br.edu.utfpr.android.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

//Para fazer uma ListView, voc� pode extender a classe ListActivity
public class ListView1Activity extends AppCompatActivity {
	
	private List<String> itens = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview1);

		for(int i=1; i<20; i++) 
			itens.add("Item "+i);

		
		//Pegando a referência da ListView na tela
		final ListView listView = (ListView)findViewById(android.R.id.list);

		//Configurando a Ação de clique nos itens da ListView
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int posicao, long arg3) {
				String str = (String)listView.getItemAtPosition(posicao);
				 
				Toast.makeText(ListView1Activity.this, str, Toast.LENGTH_SHORT).show();
			}
		});
		
	
		
		// Primeiramente é necess�rio criar um ArrayAdapter
		ArrayAdapter<String> adapter 
			= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens);
		
		//Seta o adapter na ListView
		listView.setAdapter(adapter);

		
	}

}

