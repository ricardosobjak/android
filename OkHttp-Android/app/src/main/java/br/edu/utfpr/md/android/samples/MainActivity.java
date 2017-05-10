package br.edu.utfpr.md.android.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebClient client = new WebClient();

        try {
            System.out.println(client.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
