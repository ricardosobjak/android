package br.edu.utfpr.android.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;


public class ProgressDialogFragment extends DialogFragment {
	public static ProgressDialogFragment newInstance() {
		ProgressDialogFragment frag = new ProgressDialogFragment();
		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = new ProgressDialog(getActivity());
		dialog.setTitle(R.string.wait);
		return dialog;
	}

}
