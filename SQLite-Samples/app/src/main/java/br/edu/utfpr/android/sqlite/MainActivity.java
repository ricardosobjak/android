package br.edu.utfpr.android.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.edu.utfpr.android.sqlite.db.NotesDAO;
import br.edu.utfpr.android.sqlite.modelo.Note;

public class MainActivity extends AppCompatActivity {

	private NotesDAO dao;

	private Toolbar toolbar;
	private ListView list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		list = (ListView) findViewById(android.R.id.list);
		setSupportActionBar(toolbar);

		final ActionBar actionBar = getSupportActionBar();

		dao = new NotesDAO(this);
		dao.open();


	}

	@Override
	protected void onResume() {
		dao.open();
		super.onResume();

		List<Note> notes = dao.getAll();

		ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this,
				android.R.layout.simple_list_item_1, notes);
		list.setAdapter(adapter);
		
	}

	@Override
	protected void onPause() {
		dao.close();
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.add_note) {
			Intent intent = new Intent(this, AddNoteActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}