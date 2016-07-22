package avia.androi.innopolis.com.aviasales.interfaces;

import avia.androi.innopolis.com.aviasales.models.User;
import avia.androi.innopolis.com.aviasales.models.responses.FlightResponse;

public interface IPresenter {

    void onServerSuccess(Object object);

    void onServerFail(String message);

    void onConnectionFail();
}
