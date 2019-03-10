package ru.psylon.weatherapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        CityInputInfoFragment inputInfoFragment = (CityInputInfoFragment) fragmentManager.findFragmentByTag(CityInputInfoFragment.TAG);

        if (inputInfoFragment == null) {
            inputInfoFragment = CityInputInfoFragment.newInstance(null);
        }

        replaceFragment(fragmentManager, R.id.input_info_fragment, inputInfoFragment, CityInputInfoFragment.TAG);


    }

    public static void replaceFragment(FragmentManager manager, int target, Fragment fragment, String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(target, fragment, tag);
        transaction.commitAllowingStateLoss();
    }

}
