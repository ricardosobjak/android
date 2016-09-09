package br.edu.utfpr.android.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Explicita3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicita3);
    }

    @Override
    public void onBackPressed() {
        // Passando os dados de retorno para a outra atividade
        Intent i = new Intent();
        i.putExtra("mensagem", "Olá Atividade! Esta é minha mensagem de retorno");
        setResult(RESULT_OK, i);

        finish();
    }
}
