package br.edu.utfpr.android.location;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


public class Permission {
    private Permission() {}

    public static boolean check(Activity activity, String permission, int code) {
        if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity, new String[]{permission}, code);

            return false;
        }
        else
            return true;
    }


}
