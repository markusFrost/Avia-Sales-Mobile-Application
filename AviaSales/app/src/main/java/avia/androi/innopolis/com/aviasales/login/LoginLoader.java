package avia.androi.innopolis.com.aviasales.login;

import android.os.Handler;

import avia.androi.innopolis.com.aviasales.interfaces.ILoader;
import avia.androi.innopolis.com.aviasales.models.Item;
import avia.androi.innopolis.com.aviasales.models.User;

public class LoginLoader implements ILoader<Item> {

    ILoginPresenter iPresenter;

    public LoginLoader(ILoginPresenter presenter) {

        this.iPresenter = presenter;
    }

    @Override
    public void load(User item) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iPresenter.onServerFail();
            }
        }, 2000);
    }
}
