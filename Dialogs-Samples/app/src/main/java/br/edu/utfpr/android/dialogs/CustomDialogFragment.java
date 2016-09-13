package br.edu.utfpr.android.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * DialogFragment requer no mínimo o SDK 11.
 */
public class CustomDialogFragment extends DialogFragment {

	public static CustomDialogFragment newInstance() {
		CustomDialogFragment frag = new CustomDialogFragment();
		return frag;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.custom_dialog, container, false);
		getDialog().setTitle(R.string.android_training);
		return v;
	}
}