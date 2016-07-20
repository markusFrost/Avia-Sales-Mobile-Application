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

import avia.androi.innopolis.com.aviasales.MainActivity;
import avia.androi.innopolis.com.aviasales.R;
import avia.androi.innopolis.com.aviasales.models.User;
import avia.androi.innopolis.com.aviasales.register.RegisterFragment;
import avia.androi.innopolis.com.aviasales.search.TicketFragment;

public class LoginFragment extends Fragment implements ILoginView {

    private EditText mEditEmail, mEditPassword;

    private Button mButtonOk, mButtonRegistration;

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

        mEditEmail = (EditText) view.findViewById(R.id.loginEditEmail);
        mEditPassword = (EditText) view.findViewById(R.id.loginEditPassword);

        mTextViewError = (TextView) view.findViewById(R.id.loginTvError);
        mTextViewError.setVisibility(View.GONE);

        mLoginLinearLayout = (LinearLayout) view.findViewById(R.id.loginLayout);

        mProgressBar = (ProgressBar) view.findViewById(R.id.loginProgressBar);
        hideProgressBar();

        mButtonOk = (Button) view.findViewById(R.id.loginButtonOk);
        mButtonRegistration = (Button) view.findViewById(R.id.loginButtonRegister);


        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateData();
            }
        });

        mButtonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = RegisterFragment.newInstance();

                MainActivity activity = (MainActivity) getActivity();

                activity.showFragment(fragment);

            }
        });


        return view;
    }

    private void validateData() {

        mEditEmail.setText("and@mail");
        mEditPassword.setText("1234");

        mTextViewError.setVisibility(View.GONE);

        String email = mEditEmail.getText().toString();
        String password = mEditPassword.getText().toString();

        if (email == null || email.isEmpty()) {

            setErrorMessage(R.string.errorEmptyEmail);

            return;
        }

        if (password == null || password.isEmpty()) {

            setErrorMessage(R.string.errorEmptyPassword);

            return;
        }

        User user = new User();

        user.setEmail(email);
        user.setPassword(password);

        mPresenter.login(user);
    }

    @Override
    public void onLoginSuccess() {

        Fragment fragment = TicketFragment.newInstance();

        if (getActivity() instanceof MainActivity){

            MainActivity mainActivity = (MainActivity) getActivity();

            mainActivity.showFragment(fragment);
        }
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
