package avia.androi.innopolis.com.aviasales.interfaces;

import avia.androi.innopolis.com.aviasales.models.User;

public interface IPresenter {

    void onServerSuccess(User user);

    void onServerFail();

    void onConnectionFail();
}
