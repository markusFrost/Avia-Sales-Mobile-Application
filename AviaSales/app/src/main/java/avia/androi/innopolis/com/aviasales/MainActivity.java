package avia.androi.innopolis.com.aviasales;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import avia.androi.innopolis.com.aviasales.base.BaseActivity;
import avia.androi.innopolis.com.aviasales.history.BookingHistoryFragment;
import avia.androi.innopolis.com.aviasales.main_presenters.IMainView;
import avia.androi.innopolis.com.aviasales.main_presenters.MainActivityPresenter;
import avia.androi.innopolis.com.aviasales.models.User;
import avia.androi.innopolis.com.aviasales.search.TicketFragment;
import avia.androi.innopolis.com.aviasales.utils.FragmentUtils;
import avia.androi.innopolis.com.aviasales.utils.ShPrefUtils;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, IMainView {

    private MainActivityPresenter mPresenter;

    private  NavigationView navigationView;

    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainActivityPresenter(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mPresenter.chooseRightFragment();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.nav_search_flight) {

            fragment = TicketFragment.newInstance();
        }
        else if (id == R.id.nav_booking_history) {

            fragment = BookingHistoryFragment.newInstance();
        }
        else if (id == R.id.nav_logout) {

        }

        showFragment(fragment);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showFragment(Fragment fragment) {

        if (fragment != null) {

            this.fragment = fragment;
            FragmentUtils.setFragment(fragment, MainActivity.this);
        }
    }

    @Override
    public void initializeNavDrawer() {

        View view = getLayoutInflater().inflate(R.layout.nav_header_main, null);

        TextView tvName = (TextView) view.findViewById(R.id.nav_header_name);

        TextView tvEmail = (TextView) view.findViewById(R.id.nav_header_email);

        User user = ShPrefUtils.getUser();

        tvName.setText(user.getName());

        tvEmail.setText(user.getEmail());

        navigationView.addHeaderView(view);
    }

    public Fragment getFragment(){

        return fragment;
    }
}
