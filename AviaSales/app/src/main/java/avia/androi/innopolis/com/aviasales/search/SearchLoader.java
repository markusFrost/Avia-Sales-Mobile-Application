package avia.androi.innopolis.com.aviasales.search;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import avia.androi.innopolis.com.aviasales.interfaces.IAviaService;
import avia.androi.innopolis.com.aviasales.interfaces.ILoader;
import avia.androi.innopolis.com.aviasales.models.responses.FlightRequest;
import avia.androi.innopolis.com.aviasales.models.responses.FlightResponse;
import avia.androi.innopolis.com.aviasales.objects.AppContext;
import avia.androi.innopolis.com.aviasales.objects.Constants;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SearchLoader implements ILoader<FlightRequest> {

    ISearchPresenter iPresenter;

    public SearchLoader(ISearchPresenter presenter) {

        this.iPresenter = presenter;
    }

    @Override
    public void load(FlightRequest request) {

       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String json = "{\"listTo\":[[{\"flightId\":\"c039581f-e70b-4a3e-83f6-82b253d091d5\",\"mCityFrom\":{\"cityId\":\"f3df9929-bd70-4658-9a4d-d523a3bf2a2c\",\"name\":\"Moscow\",\"code\":\"1\"},\"mCityTo\":{\"cityId\":\"a4ce48f3-25df-4d12-b0b9-50f25c920b27\",\"name\":\"Tokio\",\"code\":\"2\"},\"mDateDep\":1468947780000,\"mDateArr\":1469034180000,\"mPricePerTicket\":200.0,\"mFreePlaceCount\":20}],[{\"flightId\":\"4759b7a7-0aa2-427d-9ff8-1790019215d4\",\"mCityFrom\":{\"cityId\":\"f3df9929-bd70-4658-9a4d-d523a3bf2a2c\",\"name\":\"Moscow\",\"code\":\"1\"},\"mCityTo\":{\"cityId\":\"a4ce48f3-25df-4d12-b0b9-50f25c920b27\",\"name\":\"Tokio\",\"code\":\"2\"},\"mDateDep\":1468969500000,\"mDateArr\":1468980600000,\"mPricePerTicket\":250.0,\"mFreePlaceCount\":10}]],\"listBack\":[[{\"flightId\":\"5ae8c137-0c96-4eb5-8232-f2ce8872ae6a\",\"mCityFrom\":{\"cityId\":\"a4ce48f3-25df-4d12-b0b9-50f25c920b27\",\"name\":\"Tokio\",\"code\":\"2\"},\"mCityTo\":{\"cityId\":\"f3df9929-bd70-4658-9a4d-d523a3bf2a2c\",\"name\":\"Moscow\",\"code\":\"1\"},\"mDateDep\":1469205000000,\"mDateArr\":1469219400000,\"mPricePerTicket\":240.0,\"mFreePlaceCount\":25}]],\"code\":0}";

                FlightResponse flightResponse = AppContext.getGson().fromJson(json, FlightResponse.class);

                Object object = flightResponse;

                iPresenter.onServerSuccess(object);
            }
        }, 2000);*/



        String json = AppContext.getGson().toJson(request);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IAviaService service = retrofit.create(IAviaService.class);

        Call<ResponseBody> responseBodyCall =  service.getFlights(json);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit)
            {
                try {
                    String json = response.body().string();

                    FlightResponse flightResponse = AppContext.getGson().fromJson(json, FlightResponse.class);

                    Object object = flightResponse;

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
