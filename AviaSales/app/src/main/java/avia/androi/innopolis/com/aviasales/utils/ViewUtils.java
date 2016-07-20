package avia.androi.innopolis.com.aviasales.utils;

import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.models.Booking;
import avia.androi.innopolis.com.aviasales.models.Counter;
import avia.androi.innopolis.com.aviasales.models.Flight;

public class ViewUtils {

    public static View createHelpView (Activity activity){

        View line = new View(activity);
        line.setLayoutParams(new ViewGroup.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT));

        return line;
    }

    private static void createBhBookingName(Activity activity, Booking booking, LinearLayout container, Counter index){

        String msg = activity.getResources().getString(R.string.booking_number) +
                " " + booking.getBookingId().toString();

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

    public static void loadBookFlights (Activity activity, Booking booking, LinearLayout container, Counter index){

       createBhBookingName(activity, booking, container, index);

        for (Flight flight : booking.getListFlightsTo()){


        }
    }
}
