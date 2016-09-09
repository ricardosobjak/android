package br.edu.utfpr.android.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import br.edu.utfpr.android.sqlite.db.NotesDAO;

public class AddNoteActivity extends AppCompatActivity {
	private NotesDAO dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note);

		dao = new NotesDAO(this);
		dao.open();

		Button saveButton = (Button) findViewById(R.id.save_note_button);
		final EditText noteText = (EditText) findViewById(R.id.note_text);

		saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String note = noteText.getEditableText().toString();
				dao.create(note);
				finish();
			}
		});
	}

	@Override
	protected void onResume() {
		dao.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		dao.close();
		super.onPause();
	}
}