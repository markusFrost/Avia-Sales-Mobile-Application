package avia.androi.innopolis.com.aviasales.objects;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.widget.TextView;

import java.util.UUID;

import avia.androi.innopolis.com.aviasales.MainActivity;
import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.history.BookingHistoryFragment;

public class OnViewClickListner implements TextView.OnClickListener {
        Activity activity;

    public OnViewClickListner(Activity activity) {

        this.activity = activity;
    }

    @Override
    public void onClick(View view) {

        if (activity instanceof MainActivity) {

            MainActivity mainActivity = (MainActivity) activity;

            Fragment fragment = mainActivity.getFragment();

            if (fragment instanceof BookingHistoryFragment) {

                UUID bookingId = (UUID) view.getTag(R.id.search_button_book);

                ((BookingHistoryFragment) fragment).showUndoBookingDialog(bookingId);
            }
        }
    }
}
