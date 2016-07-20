package avia.androi.innopolis.com.aviasales.search;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import java.util.List;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.base.BaseActivity;
import avia.androi.innopolis.com.aviasales.booking.BookingPresenter;
import avia.androi.innopolis.com.aviasales.history.BookingHistoryFragment;
import avia.androi.innopolis.com.aviasales.models.Booking;
import avia.androi.innopolis.com.aviasales.models.Counter;
import avia.androi.innopolis.com.aviasales.models.Flight;
import avia.androi.innopolis.com.aviasales.models.responses.FlightRequest;
import avia.androi.innopolis.com.aviasales.utils.FragmentUtils;
import avia.androi.innopolis.com.aviasales.utils.ViewUtils;
import avia.androi.innopolis.com.aviasales.view.FlightsInBackDirectionLoader;
import avia.androi.innopolis.com.aviasales.view.FlightsInRightDirectionLoader;

public class TicketFragment extends Fragment implements ITicketView {

    SearchPresenter seachPresenter;
    BookingPresenter bookingPresenter;

    View view;
    LinearLayout container;
    Counter index;
    ProgressBar pb;
    ScrollView mScrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containerViewGroup, Bundle savedInstanceState) {

        seachPresenter = new SearchPresenter(this);
        bookingPresenter = new BookingPresenter(this);

        view = inflater.inflate(R.layout.fragment_tickets, null);

        View searchPanel = inflater.inflate(R.layout.view_search_panel, containerViewGroup, false);

        index = new Counter();
        index.setCount(0);

        container = (LinearLayout) view.findViewById(R.id.search_container);

        View line = ViewUtils.createHelpView(getActivity());

        container.addView(searchPanel, index.getCount());
        index.increment();
        container.addView(line,index.getCount() );

        Button btnSearch = (Button) searchPanel.findViewById(R.id.search_button_ok);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FlightRequest request = new FlightRequest();

                request.setCityFrom("Moscow");
                request.setCityTo("Tokio");
                request.setDateDeparture(1468935531506L);
                request.setDateBackReturn(1469197800000L);
                request.setRoundTrip(true);

                seachPresenter.search(request);
            }
        });

        mScrollView = (ScrollView) view.findViewById(R.id.search_scroll_view);

        pb = (ProgressBar) view.findViewById(R.id.search_progress_bar);
        hideProgressBar();

        showAlertPlaceCount();

        return view;
    }



    @Override
    public void showProgressBar() {

        mScrollView.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {

        mScrollView.setVisibility(View.VISIBLE);
        pb.setVisibility(View.GONE);
    }

    @Override
    public void setErrorMessage(int msgId) {

    }

    public static Fragment newInstance() {

        return new TicketFragment();
    }

    @Override
    public void displayFlightsListNoTranspher(List<Flight> list) {

        FlightsInRightDirectionLoader loaderTo = new FlightsInRightDirectionLoader(getActivity());

        loaderTo.loadNoTransphers(list, container, index);
    }

    @Override
    public void displayEmptyFlightsListNoTranspher() {

    }

    @Override
    public void displayFlightsListTransphers(List<Flight> list) {

        FlightsInBackDirectionLoader loaderBack = new FlightsInBackDirectionLoader(getActivity());

        loaderBack.addTripsBackInfo(container, index);

        loaderBack.loadNoTransphers(list, container, index);

        bookingPresenter.book();
    }

    @Override
    public void displayEmptyFlightsListTransphers() {

    }

    @Override
    public void goToBookingHistory(List<Booking> listBooking) {

        Fragment fragment = BookingHistoryFragment.newInstance(listBooking);

        FragmentUtils.setFragment(fragment, (BaseActivity) getActivity());
    }

    @Override
    public void showAlertPlaceCount() {

        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setTitle(R.string.choose_place_count);

        String [] type = {"1", "2" , "3", "4" , "5" ,"6" , "7"};

        b.setItems(type, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

                int placeCount = which + 1;
            }

        });

        b.show();
    }
}
