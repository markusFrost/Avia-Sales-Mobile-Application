package avia.androi.innopolis.com.aviasales.login;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.models.User;

public class LoginFragment extends Fragment implements ILoginView {

    private EditText mEditName, mEditPassword;

    private Button mButtonOk, mButtonCancel;

    private TextView mTextViewError;

    private LinearLayout mLoginLinearLayout;

    private ProgressBar mProgressBar;

    private LoginPresenter mPresenter;


    public static LoginFragment newInstance(){

        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mPresenter = new LoginPresenter(this);

        View view = inflater.inflate(R.layout.fragment_login, null);

        mEditName = (EditText) view.findViewById(R.id.loginEditName);
        mEditPassword = (EditText) view.findViewById(R.id.loginEditPassword);

        mTextViewError = (TextView) view.findViewById(R.id.loginTvError);
        mTextViewError.setVisibility(View.GONE);

        mLoginLinearLayout = (LinearLayout) view.findViewById(R.id.loginLayout);

        mProgressBar = (ProgressBar) view.findViewById(R.id.loginProgressBar);
        hideProgressBar();

        mButtonOk = (Button) view.findViewById(R.id.loginButtonOk);


        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateData();
            }
        });


        return view;
    }

    private void validateData() {

        mTextViewError.setVisibility(View.GONE);

        String name = mEditName.getText().toString();
        String password = mEditPassword.getText().toString();

        if (name == null || name.isEmpty()) {

            setErrorMessage(R.string.errorEmptyName);

            return;
        }

        if (password == null || password.isEmpty()) {

            setErrorMessage(R.string.errorEmptyPassword);

            return;
        }

        User user = new User();

        user.setName(name);
        user.setPassword(password);

        mPresenter.login(user);
    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void showProgressBar() {

        mLoginLinearLayout.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {

        mLoginLinearLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorMessage(int msgId) {

        mTextViewError.setVisibility(View.VISIBLE);
        mTextViewError.setText(msgId);
    }
}
