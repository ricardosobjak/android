package br.edu.utfpr.md.appcostura;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btCadastro = (Button)findViewById(R.id.btCadastro);

        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(MainActivity.this,
                        CadastroListActivity.class)
                );
            }
        });

        // Obtendo as preferências do Aplicativo
        final SharedPreferences prefs = getSharedPreferences("appcostura", MODE_PRIVATE);

        final EditText edtNome = (EditText)findViewById(R.id.edtNome);
        final Button button = (Button) findViewById(R.id.button);

        edtNome.setText("Oi " + prefs.getString("nome", ""));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Armazenando uma preferência
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("nome", edtNome.getText().toString());
                editor.commit();

                Toast.makeText(MainActivity.this, "Nome salvo nas preferências do APP", Toast.LENGTH_LONG).show();
            }
        });


        System.out.println("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");

    }


}
