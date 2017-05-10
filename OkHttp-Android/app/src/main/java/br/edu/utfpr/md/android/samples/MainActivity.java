package br.edu.utfpr.md.android.samples;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Modificando a política de thread, para permitir fazer a requisição na
         * Thread principal do APP, independente do tempo de execução.
         */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        /*
            Requisição com a biblioteca OkHttp
         */
        WebClientOkHttp client = new WebClientOkHttp();

        try {
            TextView textView = (TextView)findViewById(R.id.result);
            textView.setText(client.get("http://desen.md.utfpr.edu.br/sobjak/webapp-vraptor-costura/api/pessoa/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

