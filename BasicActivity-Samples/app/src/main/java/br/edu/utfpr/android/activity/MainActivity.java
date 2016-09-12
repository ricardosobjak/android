package br.edu.utfpr.android.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Método onCreate");
		
		setContentView(R.layout.activity_main);
		
		
		Button btPrincipal = (Button)findViewById(R.id.bt_principal);
		
		btPrincipal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView tv = (TextView) findViewById(R.id.txv_texto);
				tv.setText(R.string.mensagem);
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("Método onPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("Método onStop");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("Método onResume");
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println("Método onStart");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		System.out.println("Método onRestart");
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("Método onDestroy");
	}
	
}
