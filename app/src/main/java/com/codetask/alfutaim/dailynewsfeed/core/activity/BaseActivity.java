package com.codetask.alfutaim.dailynewsfeed.core.activity;

import android.support.v7.app.AppCompatActivity;

import com.codetask.alfutaim.dailynewsfeed.app.controller.AppNewsApiController;
import com.codetask.alfutaim.dailynewsfeed.core.service.NetworkConnectService;

public class BaseActivity extends AppCompatActivity {

    public BaseActivity(){
        setupNetworkAvailability();
    }

    public void setupNetworkAvailability() {
        NetworkConnectService networkConnectService = new NetworkConnectService();
        networkConnectService.isNetworkAvailable();
    }

    public AppNewsApiController getAppNewsApiController() {
        return new AppNewsApiController();
    }
}
