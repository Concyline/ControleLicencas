package br.com.controlelicencas;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;


public class Util {

    public static boolean verificaConexaoInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getImei(Context context){
        try {
            String deviceId = null;
            //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            //}else {
               // TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
               // deviceId = telephonyManager.getDeviceId();
            //}
            return deviceId.toUpperCase();
        }catch (Exception e){
            e.printStackTrace();

            return "not found";
        }
    }


}