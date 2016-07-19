package avia.androi.innopolis.com.aviasales.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

public class ViewUtils {

    public static View createHelpView (Activity activity){

        View line = new View(activity);
        line.setLayoutParams(new ViewGroup.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT));

        return line;
    }
}
