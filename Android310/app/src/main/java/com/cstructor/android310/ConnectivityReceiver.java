package com.cstructor.android310;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class ConnectivityReceiver extends BroadcastReceiver {
    public ConnectivityReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null){
            boolean isConnected = activeNetwork.isConnectedOrConnecting();

            Log.d("ConnectivityReceiver.isConnected", Boolean.toString(isConnected));

            boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;

            Log.d("ConnectivityReceiver.isWiFi", Boolean.toString(isWiFi));

            boolean isMobile = activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;

            Log.d("ConnectivityReceiver.isMobile", Boolean.toString(isMobile));
        }
        else{
            Log.d("ConnectivityReceiver.activeNetwork", "null");
        }

    }
}
