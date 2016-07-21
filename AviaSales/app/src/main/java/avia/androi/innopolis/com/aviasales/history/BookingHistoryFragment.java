package avia.androi.innopolis.com.aviasales.history;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import java.util.List;
import java.util.UUID;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.cancel.UndoBookingPresenter;
import avia.androi.innopolis.com.aviasales.models.Booking;
import avia.androi.innopolis.com.aviasales.models.Counter;
import avia.androi.innopolis.com.aviasales.objects.OnViewClickListner;
import avia.androi.innopolis.com.aviasales.utils.NetworkUtils;
import avia.androi.innopolis.com.aviasales.utils.ShPrefUtils;
import avia.androi.innopolis.com.aviasales.view.BookingHistoryViewLoader;

public class BookingHistoryFragment extends Fragment implements IBookingHistoryView {

    private List<Booking> listBooking;

    ProgressBar pb;
    ScrollView mScrollView;

    Counter index;
    LinearLayout container;

    OnViewClickListner listner;

    public BookingHistoryFragment(List<Booking> listBooking){

        this.listBooking = listBooking;
    }

    private UndoBookingPresenter undoBookingPresenter;

    public BookingHistoryFragment(){}


    private BookingHistoryPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containerViewGroup, Bundle savedInstanceState) {

        undoBookingPresenter = new UndoBookingPresenter(this);

        listner = new OnViewClickListner(getActivity());

        View view = inflater.inflate(R.layout.fragment_tickets, null);

        presenter = new BookingHistoryPresenter(this);

        mScrollView = (ScrollView) view.findViewById(R.id.search_scroll_view);

        pb = (ProgressBar) view.findViewById(R.id.search_progress_bar);
        hideProgressBar();

        index = new Counter();
        index.setCount(0);

        container = (LinearLayout) view.findViewById(R.id.search_container);



        if (listBooking != null && !listBooking.isEmpty()){

            displayBookingHistoryList(listBooking);
        }
        else if (!NetworkUtils.isConnected()) {

            listBooking = ShPrefUtils.getListBooking();
            displayBookingHistoryList(listBooking);
        }
        else {

            presenter.getBookingHistory();
        }



        return view;
    }

    @Override
    public void displayBookingHistoryList(List<Booking> listBooking) {

        BookingHistoryViewLoader loader = new BookingHistoryViewLoader(getActivity());
        loader.loadBookHistory(listBooking, container, index, listner);

        if (listBooking!= null || listBooking.size() == 0){

            container.removeAllViews();
        }
    }

    @Override
    public void showUndoBookingDialog(final UUID bookingId) {

        AlertDialog.Builder  ad = new AlertDialog.Builder(getActivity());
        ad.setMessage(R.string.book_shure_undo);
        ad.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                undoBookingPresenter.undoBooking(bookingId);
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


    @Override
    public void loadBookingHistoryFromCash() {


        listBooking = ShPrefUtils.getListBooking();
        displayBookingHistoryList(listBooking);
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
        return new BookingHistoryFragment();
    }

    public static Fragment newInstance(List<Booking> list) {

        return new BookingHistoryFragment(list);
    }
}
