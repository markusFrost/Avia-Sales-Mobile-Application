package avia.androi.innopolis.com.aviasales.register;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.database.IAviaSalesDatabase;
import avia.androi.innopolis.com.aviasales.models.User;
import avia.androi.innopolis.com.aviasales.utils.ShPrefUtils;

public class RegistrationPresenter implements IRegistrationPresenter {

    IRegistrationView iView;

    RegistrationLoader mLoader;

    IAviaSalesDatabase db;

    public RegistrationPresenter(IRegistrationView iRegistrationView) {

        this.iView = iRegistrationView;

        this.mLoader = new RegistrationLoader(this);
    }

    public RegistrationPresenter(IRegistrationView iRegistrationView, IAviaSalesDatabase iAviaSalesDatabase) {

        this.iView = iRegistrationView;

        this.mLoader = new RegistrationLoader(this);

        db = iAviaSalesDatabase;
    }



    @Override
    public void register(User user) {

        iView.showProgressBar();

        mLoader.load(user);
    }

    @Override
    public void onServerSuccess(User user) {

        ShPrefUtils.setUser(user);

        iView.hideProgressBar();
        iView.onRegistrationSuccess();
    }

    @Override
    public void onServerFail() {

        iView.hideProgressBar();
        iView.setErrorMessage(R.string.errorCanNotRegister);
    }

    @Override
    public void onConnectionFail() {

        iView.hideProgressBar();;
        iView.setErrorMessage(R.string.errorNoConnection);
    }
}
