package avia.androi.innopolis.com.aviasales.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import avia.androi.innopolis.com.aviasales.objects.AppContext;

public class NetworkUtils {

    public static boolean isConnected(){

        ConnectivityManager cm =
                (ConnectivityManager) AppContext.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();

    }
}
