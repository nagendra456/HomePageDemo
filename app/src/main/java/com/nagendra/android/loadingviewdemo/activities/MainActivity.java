package com.nagendra.android.loadingviewdemo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nagendra.android.loadingviewdemo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mNextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNextBtn = findViewById(R.id.next);
        mNextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next:
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
                break;
        }
    }
}
