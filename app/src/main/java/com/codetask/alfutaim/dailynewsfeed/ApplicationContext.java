package com.codetask.alfutaim.dailynewsfeed;

import android.app.Application;
import android.content.Context;

public class ApplicationContext extends Application {
    /**
     * Keeps a reference of the application context
     */
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

    }

    /**
     * Returns the application context
     *
     * @return application context
     */
    public static Context getContext() {
        return context;
    }
}
