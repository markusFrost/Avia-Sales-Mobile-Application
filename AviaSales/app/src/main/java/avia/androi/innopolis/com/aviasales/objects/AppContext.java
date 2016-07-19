package avia.androi.innopolis.com.aviasales.objects;

import android.app.Application;

import com.google.gson.Gson;

public class AppContext extends Application {

    private static Gson mGson;

    @Override
    public void onCreate() {
        super.onCreate();

        mGson = new Gson();
    }

    public static Gson getGson() {
        return mGson;
    }
}
