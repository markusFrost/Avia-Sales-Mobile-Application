package avia.androi.innopolis.com.aviasales.booking;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import avia.androi.innopolis.com.aviasales.interfaces.IAviaService;
import avia.androi.innopolis.com.aviasales.interfaces.ILoader;
import avia.androi.innopolis.com.aviasales.models.responses.BookingRequest;
import avia.androi.innopolis.com.aviasales.models.responses.BookingResponse;
import avia.androi.innopolis.com.aviasales.objects.AppContext;
import avia.androi.innopolis.com.aviasales.objects.Constants;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class BookingLoader implements ILoader<BookingRequest> {

    IBookingPresenter iPresenter;

    public BookingLoader(IBookingPresenter presenter) {

        this.iPresenter = presenter;
    }

    @Override
    public void load(BookingRequest request) {

      /*  new Handler().postDelayed(new Runnable() {
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
        }, 2000);*/

        String json = AppContext.getGson().toJson(request);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IAviaService service = retrofit.create(IAviaService.class);

        Call<ResponseBody> responseBodyCall =  service.bookFlights(json);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit)
            {
                try {
                    String json = response.body().string();

                    BookingResponse bookingResponse = AppContext.getGson().fromJson(json, BookingResponse.class);

                    Object object = bookingResponse;

                    iPresenter.onServerSuccess(object);

                } catch (IOException e) {

                    iPresenter.onServerFail();
                }

            }

            @Override
            public void onFailure(Throwable t) {

                iPresenter.onConnectionFail();
            }
        });



    }
}
