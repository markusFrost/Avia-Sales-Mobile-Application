package avia.androi.innopolis.com.aviasales.history;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.models.Booking;
import avia.androi.innopolis.com.aviasales.models.Counter;
import avia.androi.innopolis.com.aviasales.utils.ShPrefUtils;
import avia.androi.innopolis.com.aviasales.view.BookingHistoryViewLoader;

public class BookingHistoryFragment extends Fragment implements IBookingHistoryView {

    private List<Booking> listBooking;

    public BookingHistoryFragment(List<Booking> listBooking){

        this.listBooking = listBooking;
    }

    public BookingHistoryFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containerViewGroup, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tickets, null);

        Counter index = new Counter();
        index.setCount(0);

        LinearLayout container = (LinearLayout) view.findViewById(R.id.search_container);

        BookingHistoryViewLoader loader = new BookingHistoryViewLoader(getActivity());

        if (listBooking == null){

            listBooking = ShPrefUtils.getListBooking();
        }

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

    public static Fragment newInstance(List<Booking> list) {

        return new BookingHistoryFragment(list);
    }
}
