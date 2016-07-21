package avia.androi.innopolis.com.aviasales.history;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import avia.androi.innopolis.com.aviasales.interfaces.IAviaService;
import avia.androi.innopolis.com.aviasales.interfaces.ILoader;
import avia.androi.innopolis.com.aviasales.models.responses.BookingResponse;
import avia.androi.innopolis.com.aviasales.models.responses.UserRequest;
import avia.androi.innopolis.com.aviasales.objects.AppContext;
import avia.androi.innopolis.com.aviasales.objects.Constants;
import avia.androi.innopolis.com.aviasales.utils.NetworkUtils;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class BookingHistoryLoader implements ILoader<UserRequest> {

    IBookingHistoryPresenter iPresenter;
    public BookingHistoryLoader(IBookingHistoryPresenter presenter) {

        this.iPresenter = presenter;
    }

    @Override
    public void load(UserRequest request) {

        String json = AppContext.getGson().toJson(request);

        boolean isConnect = NetworkUtils.isConnected();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IAviaService service = retrofit.create(IAviaService.class);

        Call<ResponseBody> responseBodyCall =  service.getBookingHistory(json);

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
