package br.edu.utfpr.android.listview;

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

    public void openListView1(View v) {
        startActivity(new Intent(this, ListView1Activity.class));
    }

    public void openListView2(View v) {
        startActivity(new Intent(this, ListView2Activity.class));
    }
}
