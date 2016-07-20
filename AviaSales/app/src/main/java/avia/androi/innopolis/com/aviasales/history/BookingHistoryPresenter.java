package avia.androi.innopolis.com.aviasales.history;

import avia.androi.innopolis.com.aviasales.models.responses.BookingResponse;
import avia.androi.innopolis.com.aviasales.models.responses.UserRequest;
import avia.androi.innopolis.com.aviasales.utils.ShPrefUtils;

public class BookingHistoryPresenter implements IBookingHistoryPresenter {

    private IBookingHistoryView iView;

    private BookingHistoryLoader loader;

    public BookingHistoryPresenter(IBookingHistoryView view) {

        this.iView = view;

        loader = new BookingHistoryLoader(this);
    }

    @Override
    public void getBookingHistory() {

        iView.showProgressBar();
        UserRequest request = new UserRequest();
        request.setUserId(ShPrefUtils.getUser().getId());

        loader.load(request);
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
