package avia.androi.innopolis.com.aviasales.search;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.models.City;
import avia.androi.innopolis.com.aviasales.models.Counter;
import avia.androi.innopolis.com.aviasales.models.Flight;
import avia.androi.innopolis.com.aviasales.utils.ViewUtils;

public class TicketFragment extends Fragment implements ITicketView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containerViewGroup, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tickets, null);

        View searchPanel = inflater.inflate(R.layout.view_search_panel, containerViewGroup, false);

        List<Flight> listFlight = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {


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

            listFlight.add(flight);
        }

        Counter index = new Counter();
        index.setCount(0);

        LinearLayout container = (LinearLayout) view.findViewById(R.id.search_container);

        View line = ViewUtils.createHelpView(getActivity());

        container.addView(searchPanel, index.getCount());
        index.increment();
        container.addView(line,index.getCount() );

       //ViewUtils.formTripWithoutTransphersInRightDirection(getActivity(), listFlight, container, index);
        ViewUtils.formTripWithoutTransphersInBackDirection(getActivity(), listFlight, container, index);

        List<List<Flight>> listList = new ArrayList<>();
        for (int i = 1; i <= 3; i++){

            listList.add(listFlight);
        }

       // ViewUtils.formTripWithTransphersInRightDirection(getActivity(), listList, container, index);
        ViewUtils.formTripWithTransphersInBackDirection(getActivity(), listList, container, index);

        return view;
    }

    @Override
    public void displayFlightsList(List<Flight> list) {

    }

    @Override
    public void displayEmptyFlightsList() {

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

        return new TicketFragment();
    }
}
