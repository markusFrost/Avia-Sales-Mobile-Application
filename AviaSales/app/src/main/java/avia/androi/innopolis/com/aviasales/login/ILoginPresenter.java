package avia.androi.innopolis.com.aviasales.login;

import avia.androi.innopolis.com.aviasales.interfaces.IPresenter;
import avia.androi.innopolis.com.aviasales.models.User;

public interface ILoginPresenter extends IPresenter {

    void login(User user);
}
