package avia.androi.innopolis.com.aviasales.booking;

import android.os.Handler;

import avia.androi.innopolis.com.aviasales.interfaces.ILoader;
import avia.androi.innopolis.com.aviasales.models.responses.BookingRequest;
import avia.androi.innopolis.com.aviasales.models.responses.BookingResponse;
import avia.androi.innopolis.com.aviasales.objects.AppContext;
import avia.androi.innopolis.com.aviasales.utils.FakeHelper;

public class BookingLoader implements ILoader<BookingRequest> {

    IBookingPresenter iPresenter;

    public BookingLoader(IBookingPresenter presenter) {

        this.iPresenter = presenter;
    }

    @Override
    public void load(BookingRequest request) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                BookingResponse response = new BookingResponse();
                response.setCode(201);
                response.setMessage("Booking was sucessfull");

                response.setListBooking(FakeHelper.generateBooking());

                String json = AppContext.getGson().toJson(response);


                BookingResponse resp = AppContext.getGson().fromJson(json, BookingResponse.class);
                Object object = resp;

                iPresenter.onServerSuccess(object);
            }
        }, 2000);



    }
}
