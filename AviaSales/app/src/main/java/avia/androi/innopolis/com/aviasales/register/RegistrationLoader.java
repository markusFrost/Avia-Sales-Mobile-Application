package avia.androi.innopolis.com.aviasales.register;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import avia.androi.innopolis.com.aviasales.interfaces.IAviaService;
import avia.androi.innopolis.com.aviasales.interfaces.ILoader;
import avia.androi.innopolis.com.aviasales.models.responses.UserResponse;
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

                    UserResponse userResponse = AppContext.getGson().fromJson(json, UserResponse.class);

                    if (userResponse.getCode() != 201){

                        iPresenter.onServerFail(userResponse.getMessage());
                    }
                    else {

                        iPresenter.onServerSuccess(userResponse.getUser());
                    }

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
