package br.edu.utfpr.android.datepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerActivity extends AppCompatActivity {
	private TextView tvDisplayDate;
	private DatePicker dpResult;
	private Button btnChangeDate;

	private Calendar data = Calendar.getInstance();

	static final int DATE_DIALOG_ID = 999;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setCurrentDateOnView();
		addListenerOnButton();

	}

	// display current date
	public void setCurrentDateOnView() {

		tvDisplayDate = (TextView) findViewById(R.id.tvDate);
		dpResult = (DatePicker) findViewById(R.id.dpResult);

		// set current date into textview
		tvDisplayDate.setText(new StringBuilder()
				.append(data.get(Calendar.DAY_OF_MONTH)).append("/")
				.append(data.get(Calendar.MONTH) + 1).append("/")
				.append(data.get(Calendar.YEAR)));

		// set current date into datepicker
		dpResult.init(data.get(Calendar.YEAR), data.get(Calendar.MONTH),data.get(Calendar.DAY_OF_MONTH), null);
	}

	public void addListenerOnButton() {
		btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
		btnChangeDate.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener,
					data.get(Calendar.YEAR), data.get(Calendar.MONTH),
					data.get(Calendar.DAY_OF_MONTH));
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			data.set(selectedYear, selectedMonth, selectedDay, 0, 0, 0); 
			
			// set selected date into textview
			tvDisplayDate.setText(new StringBuilder()
					.append(data.get(Calendar.DAY_OF_MONTH)).append("/")
					.append(data.get(Calendar.MONTH) + 1).append("/")
					.append(data.get(Calendar.YEAR)));

			// set selected date into datepicker also
			dpResult.init(data.get(Calendar.YEAR), data.get(Calendar.MONTH),
					data.get(Calendar.DAY_OF_MONTH), null);

		}
	};

}