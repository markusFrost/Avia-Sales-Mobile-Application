package avia.androi.innopolis.com.aviasales.utils;

import android.app.Fragment;
import android.app.FragmentManager;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.base.BaseActivity;
import avia.androi.innopolis.com.aviasales.history.BookingHistoryFragment;
import avia.androi.innopolis.com.aviasales.login.LoginFragment;
import avia.androi.innopolis.com.aviasales.register.RegisterFragment;
import avia.androi.innopolis.com.aviasales.search.TicketFragment;

public class FragmentUtils {

    public static void setFragment(Fragment fragment, BaseActivity activity){
        int id = 0;
        if (fragment instanceof RegisterFragment){
            id = R.string.registration;
        }
        else if (fragment instanceof LoginFragment){
            id = R.string.authorisation;
        }
        else if (fragment instanceof TicketFragment){

            id = R.string.search_tickets;
        }
        else if (fragment instanceof BookingHistoryFragment){

            id = R.string.booking_history;
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
