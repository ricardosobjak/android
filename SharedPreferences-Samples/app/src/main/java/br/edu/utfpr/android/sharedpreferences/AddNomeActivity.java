package br.edu.utfpr.android.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddNomeActivity extends AppCompatActivity {

	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_nome);

		prefs = getSharedPreferences(MainActivity.APP_PREFS, MODE_PRIVATE);

		final EditText name = (EditText) findViewById(R.id.name_edit_text);
		Button saveButton = (Button) findViewById(R.id.add_name_button);

		saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String username = name.getEditableText().toString();
				Editor editor = prefs.edit();
				editor.putString(MainActivity.USERNAME_KEY, username);
				editor.commit();
				finish();
			}
		});
	}
}
