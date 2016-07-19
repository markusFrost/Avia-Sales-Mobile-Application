package avia.androi.innopolis.com.aviasales.utils;

import android.content.Context;
import android.content.SharedPreferences;

import avia.androi.innopolis.com.aviasales.models.User;
import avia.androi.innopolis.com.aviasales.objects.Constants;

public class ShPrefUtils {
    
    private static String FILE_NAME = "AviaSales";

    public static void setUser(Context context, User user) {
        SharedPreferences sd = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sd.edit();

        String json = "";

        editor.putString(Constants.KEY_USER, json);
        editor.commit();
    }

    public static User getUser(Context context) {

        SharedPreferences sd = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        String json =  sd.getString(Constants.KEY_USER, null);

        User user = new User();

        return user;

    }
}
