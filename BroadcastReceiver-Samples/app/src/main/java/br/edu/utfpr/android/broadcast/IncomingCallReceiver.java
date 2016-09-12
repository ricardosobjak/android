package br.edu.utfpr.android.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Classe para receber eventos de Broadcast referente à chamadas telefônicas recebidas no Dispositivo.
 *
 */
public class IncomingCallReceiver extends BroadcastReceiver {
	
	private Context context;

	public void onReceive(Context context, Intent intent) {
		this.context = context;
		
		try {
			// Instância da classe TELEPHONY MANAGER, para registrar um listener
			TelephonyManager tmgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

			// Criando um Listener
			MyPhoneStateListener PhoneListener = new MyPhoneStateListener();

			// Registrando listener para LISTEN_CALL_STATE
			tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

		} catch (Exception e) {
			Log.e("Phone Receive Error", " " + e);
		}

	}

	private class MyPhoneStateListener extends PhoneStateListener {

		public void onCallStateChanged(int state, String incomingNumber) {
			Log.d("MyPhoneListener", state + "   incoming no:" + incomingNumber);

			if (state == 1) {
				String msg = "New Phone Call Event. Incomming Number : " + incomingNumber;
				int duration = Toast.LENGTH_LONG;
				Toast toast = Toast.makeText(context, msg, duration);
				toast.show();
			}
		}
	}
}