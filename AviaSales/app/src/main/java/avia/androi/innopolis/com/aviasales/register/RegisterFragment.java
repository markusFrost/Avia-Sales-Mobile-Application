package avia.androi.innopolis.com.aviasales.register;

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
import avia.androi.innopolis.com.aviasales.search.TicketFragment;

public class RegisterFragment extends Fragment implements IRegistrationView {

    private EditText mEditName, mEditEmail, mEditPassword;

    private Button mButtonOk, mButtonCancel;

    private TextView mTextViewError;

    private LinearLayout mRegistrationLinearLayout;

    private ProgressBar mProgressBar;

    private RegistrationPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mPresenter = new RegistrationPresenter(this);

        View view = inflater.inflate(R.layout.fragment_register, null);

        mEditName = (EditText) view.findViewById(R.id.registrationEditName);
        mEditEmail = (EditText) view.findViewById(R.id.registrationEditEmail);
        mEditPassword = (EditText) view.findViewById(R.id.registrationEditPasswoed);

        mTextViewError = (TextView) view.findViewById(R.id.registrationTvError);
        mTextViewError.setVisibility(View.GONE);

        mRegistrationLinearLayout = (LinearLayout) view.findViewById(R.id.registrationLayout);

        mProgressBar = (ProgressBar) view.findViewById(R.id.registrationProgressBar);
        hideProgressBar();

        mButtonOk = (Button) view.findViewById(R.id.registrationButtonOk);


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
        String email = mEditEmail.getText().toString();
        String password = mEditPassword.getText().toString();



        if (name == null || name.isEmpty()) {

            setErrorMessage(R.string.errorEmptyName);

            return;
        }

        if (email == null || email.isEmpty()) {

            setErrorMessage(R.string.errorEmptyEmail);

            return;
        }

        if (password == null || password.isEmpty()) {

            setErrorMessage(R.string.errorEmptyPassword);

            return;
        }

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        mPresenter.register(user);


    }

    @Override
    public void onRegistrationSuccess() {

        Fragment fragment = TicketFragment.newInstance();

        if (getActivity() instanceof MainActivity){

            MainActivity mainActivity = (MainActivity) getActivity();

            mainActivity.showFragment(fragment);
        }
    }

    @Override
    public void showProgressBar() {

        mRegistrationLinearLayout.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mRegistrationLinearLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorMessage(int msgId) {

        mTextViewError.setVisibility(View.VISIBLE);

        mTextViewError.setText(msgId);
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }
}
