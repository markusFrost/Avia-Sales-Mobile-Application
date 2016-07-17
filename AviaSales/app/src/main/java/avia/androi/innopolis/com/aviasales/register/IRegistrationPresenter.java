package avia.androi.innopolis.com.aviasales.register;

import avia.androi.innopolis.com.aviasales.interfaces.IPresenter;
import avia.androi.innopolis.com.aviasales.models.User;

public interface IRegistrationPresenter extends IPresenter {

    void register(User user);
}
