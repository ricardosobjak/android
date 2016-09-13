package br.edu.utfpr.android.toast;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MeuToast extends Toast {

	public MeuToast(Activity parent) {
		super(parent);
		
		LayoutInflater inflater = LayoutInflater.from(parent);
		//LayoutInflater inflater = (LayoutInflater) parent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//LayoutInflater inflater = parent.getLayoutInflater();
		
		View layout = inflater.inflate(R.layout.toast_layout,
				(ViewGroup) parent.findViewById(R.id.toast_layout_root));
		

		ImageView image = (ImageView) layout.findViewById(R.id.image);
		image.setImageResource(R.mipmap.ic_launcher);
		
		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText("Olá, este é um exemplo de Toast personalizado");

		this.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		this.setDuration(Toast.LENGTH_LONG);
		this.setView(layout);
	}

}
