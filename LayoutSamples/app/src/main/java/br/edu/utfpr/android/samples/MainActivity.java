package br.edu.utfpr.android.samples;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //...
    }

    public void openTableLayout(View v) {
        startActivity(new Intent(this, TableLayoutActivity.class));
    }
    public void openLinearLayoutHorizontal(View v) {
        startActivity(new Intent(this, LinearLayoutHorizontalActivity.class));
    }
    public void openTableLayoutVertical(View v) {
        startActivity(new Intent(this, LinearLayoutVerticalActivity.class));
    }
    public void openFrameLayout(View v) {
        startActivity(new Intent(this, FrameLayoutActivity.class));
    }
    public void openRelativeLayout(View v) {
        startActivity(new Intent(this, RelativeLayoutActivity.class));
    }
    public void openGridLayout(View v) {
        startActivity(new Intent(this, GridLayoutActivity.class));
    }
}
