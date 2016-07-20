package avia.androi.innopolis.com.aviasales.booking;

import java.util.ArrayList;
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
    public void book(List<UUID> list, int placeCount) {

        mBookingLoader = new BookingLoader(this);
        BookingRequest request = new BookingRequest();

        request.setUserId(UUID.fromString("638da681-2688-493a-9f5b-dff07e8cdcb2"));

        List<UUID> listIds = new ArrayList<>();

        listIds.add(UUID.fromString("c039581f-e70b-4a3e-83f6-82b253d091d5"));
        listIds.add(UUID.fromString("5ae8c137-0c96-4eb5-8232-f2ce8872ae6a"));

        request.setDateBooking(System.currentTimeMillis());
        request.setListFlightIds(listIds);

        request.setPlaceCount(1);

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
