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
    public void undoBooking() {

        BookingRequest request = new BookingRequest();
        request.setUserId(UUID.fromString("99a3c93a-25f2-4dc4-802c-e17f290df021"));
        request.setBookingId(UUID.fromString("0289e81d-3347-409d-bc4f-fe236157a30f"));

        mLoader.load(request);
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
    public void onServerFail() {

        iView.hideProgressBar();
    }

    @Override
    public void onConnectionFail() {

        iView.hideProgressBar();
    }
}
