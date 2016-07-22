package avia.androi.innopolis.com.aviasales.login;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.models.User;
import avia.androi.innopolis.com.aviasales.utils.ShPrefUtils;

public class LoginPresenter implements ILoginPresenter {

    private ILoginView iView;

    private LoginLoader mLoader;

    public LoginPresenter(ILoginView view) {

        this.iView = view;

        this.mLoader = new LoginLoader(this);
    }

    @Override
    public void login(User user) {

        iView.showProgressBar();

        mLoader.load(user);
    }

    @Override
    public void onServerSuccess(Object object) {

        if (object instanceof User) {

            User user = (User) object;
            ShPrefUtils.setUser(user);

            iView.hideProgressBar();
            iView.onLoginSuccess();
        }
    }

    @Override
    public void onServerFail(String message) {

        iView.hideProgressBar();
        iView.setErrorMessage(message);
    }

    @Override
    public void onConnectionFail() {

        iView.hideProgressBar();;
        iView.setErrorMessage(R.string.errorNoConnection);
    }
}
