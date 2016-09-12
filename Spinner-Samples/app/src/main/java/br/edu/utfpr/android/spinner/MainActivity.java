package br.edu.utfpr.android.spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private Spinner spn1;
	private Spinner spn2;
	private List<String> nomes = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Adicionando Nomes no ArrayList
		nomes.add("Adriana");
		nomes.add("Bernardo");
		nomes.add("Fernando");
		nomes.add("Paulo");
		nomes.add("Vanessa");

		// Busca a referência para os spinners definidos no layout
		spn1 = (Spinner) findViewById(R.id.spinner1);
		spn2 = (Spinner) findViewById(R.id.spinner2);
		
		// Cria um ArrayAdapter usando um padrão de layout da classe R do
		// android, passando o ArrayList nomes
		ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(
						this, android.R.layout.simple_spinner_dropdown_item, nomes);
		arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spn1.setAdapter(arrayAdapter1);

		// Método do Spinner para capturar o item selecionado
		spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int posicao, long id) {
				// pega nome pela posição
				String nome = parent.getItemAtPosition(posicao).toString();
				// imprime um Toast na tela com o nome que foi selecionado
				Toast.makeText(MainActivity.this,
						"Nome Selecionado: " + nome, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});


		/*
			Criar o Spinner de Frutas
		 */
		// Obtendo o array de strings a partir dos recursos da aplicação (strings.xml)
		final String[] frutas = getResources().getStringArray(R.array.frutas);
		// Criando o Adaptador de dados para o Spinner
		ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_dropdown_item, frutas);
		arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spn2.setAdapter(arrayAdapter2);

		spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				Toast.makeText(MainActivity.this,
						"Fruta Selecionada: " + frutas[i], Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
			}
		});

	}
}
