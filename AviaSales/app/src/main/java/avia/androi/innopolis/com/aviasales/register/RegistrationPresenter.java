package avia.androi.innopolis.com.aviasales.register;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.models.User;

public class RegistrationPresenter implements IRegistrationPresenter {

    IRegistrationView iView;

    RegistrationLoader mLoader;

    public RegistrationPresenter(IRegistrationView iRegistrationView) {

        this.iView = iRegistrationView;

        this.mLoader = new RegistrationLoader(this);
    }

    @Override
    public void register(User user) {

        iView.showProgressBar();

        mLoader.load(user);
    }

    @Override
    public void onServerSuccess() {

        iView.hideProgressBar();
        iView.onRegistrationSuccess();
    }

    @Override
    public void onServerFail() {

        iView.hideProgressBar();;
        iView.setErrorMessage(R.string.errorCanNotRegister);
    }

    @Override
    public void onConnectionFail() {

        iView.hideProgressBar();;
        iView.setErrorMessage(R.string.errorNoConnection);
    }
}
