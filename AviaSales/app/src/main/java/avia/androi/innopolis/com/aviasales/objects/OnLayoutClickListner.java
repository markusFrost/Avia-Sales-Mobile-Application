package avia.androi.innopolis.com.aviasales.objects;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

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

       /* String json = (String) view.getTag(R.id.action_settings);

        ViewItem item = AppContext.getGson().fromJson(json, ViewItem.class);*/

        ViewItem item = (ViewItem) view.getTag(R.id.action_settings);

       /* Toast.makeText(activity,flightId.toString(), Toast.LENGTH_SHORT).show();*/

        if (activity instanceof MainActivity){

            MainActivity mainActivity = (MainActivity) activity;

            Fragment fragment = mainActivity.getFragment();

            if (fragment instanceof TicketFragment){

                if (!item.isClicked()) {

                    if (item.getTripType() == Constants.STRAIGHT) {

                        ((TicketFragment) fragment).putIdsStraight(item.getListIds());
                    }else{

                        ((TicketFragment) fragment).putIdsSBack(item.getListIds());
                    }
                    ((TicketFragment) fragment).showButtonBuy();
                }
                else {

                    if (item.getTripType() == Constants.STRAIGHT) {

                        ((TicketFragment) fragment).deleteIdsStraight(item.getListIds());
                    }
                    else{

                        ((TicketFragment) fragment).deleteIdsBack(item.getListIds());
                    }
                    ((TicketFragment) fragment).hideButtonBuy();
                }
            }
        }

        if (item.isClicked()){

            List<View> listViews = item.getListViews();
            if (listViews != null && !listViews.isEmpty()){

                for (View v: listViews){

                    ViewItem viewItem = (ViewItem) v.getTag(R.id.action_settings);

                    viewItem.setClicked(false);

                    v.setTag(R.id.action_settings, viewItem);

                    v.setBackgroundResource(R.drawable.flight_item_selector);
                }
            }

           // view.setBackgroundResource(R.drawable.flight_item_selector);
            item.setClicked(false);
        }
        else{

            List<View> listViews = item.getListViews();
            if (listViews != null && !listViews.isEmpty()){

                for (View v: listViews){

                    ViewItem viewItem = (ViewItem) v.getTag(R.id.action_settings);

                    viewItem.setClicked(true);

                    v.setTag(R.id.action_settings, viewItem);

                    v.setBackgroundColor(Color.GREEN);
                }
            }

            //view.setBackgroundColor(Color.GREEN);
            item.setClicked(true);
        }

       // json = AppContext.getGson().toJson(item);

        view.setTag(R.id.action_settings, item);
    }
}
