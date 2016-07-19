package avia.androi.innopolis.com.aviasales.objects;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

public class AppContext extends Application {

    private static Gson mGson;

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mGson = new Gson();

        mContext = getApplicationContext();
    }

    public static Gson getGson() {
        return mGson;
    }

    public static Context getContext() {
        return mContext;
    }
}
