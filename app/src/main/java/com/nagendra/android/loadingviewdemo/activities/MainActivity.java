package com.nagendra.android.loadingviewdemo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nagendra.android.loadingviewdemo.R;
import com.nagendra.android.loadingviewdemo.fragment.DataFragment;
import com.nex3z.togglebuttongroup.MultiSelectToggleGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mNextBtn;
    private Set<Integer> apartmentGroup = new HashSet<>();
    private Set<Integer> propertyGroup = new HashSet<>();
    private ArrayList<String> apartmentFilterList = new ArrayList<>();
    private ArrayList<String> propertyFilterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNextBtn = findViewById(R.id.apply_filter);
        mNextBtn.setOnClickListener(this);

        MultiSelectToggleGroup multi1 = (MultiSelectToggleGroup) findViewById(R.id.group_Apartment_type);
        multi1.setOnCheckedChangeListener(new MultiSelectToggleGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedStateChanged(MultiSelectToggleGroup group, int checkedId, boolean isChecked) {
                Log.v("group1", "onCheckedStateChanged(): group.getCheckedIds() = " + group.getCheckedIds());
                apartmentGroup =group.getCheckedIds();
                Log.v("group11",apartmentGroup.contains(2131230925)+"");

            }
        });

        MultiSelectToggleGroup multi2 = (MultiSelectToggleGroup) findViewById(R.id.group_property);
        multi2.setOnCheckedChangeListener(new MultiSelectToggleGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedStateChanged(MultiSelectToggleGroup group, int checkedId, boolean isChecked) {
                Log.v("group2", "onCheckedStateChanged(): group.getCheckedIds() = " + group.getCheckedIds());
                propertyGroup = group.getCheckedIds();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.apply_filter:
                if (apartmentGroup.contains(2131230925)){
                    Log.d("group111","1RK house");
                    apartmentFilterList.add("RK1");
                }
                if (apartmentGroup.contains(2131230834)){
                    Log.d("group111","1BHK house");
                    apartmentFilterList.add("BHK1");
                }
                if (apartmentGroup.contains(2131230915)){
                    Log.d("group111","2BHK house");
                    apartmentFilterList.add("BHK2");
                }
                if (apartmentGroup.contains(2131230895)){
                    Log.d("group111","3BHK house");
                    apartmentFilterList.add("BHK3");
                }
                if (apartmentGroup.contains(2131230797)){
                    Log.d("group111","4BHK house");
                    apartmentFilterList.add("BHK4");
                }
                if (propertyGroup.contains(2131230804)){
                    Log.d("group111","Apartment");
                    propertyFilterList.add("Apartment");
                }
                if (propertyGroup.contains(2131230920)){
                    Log.d("group111","Independent house/Villa");
                    propertyFilterList.add("Independent house/Villa");
                }
                if (propertyGroup.contains(2131230795)){
                    Log.d("group111","Independent Floor/Builder Floor");
                    propertyFilterList.add("Independent Floor/Builder Floor");
                }
                mNextBtn.setVisibility(View.INVISIBLE);
                DataFragment fragment = new DataFragment();
                Bundle args = new Bundle();
                args.putStringArrayList("apartment_type", apartmentFilterList);
                args.putStringArrayList("property_type",propertyFilterList);
                fragment.setArguments(args);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_launcher, fragment).addToBackStack(null).commitAllowingStateLoss();
                break;
        }
    }

    public void values(String callVerify) {
        mNextBtn.setVisibility(View.VISIBLE);
        apartmentFilterList.clear();
    }
}
