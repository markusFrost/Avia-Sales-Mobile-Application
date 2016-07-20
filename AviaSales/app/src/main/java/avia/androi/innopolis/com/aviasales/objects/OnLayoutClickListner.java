package avia.androi.innopolis.com.aviasales.objects;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import avia.androi.innopolis.com.aviasales.MainActivity;
import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.models.ViewItem;
import avia.androi.innopolis.com.aviasales.search.TicketFragment;

public class OnLayoutClickListner implements LinearLayout.OnClickListener {
    Activity activity;

    public OnLayoutClickListner (Activity activity){

        this.activity = activity;
    }
    @Override
    public void onClick(View view) {

        String json = (String) view.getTag(R.id.action_settings);

        ViewItem item = AppContext.getGson().fromJson(json, ViewItem.class);

       /* Toast.makeText(activity,flightId.toString(), Toast.LENGTH_SHORT).show();*/

        if (activity instanceof MainActivity){

            MainActivity mainActivity = (MainActivity) activity;

            Fragment fragment = mainActivity.getFragment();

            if (fragment instanceof TicketFragment){

                if (item.isClicked()) {

                    ((TicketFragment) fragment).putIdsStraight(item.getListIds());
                    ((TicketFragment) fragment).hideButtonBuy();
                }
                else {

                    ((TicketFragment) fragment).deleteIdsStraight(item.getListIds());
                    ((TicketFragment) fragment).showButtonBuy();
                }
            }
        }

        if (item.isClicked()){

            view.setBackgroundResource(R.drawable.flight_item_selector);
            item.setClicked(false);
        }
        else{

            view.setBackgroundColor(Color.GREEN);
            item.setClicked(true);
        }

        json = AppContext.getGson().toJson(item);

        view.setTag(R.id.action_settings, json);
    }
}
