package br.edu.utfpr.android.intents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Explicita2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicita2);

        // Obtendo o pacote de extras, onde estão os parâmetros passados
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String nome = extras.getString("nome");
            int idade = extras.getInt("idade");

            LinearLayout layout = (LinearLayout) findViewById(R.id.layout_explicita2);

            TextView tv1 = new TextView(this);
            tv1.setText(getString(R.string.nome) + ": " + nome);

            TextView tv2 = new TextView(this);
            tv2.setText(getString(R.string.idade)+": "+String.valueOf(idade));

            layout.addView(tv1);
            layout.addView(tv2);
        }
    }
}