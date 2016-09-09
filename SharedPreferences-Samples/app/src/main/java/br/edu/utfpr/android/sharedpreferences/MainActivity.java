package br.edu.utfpr.android.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	final static String APP_PREFS = "app_prefs";
	final static String USERNAME_KEY = "username";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();

		SharedPreferences prefs = getSharedPreferences(APP_PREFS, MODE_PRIVATE);
		String username = prefs.getString(USERNAME_KEY, null);

		TextView message = (TextView)findViewById(R.id.welcome_message);
		Button addNameButton = (Button)findViewById(R.id.add_name_button);

		if (username != null) {
			message.setText("Bem vindo, " + username + "!");
			addNameButton.setText("Trocar de nome");
		} else {
			message.setText("Você não cadastrou seu nome... ");
			addNameButton.setText("Adicionar nome");
		}

		addNameButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AddNomeActivity.class);
				startActivity(intent);
			}
		});
	}
}
