package avia.androi.innopolis.com.aviasales.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import avia.androi.innopolis.com.aviasales.models.Booking;
import avia.androi.innopolis.com.aviasales.models.User;
import avia.androi.innopolis.com.aviasales.objects.AppContext;
import avia.androi.innopolis.com.aviasales.objects.Constants;

public class ShPrefUtils {

    private static String FILE_NAME = "AviaSales";

    public static void setUser(User user) {

        Context context = AppContext.getContext();

        SharedPreferences sd = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sd.edit();

        if (user != null) {

            String json = AppContext.getGson().toJson(user);

            editor.putString(Constants.KEY_USER, json);
        }
        else{

            editor.putString(Constants.KEY_USER, null);
        }
        editor.commit();
    }

    public static User getUser() {

        Context context = AppContext.getContext();

        SharedPreferences sd = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        String json =  sd.getString(Constants.KEY_USER, null);

        User user = AppContext.getGson().fromJson(json, User.class);

        return user;

    }

    public static void setListBooking(List<Booking> list) {

        Context context = AppContext.getContext();

        SharedPreferences sd = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sd.edit();

        if (list == null){
            editor.putString(Constants.KEY_BOOKING_LIST, null);
        }
        else {

            String json = AppContext.getGson().toJson(list);

            editor.putString(Constants.KEY_BOOKING_LIST, json);
        }
        editor.commit();
    }

    public static List<Booking> getListBooking() {

        Context context = AppContext.getContext();

        SharedPreferences sd = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        String json =  sd.getString(Constants.KEY_BOOKING_LIST, null);

        Type listType = new TypeToken<List<Booking>>(){}.getType();

        List<Booking> list = AppContext.getGson().fromJson(json,listType );

        return list;

    }
}
