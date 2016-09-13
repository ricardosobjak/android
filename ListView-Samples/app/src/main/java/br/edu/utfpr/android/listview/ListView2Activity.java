package br.edu.utfpr.android.listview;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.utfpr.android.listview.listview2.AdapterListView;
import br.edu.utfpr.android.listview.listview2.ItemListView;


//Para fazer uma ListView, você pode extender a classe ListActivity
public class ListView2Activity extends AppCompatActivity {
	
	//Array contendo os dados
	private ArrayList<ItemListView> itens = new ArrayList<ItemListView>();
	
	//Componente ListView da Activity
	private ListView listView;
	
	//ArrayAdapter necessário para apresentar os dados na ListView
    private AdapterListView adapterListView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview2);
		
        //Criamos nossa lista que preenchera o ListView
        itens = new ArrayList<ItemListView>();
 

		for(int i=1; i<20; i++) {
			BitmapDrawable drawable = (BitmapDrawable)getResources().getDrawable(R.drawable.ico1, getTheme());
			
			itens.add(new ItemListView("Item "+i, drawable.getBitmap()));
		}
		
		//Pegando a refer�ncia da ListView na tela
		listView = (ListView)findViewById(android.R.id.list);

		//Configurando a Ação de clique nos itens da ListView
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int posicao, long arg3) {
				ItemListView item = (ItemListView)listView.getItemAtPosition(posicao);
				 
				Toast.makeText(ListView2Activity.this, item.getTexto(), Toast.LENGTH_SHORT).show();
			}
		});
		
	
		
		
		//Cria o adapter
        adapterListView = new AdapterListView(this, itens);
 
        //Define o Adapter
        listView.setAdapter(adapterListView);
        
        //Cor quando a lista é selecionada para rolagem.
        listView.setCacheColorHint(Color.RED);
		
	}
}