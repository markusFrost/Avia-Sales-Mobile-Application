package avia.androi.innopolis.com.aviasales.register;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import avia.androi.innopolis.com.aviasales.interfaces.IAviaService;
import avia.androi.innopolis.com.aviasales.interfaces.ILoader;
import avia.androi.innopolis.com.aviasales.models.ServerResponse;
import avia.androi.innopolis.com.aviasales.models.User;
import avia.androi.innopolis.com.aviasales.objects.AppContext;
import avia.androi.innopolis.com.aviasales.objects.Constants;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RegistrationLoader implements ILoader<User> {


    IRegistrationPresenter iPresenter;

    public RegistrationLoader(IRegistrationPresenter iPresenter){

        this.iPresenter = iPresenter;
    }

    @Override
    public void load(final User user) {

        String json = AppContext.getGson().toJson(user);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IAviaService service = retrofit.create(IAviaService.class);

        Call<ResponseBody> responseBodyCall =  service.regiser(json);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit)
            {
                try {
                    String json = response.body().string();

                    ServerResponse serverResponse = AppContext.getGson().fromJson(json, ServerResponse.class);

                    iPresenter.onServerSuccess(serverResponse.getUser());

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
