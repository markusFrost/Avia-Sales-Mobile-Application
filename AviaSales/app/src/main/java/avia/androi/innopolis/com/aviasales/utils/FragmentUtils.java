package avia.androi.innopolis.com.aviasales.utils;

import android.app.Fragment;
import android.app.FragmentManager;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.base.BaseActivity;
import avia.androi.innopolis.com.aviasales.register.RegisterFragment;

public class FragmentUtils {

    public static void setFragment(Fragment fragment, BaseActivity activity){
        int id = 0;
        if (fragment instanceof RegisterFragment){
            id = R.string.registration;
        }

        if (id != 0) {
            activity.setTitle(id);
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }
}
