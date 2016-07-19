package avia.androi.innopolis.com.aviasales.view;

import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.models.Booking;
import avia.androi.innopolis.com.aviasales.models.City;
import avia.androi.innopolis.com.aviasales.models.Counter;
import avia.androi.innopolis.com.aviasales.utils.ViewUtils;

public class BookingHistoryViewLoader extends BaseFlightsLoader {

    private Activity activity;

    public BookingHistoryViewLoader(Activity activity){

        super(activity);
        this.activity = activity;
    }

    private void createBookingTitle(Booking booking, LinearLayout container, Counter index){

        String msg = activity.getResources().getString(R.string.booking_number) +
                " " + booking.getId().toString();

        View helpView = ViewUtils.createHelpView(activity);

        View viewTranspherType =  activity.getLayoutInflater().
                inflate(R.layout.view_transpher_type, null);

        TextView tv = (TextView)viewTranspherType.findViewById(R.id.search_tv_transfer_name);

        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        tv.setTextColor(Color.RED);
        tv.setText(msg);


        container.addView(viewTranspherType, index.getCount());
        index.increment();
        container.addView(helpView, index.getCount());
    }

    private void createCityDirection(City cityFrom, City cityTo,  LinearLayout container, Counter index){

        String msg = cityFrom.getName() + " from " + cityTo.getName();

        View helpView = ViewUtils.createHelpView(activity);

        View viewTranspherType =  activity.getLayoutInflater().
                inflate(R.layout.view_transpher_type, null);

        TextView tv = (TextView)viewTranspherType.findViewById(R.id.search_tv_transfer_name);

        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        tv.setTextColor(Color.GRAY);
        tv.setText(msg);


        container.addView(viewTranspherType, index.getCount());
        index.increment();
        container.addView(helpView, index.getCount());
    }

    public void loadBookHistory(List<Booking> list, LinearLayout container, Counter index){

        for (Booking booking : list){

            City cityFrom = booking.getListFlightsTo().get(0).getCityFrom();
            City cityTo = booking.getListFlightsTo().get(booking.getListFlightsTo().size() - 1).getCityFrom();

            createBookingTitle(booking, container, index);

            createCityDirection(cityFrom, cityTo, container, index);

            designTripWIthTranspers(booking.getListFlightsTo(), container, index);

            if (!booking.getListFlightsBack().isEmpty()) {

                createCityDirection(cityTo, cityFrom, container, index);

                designTripWIthTranspers(booking.getListFlightsBack(), container, index);
            }
        }
    }
}
