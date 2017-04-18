package avia.androi.innopolis.com.aviasales.cancel;

import java.util.UUID;

import avia.androi.innopolis.com.aviasales.history.IBookingHistoryView;
import avia.androi.innopolis.com.aviasales.models.responses.BookingRequest;
import avia.androi.innopolis.com.aviasales.models.responses.BookingResponse;
import avia.androi.innopolis.com.aviasales.utils.ShPrefUtils;

public class UndoBookingPresenter implements IUndoBookingPresenter {

    IBookingHistoryView iView;
    UndoBookingLoader mLoader;

    public UndoBookingPresenter(IBookingHistoryView view) {

        this.iView = view;

        mLoader = new UndoBookingLoader(this);
    }

    @Override
    public void undoBooking(UUID bookingId) {

        BookingRequest request = new BookingRequest();
        request.setUserId(ShPrefUtils.getUser().getId());
        request.setBookingId(bookingId);

        mLoader.load(request);

        iView.showProgressBar();
    }

    @Override
    public void onServerSuccess(Object object) {

        if (object instanceof BookingResponse){

            BookingResponse response = (BookingResponse) object;

            ShPrefUtils.setListBooking(response.getListBooking());

            iView.displayBookingHistoryList(response.getListBooking());

            iView.hideProgressBar();
        }
    }

    @Override
    public void onServerFail(String message) {

        iView.hideProgressBar();
    }

    @Override
    public void onConnectionFail() {

        iView.hideProgressBar();
    }
}
