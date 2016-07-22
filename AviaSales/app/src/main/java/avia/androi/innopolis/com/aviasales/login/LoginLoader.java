package avia.androi.innopolis.com.aviasales.login;

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

public class LoginLoader implements ILoader<User> {

    ILoginPresenter iPresenter;

    public LoginLoader(ILoginPresenter presenter) {

        this.iPresenter = presenter;
    }

    @Override
    public void load(User user) {

        String json = AppContext.getGson().toJson(user);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IAviaService service = retrofit.create(IAviaService.class);

        Call<ResponseBody> responseBodyCall =  service.login(json);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit)
            {
                try {
                    String json = response.body().string();

                     UserResponse resp = AppContext.getGson().fromJson(json, UserResponse.class);

                    if (resp.getCode() != 200){

                        iPresenter.onServerFail(resp.getMessage());
                    }
                    else {

                        iPresenter.onServerSuccess(resp.getUser());
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
