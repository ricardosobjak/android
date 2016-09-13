package br.edu.utfpr.android.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button progressButton = (Button)findViewById(R.id.show_progress_dialog_button);
		Button alertButton = (Button) findViewById(R.id.show_alert_dialog_button);
		Button customButton = (Button) findViewById(R.id.show_custom_dialog_button);
		
		Toast.makeText(this, "Bem Vindo", 100).show();

		progressButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment dialog = ProgressDialogFragment.newInstance();
				dialog.show(getFragmentManager(), "progress");
			}
		});

		alertButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment dialog = AlertDialogFragment.newInstance();
				dialog.show(getFragmentManager(), "alert");
			}
		});

		customButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment dialog = CustomDialogFragment.newInstance();
				dialog.show(getFragmentManager(), "custom");
			}
		});

	}

	





}