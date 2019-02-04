package com.codetask.alfutaim.dailynewsfeed.core.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.codetask.alfutaim.dailynewsfeed.ApplicationContext;

public class NetworkConnectService {

    private Context context = ApplicationContext.getContext();
    public static boolean isConnectedFlag=false;

    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if(activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()){
            isConnectedFlag = true;
            return true;
        }

        return false;
    }
}
