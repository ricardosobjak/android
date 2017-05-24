package br.edu.utfpr.md.appcostura.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        if(bundle != null) {

            Object[] pdus = (Object[])bundle.get("pdus");
            SmsMessage[] msgs = new SmsMessage[pdus.length];

            for(int i=0; i<pdus.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);

                String telefone = msgs[i].getOriginatingAddress();
                String mensagem = msgs[i].getMessageBody().toString();

                Toast.makeText(context, "O número "+telefone+" enviou a seguinte mensagem: "
                        +mensagem, Toast.LENGTH_LONG).show();

                Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(2000);
            }

        }
    }
}
