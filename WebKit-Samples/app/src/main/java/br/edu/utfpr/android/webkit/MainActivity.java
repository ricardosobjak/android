package br.edu.utfpr.android.webkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openDemo1(View v) {
        startActivity(new Intent(this, Browser1Activity.class));
    }
    public void openDemo2(View v) {
        startActivity(new Intent(this, Browser2Activity.class));
    }
    public void openDemo3(View v) {
        startActivity(new Intent(this, Browser3Activity.class));
    }
}
