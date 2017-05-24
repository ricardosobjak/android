package br.edu.utfpr.md.appcostura.notification;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;

import br.edu.utfpr.md.appcostura.CadastroFormActivity;
import br.edu.utfpr.md.appcostura.Main2Activity;
import br.edu.utfpr.md.appcostura.R;

public class AniversariantesNotification {

    public static void create(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));

        builder.setContentTitle("Tem aniversariantes hoje!");
        builder.setContentText("As seguintes pessoas estão de aniversário hoje: ...");

        Intent resulIntent = new Intent(context, CadastroFormActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(Main2Activity.class);

        stackBuilder.addNextIntent(resulIntent);

        PendingIntent resulPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(resulPendingIntent);
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;

        manager.notify(1,notification); //Notifica (id, notificação)
    }
}