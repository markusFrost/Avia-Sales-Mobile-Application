package avia.androi.innopolis.com.aviasales.history;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.models.Booking;
import avia.androi.innopolis.com.aviasales.models.City;
import avia.androi.innopolis.com.aviasales.models.Counter;
import avia.androi.innopolis.com.aviasales.models.Flight;
import avia.androi.innopolis.com.aviasales.utils.HelpUtils;
import avia.androi.innopolis.com.aviasales.view.BookingHistoryViewLoader;

public class BookingHistoryFragment extends Fragment implements IBookingHistoryView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containerViewGroup, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tickets, null);

        List<Booking> listBooking = new ArrayList<>();

        for (int i = 1; i <= 2; i++) {
            List<Flight> listFlightsTo = new ArrayList<>();
            List<Flight> listFlightsBack = new ArrayList<>();

            UUID guid = HelpUtils.generateGUID();

            for (int j = 1; j <= 1; j++) {

                City cityFrom = new City();
                cityFrom.setName("CityFrom " + i);

                City cityTo = new City();
                cityTo.setName("CityTo " + i);


                Flight flight = new Flight();
                flight.setCityFrom(cityFrom);
                flight.setCityTo(cityTo);
                flight.setDateArr(i * 5);
                flight.setDateDep(i);
                flight.setFreePlaceCount(i);
                flight.setPricePerTicket(i * 50 + 10);

                listFlightsTo.add(flight);

                listFlightsBack.add(flight);
            }

            Booking booking = new Booking();

            booking.setDateBook(System.currentTimeMillis());
            booking.setListFlightsTo(listFlightsTo);
            booking.setListFlightsBack(listFlightsBack);

            booking.setId(guid);

            listBooking.add(booking);
        }

        Counter index = new Counter();
        index.setCount(0);

        LinearLayout container = (LinearLayout) view.findViewById(R.id.search_container);

        BookingHistoryViewLoader loader = new BookingHistoryViewLoader(getActivity());

        loader.loadBookHistory(listBooking, container, index);

        return view;
    }

    @Override
    public void displayBookingHistoryList() {

    }

    @Override
    public void displayEmptyBookingHistoryList() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void setErrorMessage(int msgId) {

    }

    public static Fragment newInstance() {
        return new BookingHistoryFragment();
    }
}
