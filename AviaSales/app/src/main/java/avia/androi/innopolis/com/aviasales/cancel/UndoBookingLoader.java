package avia.androi.innopolis.com.aviasales.cancel;

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

public class UndoBookingLoader implements ILoader<BookingRequest> {

    IUndoBookingPresenter iPresenter;

    public UndoBookingLoader(IUndoBookingPresenter presenter) {

        this.iPresenter = presenter;
    }

    @Override
    public void load(BookingRequest request) {

        String json = AppContext.getGson().toJson(request);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IAviaService service = retrofit.create(IAviaService.class);

        Call<ResponseBody> responseBodyCall =  service.undoBbookFlights(json);

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

                    iPresenter.onConnectionFail();
                }

            }

            @Override
            public void onFailure(Throwable t) {

                iPresenter.onConnectionFail();
            }
        });
    }
}

