package br.edu.utfpr.android.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class AlertDialogFragment extends DialogFragment {
	
	public static AlertDialogFragment newInstance() {
		AlertDialogFragment frag = new AlertDialogFragment();
		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
		dialog.setTitle(getActivity().getString(R.string.attention));
		dialog.setMessage(getActivity().getString(R.string.which_button_gonna_press));
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, getActivity().getString(R.string.yes),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getActivity(), R.string.pressed_yes,	Toast.LENGTH_SHORT).show();
					}
				});
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getActivity().getString(R.string.no),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getActivity(), R.string.pressed_no,
								Toast.LENGTH_SHORT).show();
					}
				});
		return dialog;
	}
}
