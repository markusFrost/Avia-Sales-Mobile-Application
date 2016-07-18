package avia.androi.innopolis.com.aviasales.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.models.Flight;

public class ViewUtils {

    public static LinearLayout formTripWithoutTransphers(Activity activity, List<Flight> listFlight, LinearLayout container){

        int index = 1;

        View helpView = createHelpView(activity);

        View viewTranspherType =  activity.getLayoutInflater().
                inflate(R.layout.view_transpher_type, null);

        TextView tv = (TextView)viewTranspherType.findViewById(R.id.search_tv_transfer_name);
        tv.setText(R.string.trip_without_transpher);



        container.addView(viewTranspherType, index);
        container.addView(helpView, index + 1);

        index++;

        for (Flight flight : listFlight){

            View line = createHelpView(activity);

            View flight_view = activity.getLayoutInflater().
                    inflate(R.layout.view_flight_item, null);

            TextView tvCityFrom = (TextView) flight_view.findViewById(R.id.search_flight_city_to);
            TextView tvCityTo = (TextView) flight_view.findViewById(R.id.search_flight_city_from);

            TextView tvDateDep = (TextView) flight_view.findViewById(R.id.search_flight_date_dep);
            TextView tvDateArr = (TextView) flight_view.findViewById(R.id.search_flight_date_arr);

            TextView tvPricePerTicket = (TextView) flight_view.findViewById(R.id.search_flight_price_pert_ticket);
            TextView tvFreePlaceCount = (TextView) flight_view.findViewById(R.id.search_flight_free_place_count);

            tvCityFrom.setText(flight.getCityFrom().getName());
            tvCityTo.setText(flight.getCityTo().getName());

            tvDateDep.setText(flight.getDateDep() + "");
            tvDateArr.setText(flight.getDateArr() + "");


            tvPricePerTicket.setText(flight.getPricePerTicket() + "");
            tvFreePlaceCount.setText(flight.getFreePlaceCount() + "");

            container.addView(flight_view, index);
            container.addView(line, index + 1);

            index++;
        }

return container;

    }

    public static View createHelpView (Activity activity){

        View line = new View(activity);
        line.setLayoutParams(new ViewGroup.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT));

        return line;
    }
}
