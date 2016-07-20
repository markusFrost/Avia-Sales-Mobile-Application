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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import avia.androi.innopolis.com.aviasales.MainActivity;
import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.booking.BookingPresenter;
import avia.androi.innopolis.com.aviasales.history.BookingHistoryFragment;
import avia.androi.innopolis.com.aviasales.models.Booking;
import avia.androi.innopolis.com.aviasales.models.Counter;
import avia.androi.innopolis.com.aviasales.models.Flight;
import avia.androi.innopolis.com.aviasales.models.responses.FlightRequest;
import avia.androi.innopolis.com.aviasales.objects.Constants;
import avia.androi.innopolis.com.aviasales.objects.OnLayoutClickListner;
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

    OnLayoutClickListner listner;

    private Button btnBook;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containerViewGroup, Bundle savedInstanceState) {

        seachPresenter = new SearchPresenter(this);
        bookingPresenter = new BookingPresenter(this);

        listner = new OnLayoutClickListner(getActivity());

        view = inflater.inflate(R.layout.fragment_tickets, null);

        View searchPanel = inflater.inflate(R.layout.view_search_panel, containerViewGroup, false);

        btnBook = (Button) searchPanel.findViewById(R.id.search_button_book);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertPlaceCount();
            }
        });

        hideButtonBuy();

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
                request.setRoundTrip(false);

                seachPresenter.search(request);
            }
        });

        mScrollView = (ScrollView) view.findViewById(R.id.search_scroll_view);

        pb = (ProgressBar) view.findViewById(R.id.search_progress_bar);
        hideProgressBar();

        //showAlertPlaceCount();

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
    public void display___Flights__No__Tranpher__With__Full___Straight(List<Flight> list) {

        FlightsInRightDirectionLoader loaderTo = new FlightsInRightDirectionLoader(getActivity());

        loaderTo.loadNoTransphers(list, container, index, listner, Constants.STRAIGHT);
    }

    @Override
    public void display__Flights__NO___Transpher__Empty___Straight() {

    }

    @Override
    public void display__Flights__No___Transphers__Full___Back(List<Flight> list) {

        FlightsInBackDirectionLoader loaderBack = new FlightsInBackDirectionLoader(getActivity());

        loaderBack.addTripsBackInfo(container, index);

        loaderBack.loadNoTransphers(list, container, index, listner, Constants.BACK);

    }

    @Override
    public void display__Flights__No___Transphers__Empty___Back() {

    }

    @Override
    public void goToBookingHistory(List<Booking> listBooking) {

        Fragment fragment = BookingHistoryFragment.newInstance(listBooking);

        if (getActivity() instanceof MainActivity){

            MainActivity mainActivity = (MainActivity) getActivity();

            mainActivity.showFragment(fragment);
        }
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

                setPlaceCount( which + 1 );

                showAlertShure();
            }

        });

        b.show();
    }

    private void showAlertShure() {

        AlertDialog.Builder  ad = new AlertDialog.Builder(getActivity());
        ad.setMessage(R.string.book_shure);
        ad.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                bookingPresenter.book(listIdsStraight, listIdsBack, placeCount);
            }
        });

        ad.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        ad.setCancelable(false);
        ad.show();
    }


    private List<UUID> listIdsStraight = new ArrayList<>();

    private List<UUID> listIdsBack = new ArrayList<>();

    private int placeCount;


    public void putIdsStraight(List<UUID> list){

        listIdsStraight.addAll(list);
    }

    public void deleteIdsStraight(List<UUID> list){

        listIdsStraight.removeAll(list);
    }

    public void putIdsSBack(List<UUID> list){

        listIdsBack.addAll(list);
    }

    public void deleteIdsBack(List<UUID> list){

        listIdsBack.removeAll(list);
    }

    public void setPlaceCount(int count){

        placeCount = count;
    }

    public void showButtonBuy(){

        btnBook.setVisibility(View.VISIBLE);
    }

    public void hideButtonBuy(){

        if (listIdsStraight.size() == 0) {

            btnBook.setVisibility(View.GONE);
        }
    }

}
