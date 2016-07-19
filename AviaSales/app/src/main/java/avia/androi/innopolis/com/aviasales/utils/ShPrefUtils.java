package avia.androi.innopolis.com.aviasales.utils;

import android.content.Context;
import android.content.SharedPreferences;

import avia.androi.innopolis.com.aviasales.models.User;
import avia.androi.innopolis.com.aviasales.objects.AppContext;
import avia.androi.innopolis.com.aviasales.objects.Constants;

public class ShPrefUtils {

    private static String FILE_NAME = "AviaSales";

    public static void setUser(User user) {

        Context context = AppContext.getContext();

        SharedPreferences sd = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sd.edit();

        String json = AppContext.getGson().toJson(user);

        editor.putString(Constants.KEY_USER, json);
        editor.commit();
    }

    public static User getUser() {

        Context context = AppContext.getContext();

        SharedPreferences sd = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        String json =  sd.getString(Constants.KEY_USER, null);

        User user = AppContext.getGson().fromJson(json, User.class);

        return null;

    }
}
