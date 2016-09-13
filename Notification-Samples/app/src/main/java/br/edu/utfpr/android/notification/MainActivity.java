package br.edu.utfpr.android.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
 * Notifications requer o SDK nível 11
 */
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button createNotification = (Button) findViewById(R.id.create_notification_button);
		
		criarNotificacao();
		
		createNotification.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				criarNotificacao(); 
			}
		});
		
		
	}
	
	
	@SuppressLint("NewApi")
	private void criarNotificacao() {
		
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.mipmap.ic_launcher)
		        .setContentTitle("My notification")
		        .setContentText("Hello World!");
		
		
		// Cria uma intenção explícita de qual a atividade será
		// aberta após abrir a notificação
		Intent resultIntent = new Intent(this, NotificationActivity.class);
		
		
		
		// Cria uma pilha de atividades artificial, para a atividade que
		// iniciou a notificação
		// Isto garante que a ação voltar da navegação a partir da Atividade
		// levará para fora de sua aplicação, na tela inicial
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(NotificationActivity.class);
		
		// Adiciona a intenção que inicia a Atividade para o topo da pilha
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		

		
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
		    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		Notification notification = mBuilder.build();
		
		notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;

		
		// mId allows you to update the notification later on.
		mNotificationManager.notify(R.string.app_name, notification);
		
	}
}