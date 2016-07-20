package avia.androi.innopolis.com.aviasales.interfaces;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

public interface IAviaService {

    @POST("/registration_mobile.jsp")
    Call<ResponseBody> regiser(@Header("json_user") String json);

    @POST("/login_mobile.jsp")
    Call<ResponseBody> login(@Header("json_user") String json);

    @GET("/flights_search_mobile.jsp")
    Call<ResponseBody> getFlights(@Header("json_flights") String json);
}
