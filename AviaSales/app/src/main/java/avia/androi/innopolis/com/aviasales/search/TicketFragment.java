package avia.androi.innopolis.com.aviasales.search;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
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
import avia.androi.innopolis.com.aviasales.utils.TimeUtils;
import avia.androi.innopolis.com.aviasales.utils.ViewUtils;
import avia.androi.innopolis.com.aviasales.view.DatePickerBack;
import avia.androi.innopolis.com.aviasales.view.DatePickerTo;
import avia.androi.innopolis.com.aviasales.view.FlightsInBackDirectionLoader;
import avia.androi.innopolis.com.aviasales.view.FlightsInStraightDirectionLoader;

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

    private LinearLayout searchLayoutTo;
    private LinearLayout searchLayoutBack;
    View searchPanel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containerViewGroup, Bundle savedInstanceState) {

        seachPresenter = new SearchPresenter(this);
        bookingPresenter = new BookingPresenter(this);

        listner = new OnLayoutClickListner(getActivity());

        view = inflater.inflate(R.layout.fragment_tickets, null);

        searchPanel = inflater.inflate(R.layout.view_search_panel, containerViewGroup, false);

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

        addSearchPanel();

        Button btnSearch = (Button) searchPanel.findViewById(R.id.search_button_ok);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                formDate();
            }
        });

        mScrollView = (ScrollView) view.findViewById(R.id.search_scroll_view);

        pb = (ProgressBar) view.findViewById(R.id.search_progress_bar);
        hideProgressBar();

        initializeViews(view);

        //showAlertPlaceCount();

        return view;
    }



    private boolean validateDate(){

        String cityFrom = editCityFrom.getText().toString();

        if (cityFrom == null || cityFrom.isEmpty()){

            return false;
        }

        String cityTo = editCityTo.getText().toString();

        if (cityTo == null || cityTo.isEmpty()){

            return false;
        }

        long timeTo = TimeUtils.convertStringToMills(tvDateTo.getText().toString());

        if (timeTo == 0){

            return false;
        }

        if (cb.isChecked()){

            long timeBack = TimeUtils.convertStringToMills(tvDateBack.getText().toString());

            if (timeBack == 0){

                return false;
            }
        }
        return true;

    }

    private void addSearchPanel() {

        index.setCount(0);
        View line = ViewUtils.createHelpView(getActivity());

        container.addView(searchPanel, index.getCount());
        index.increment();
        container.addView(line,index.getCount() );


        if (request != null){

            if (request.getCityFrom() != null){

                editCityFrom.setText(request.getCityFrom());
            }
            if (request.getCityTo() != null){

                editCityTo.setText(request.getCityTo());
            }

            if (request.getDateDeparture() != 0){

                tvDateTo.setText(TimeUtils.convertMillsToStringDate(request.getDateDeparture()));
            }

            if (request.isRoundTrip()){

                cb.setChecked(true);

                if (request.getDateBackReturn() != 0){

                    tvDateTo.setText(TimeUtils.convertMillsToStringDate(request.getDateBackReturn()));
                }
            }
            else{

                cb.setChecked(false);
            }
        }
    }

    private TextView tvDateTo;

    private TextView tvDateBack;

    private EditText editCityFrom, editCityTo;

    private CheckBox cb;

    private void initializeViews(View v) {

        searchLayoutTo = (LinearLayout) v.findViewById(R.id.search_ll_to);
        searchLayoutBack = (LinearLayout) v.findViewById(R.id.search_ll_back);

        editCityFrom = (EditText) v.findViewById(R.id.searchEditCityFrom);
        editCityTo = (EditText) v.findViewById(R.id.searchEditCityTo);

        tvDateTo = (TextView) v.findViewById(R.id.search_date_dep_to);
        tvDateBack = (TextView) v.findViewById(R.id.search_date_dep_back);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        setDateDepTo(year, month, day);
        setDateDepBack(year, month, day);

        searchLayoutTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment dateDialog = new DatePickerTo();
                dateDialog.show(((MainActivity)getActivity()).getSupportFragmentManager(), "datePickerTo");
            }
        });

        searchLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment dateDialog = new DatePickerBack();
                dateDialog.show(((MainActivity)getActivity()).getSupportFragmentManager(), "datePickerBack");
            }
        });


        cb = (CheckBox) v.findViewById(R.id.search_cv_flight_back);

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b){

                    searchLayoutBack.setVisibility(View.VISIBLE);
                }
                else{

                    searchLayoutBack.setVisibility(View.GONE);
                }
            }
        });
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
    public void showNotTrasph(List<Flight> list) {

        FlightsInStraightDirectionLoader loaderTo = new FlightsInStraightDirectionLoader(getActivity());

        loaderTo.loadNoTransphers(list, container, index, listner, Constants.STRAIGHT);
    }

    @Override
    public void showEmptyFlights() {

        View emptyView = getActivity().getLayoutInflater().inflate(R.layout.view_empty_result, null);

        View line = ViewUtils.createHelpView(getActivity());

        container.addView(emptyView, index.getCount());
        index.increment();
        container.addView(line,index.getCount() );
    }

    @Override
    public void showNoTrasphBack(List<Flight> listBackTranphers) {

        FlightsInBackDirectionLoader loaderBack = new FlightsInBackDirectionLoader(getActivity());

        loaderBack.addTripsBackInfo(container, index);

        loaderBack.loadNoTransphers(listBackTranphers, container, index, listner, Constants.BACK);
    }

    @Override
    public void showWithTrasph(List<List<Flight>> listTo) {

        FlightsInStraightDirectionLoader loaderTo = new FlightsInStraightDirectionLoader(getActivity());

        loaderTo.loadWithTransphers (listTo, container, index, listner, Constants.STRAIGHT);
    }

    @Override
    public void showWithTrasphBack(List<List<Flight>> listBack) {

        FlightsInBackDirectionLoader loaderBack = new FlightsInBackDirectionLoader(getActivity());

        loaderBack.addTripsBackInfo(container, index);

        loaderBack.loadWithTransphers(listBack, container, index, listner, Constants.BACK);
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

    public void setDateDepTo(int year,int month, int day){

        month++;
        String msg = "";

        if (day < 10){

            msg += "0" + day + ".";
        }
        else {
            msg +=  day + ".";
        }
        if (month < 10){

            msg += "0" + month + ".";
        }
        else{
            msg += month + ".";
        }
        msg += year;

        tvDateTo.setText(msg);
    }

    public void setDateDepBack(int year,int month, int day){

        month++;
        String msg = "";

        if (day < 10){

            msg += "0" + day + ".";
        }
        else {
            msg +=  day + ".";
        }
        if (month < 10){

            msg += "0" + month + ".";
        }
        else{
            msg += month + ".";
        }
        msg += year;

        tvDateBack.setText(msg);
    }

    FlightRequest request;
    private void formDate(){

       /* FlightRequest request = new FlightRequest();

        request.setCityFrom(editCityFrom.getText().toString());
        request.setCityTo(editCityTo.getText().toString());
        request.setDateDeparture(TimeUtils.convertStringToMills(tvDateTo.getText().toString()));
        request.setDateBackReturn(TimeUtils.convertStringToMills(tvDateBack.getText().toString()));
        request.setRoundTrip(cb.isChecked());

        request.setDateDeparture(1468935531506L);*/

       // if (validateDate()) {

           /* request = new FlightRequest();

            request.setCityFrom("Moscow");
            request.setCityTo("Tokio");
            request.setDateDeparture(1468935531506L);
            request.setDateBackReturn(1469197800000L);
            request.setRoundTrip(true);*/

         request = new FlightRequest();

        request.setCityFrom("Volgograd");
        request.setCityTo("Kazan");
        request.setDateDeparture(1469110200000L);

            container.removeAllViews();
            addSearchPanel();

            seachPresenter.search(request);
        //}

    }



}
