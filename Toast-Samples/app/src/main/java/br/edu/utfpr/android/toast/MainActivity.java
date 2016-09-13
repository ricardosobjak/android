package br.edu.utfpr.android.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openToastSimples(View v) {
        Toast.makeText(this, "Toast Simples", Toast.LENGTH_LONG).show();
    }

    public void openToastCustomizado(View v) {
        final MeuToast toast = new MeuToast(this);
        toast.show();
    }
}
