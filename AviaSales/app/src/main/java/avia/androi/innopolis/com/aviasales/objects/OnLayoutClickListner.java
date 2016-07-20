package avia.androi.innopolis.com.aviasales.objects;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import avia.androi.innopolis.com.aviasales.MainActivity;
import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.search.TicketFragment;

public class OnLayoutClickListner implements LinearLayout.OnClickListener {
    Activity activity;

    public OnLayoutClickListner (Activity activity){

        this.activity = activity;
    }
    @Override
    public void onClick(View view) {

        UUID flightId = (UUID) view.getTag(R.id.action_settings);

        List<UUID> list = new ArrayList<>();
        list.add(flightId);
       /* Toast.makeText(activity,flightId.toString(), Toast.LENGTH_SHORT).show();*/

        if (activity instanceof MainActivity){

            MainActivity mainActivity = (MainActivity) activity;

            Fragment fragment = mainActivity.getFragment();

            if (fragment instanceof TicketFragment){

                ((TicketFragment) fragment).showAlertPlaceCount(list);
            }
        }
    }
}
