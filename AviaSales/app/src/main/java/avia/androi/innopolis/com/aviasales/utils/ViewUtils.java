package avia.androi.innopolis.com.aviasales.utils;

import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.models.Counter;
import avia.androi.innopolis.com.aviasales.models.Flight;

public class ViewUtils {

    public static final int FLIGHT_ID = 123;
    public static final int FLIGHTS_IDS = 574954;

    public static void formTripWithoutTransphersInRightDirection(Activity activity, List<Flight> listFlight, LinearLayout container, Counter index){

        View helpView = createHelpView(activity);

        View viewTranspherType =  activity.getLayoutInflater().
                inflate(R.layout.view_transpher_type, null);

        TextView tv = (TextView)viewTranspherType.findViewById(R.id.search_tv_transfer_name);
        tv.setText(R.string.trip_without_transpher);



        container.addView(viewTranspherType, index.getCount());
        index.increment();
        container.addView(helpView, index.getCount());

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

            //flight_view.setTag(FLIGHT_ID, flight.getId());

            container.addView(flight_view, index.getCount());
            index.increment();
            container.addView(line, index.getCount());
        }
    }

    public static void formTripWithoutTransphersInBackDirection(Activity activity, List<Flight> listFlight, LinearLayout container, Counter index){

        String topMsg = activity.getResources().getString(R.string.trips_back) + "\n\n"
                + activity.getResources().getString(R.string.trip_without_transpher);
        View helpView = createHelpView(activity);

        View viewTranspherType =  activity.getLayoutInflater().
                inflate(R.layout.view_transpher_type, null);

        TextView tv = (TextView)viewTranspherType.findViewById(R.id.search_tv_transfer_name);
        tv.setText(topMsg);


        container.addView(viewTranspherType, index.getCount());
        index.increment();
        container.addView(helpView, index.getCount());

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

            //flight_view.setTag(FLIGHT_ID, flight.getId());

            container.addView(flight_view, index.getCount());
            index.increment();
            container.addView(line, index.getCount());
        }
    }

    public static void formTripWithTransphersInRightDirection(Activity activity, List<List<Flight>> listFlight, LinearLayout container, Counter index){

        View helpView = createHelpView(activity);

        View viewTranspherType =  activity.getLayoutInflater().
                inflate(R.layout.view_transpher_type, null);

        TextView tv = (TextView)viewTranspherType.findViewById(R.id.search_tv_transfer_name);
        tv.setText(R.string.trip_with_transpher);

        container.addView(viewTranspherType, index.getCount());
        index.increment();
        container.addView(helpView, index.getCount());

        int ticketsCount = 1;

        for (List<Flight> list : listFlight){

            View line = createHelpView(activity);

            View ticketsWithTraspherView =  activity.getLayoutInflater().
                    inflate(R.layout.view_transpher_type, null);

            TextView tvName = (TextView) ticketsWithTraspherView.
                    findViewById(R.id.search_tv_transfer_name);

            tvName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            tvName.setTextColor(Color.RED);

            tvName.setText(activity.getResources().getString(R.string.ticket_number) + ticketsCount);

            container.addView(ticketsWithTraspherView, index.getCount());
            index.increment();
            container.addView(line, index.getCount());

            designTripWIthTranspers(activity, list, container, index);

            ticketsCount++;
        }
    }

    public static void formTripWithTransphersInBackDirection(Activity activity, List<List<Flight>> listFlight, LinearLayout container, Counter index){

        View helpView = createHelpView(activity);

        View viewTranspherType =  activity.getLayoutInflater().
                inflate(R.layout.view_transpher_type, null);

        TextView tv = (TextView)viewTranspherType.findViewById(R.id.search_tv_transfer_name);
        tv.setText(R.string.trip_with_transpher);

        container.addView(viewTranspherType, index.getCount());
        index.increment();
        container.addView(helpView, index.getCount());

        int ticketsCount = 1;

        for (List<Flight> list : listFlight){

            View line = createHelpView(activity);

            View ticketsWithTraspherView =  activity.getLayoutInflater().
                    inflate(R.layout.view_transpher_type, null);

            TextView tvName = (TextView) ticketsWithTraspherView.
                    findViewById(R.id.search_tv_transfer_name);

            tvName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            tvName.setTextColor(Color.RED);

            tvName.setText(activity.getResources().getString(R.string.ticket_number) + ticketsCount);

            container.addView(ticketsWithTraspherView, index.getCount());
            index.increment();
            container.addView(line, index.getCount());

            designTripWIthTranspers(activity, list, container, index);

            ticketsCount++;
        }
    }




    private static void designTripWIthTranspers(Activity activity, List<Flight> listFlights, LinearLayout container, Counter index){

        for (int i = 0; i < listFlights.size(); i++){

            Flight flight = listFlights.get(i);

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

            LinearLayout topDivider = (LinearLayout) flight_view.findViewById(R.id.search_flight_top_divider);
            topDivider.setVisibility(View.GONE);

            LinearLayout bottomDivider = (LinearLayout) flight_view.findViewById(R.id.search_flight_bottom_divider);
            topDivider.setVisibility(View.VISIBLE);

            if (i == 0){

                topDivider.setBackgroundColor(Color.RED);

                ViewGroup.LayoutParams layoutParams = topDivider.getLayoutParams();

                layoutParams.height = 5;
            }
            else if ( i == listFlights.size() - 1){

                bottomDivider.setBackgroundColor(Color.RED);

                topDivider.setVisibility(View.GONE);

                ViewGroup.LayoutParams layoutParams = bottomDivider.getLayoutParams();

                layoutParams.height = 5;
            }
            else {

                bottomDivider.setBackgroundColor(Color.GRAY);

                topDivider.setVisibility(View.GONE);

                ViewGroup.LayoutParams layoutParams = topDivider.getLayoutParams();

                layoutParams.height = 1;
            }

          //  flight_view.setTag(FLIGHTS_IDS, HelpUtils.getListIds(listFlights));

            container.addView(flight_view, index.getCount());
            index.increment();
            container.addView(line, index.getCount());
        }
    }

    public static View createHelpView (Activity activity){

        View line = new View(activity);
        line.setLayoutParams(new ViewGroup.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT));

        return line;
    }
}
