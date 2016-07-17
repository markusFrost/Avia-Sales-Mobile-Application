package avia.androi.innopolis.com.aviasales.register;

import android.os.Handler;

import avia.androi.innopolis.com.aviasales.interfaces.ILoader;
import avia.androi.innopolis.com.aviasales.models.User;

public class RegistrationLoader implements ILoader<User> {


    IRegistrationPresenter iPresenter;

    public RegistrationLoader(IRegistrationPresenter iPresenter){

        this.iPresenter = iPresenter;
    }

    @Override
    public void load(User user) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iPresenter.onServerFail();
            }
        }, 2000);

    }
}
