package avia.androi.innopolis.com.aviasales.booking;

import java.util.List;
import java.util.UUID;

import avia.androi.innopolis.com.aviasales.models.responses.BookingRequest;
import avia.androi.innopolis.com.aviasales.models.responses.BookingResponse;
import avia.androi.innopolis.com.aviasales.search.ITicketView;
import avia.androi.innopolis.com.aviasales.utils.ShPrefUtils;

public class BookingPresenter implements IBookingPresenter {

    private BookingLoader mBookingLoader;
    private ITicketView iView;

    public BookingPresenter(ITicketView view) {

        this.iView = view;
    }


    @Override
    public void book(List<UUID> listIdsStraight, List<UUID> listIdsBack, int placeCount) {

        mBookingLoader = new BookingLoader(this);
        BookingRequest request = new BookingRequest();


        //request.setUserId(ShPrefUtils.getUser().getId());
        request.setUserId(UUID.fromString("99a3c93a-25f2-4dc4-802c-e17f290df021"));

        request.setDateBooking(System.currentTimeMillis());

        request.setListFlightIdsStraight(listIdsStraight);
        request.setListFlightIdsBack(listIdsBack);

        request.setPlaceCount(placeCount);

        mBookingLoader.load(request);

        iView.showProgressBar();
    }

    @Override
    public void onServerSuccess(Object object) {

        if (object instanceof BookingResponse){

            BookingResponse response = (BookingResponse) object;

            ShPrefUtils.setListBooking(response.getListBooking());

            iView.hideProgressBar();

            iView.goToBookingHistory(response.getListBooking());
        }


    }

    @Override
    public void onServerFail() {

    }

    @Override
    public void onConnectionFail() {

    }
}
