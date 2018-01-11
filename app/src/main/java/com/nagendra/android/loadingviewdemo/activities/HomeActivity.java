package com.nagendra.android.loadingviewdemo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nagendra.android.loadingviewdemo.R;
import com.nagendra.android.loadingviewdemo.fragment.DataFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
    }

    private void initViews() {
        DataFragment fragment = new DataFragment();
//                Bundle args = new Bundle();
//                fragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_launcher, fragment).addToBackStack(null).commitAllowingStateLoss();
    }
}
