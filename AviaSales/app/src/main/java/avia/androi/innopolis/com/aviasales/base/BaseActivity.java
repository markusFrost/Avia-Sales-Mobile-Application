package avia.androi.innopolis.com.aviasales.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setTitle(int id){
        getSupportActionBar().setTitle(id);
    }
}