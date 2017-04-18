package avia.androi.innopolis.com.aviasales.main_presenters;

import android.app.Fragment;

import avia.androi.innopolis.com.aviasales.login.LoginFragment;
import avia.androi.innopolis.com.aviasales.models.User;
import avia.androi.innopolis.com.aviasales.search.TicketFragment;
import avia.androi.innopolis.com.aviasales.utils.ShPrefUtils;

public class MainActivityPresenter {

    private IMainView iView;

    public  MainActivityPresenter (IMainView iView){

        this.iView = iView;
    }

    public void chooseRightFragment(){

        User user = ShPrefUtils.getUser();

       /* user = new User();
        user.setEmail("dfd");
        user.setName("dfddf");*/

        if (user == null){

            Fragment fragment = LoginFragment.newInstance();

            iView.showFragment(fragment);
        }
        else {
            Fragment fragment = TicketFragment.newInstance();

            iView.showFragment(fragment);

            iView.initializeNavDrawer();
        }
    }
}
