package com.codetask.alfutaim.dailynewsfeed.app.service;

import com.codetask.alfutaim.dailynewsfeed.core.service.NetworkConnectService;

public class AppNetworkConnectService extends NetworkConnectService{

    public boolean isConnected(){
        if(isConnectedFlag){
            return true;
        }


        if(isNetworkAvailable()){
            return true;
        }

        return false;
    }
}
