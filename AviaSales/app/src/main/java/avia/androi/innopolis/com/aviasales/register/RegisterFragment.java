package avia.androi.innopolis.com.aviasales.register;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import avia.androi.innopolis.com.aviasales.R;

public class RegisterFragment extends Fragment implements IRegistrationView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, null);

        return view;
    }

    @Override
    public void onRegistrationSuccess() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void setErrorMessage(int msgId) {

    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }
}
