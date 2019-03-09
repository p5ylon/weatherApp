package ru.psylon.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static String PARCEL = "parcel";

    CityInputInfoFragment inputInfoFrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) inputInfoFrag = CityInputInfoFragment.newInstance(null);
        else {
            Parcel parcel = savedInstanceState.getParcelable(PARCEL);
            inputInfoFrag = CityInputInfoFragment.newInstance(parcel);
            inputInfoFrag.setParcel(parcel);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.input_info_fragment, inputInfoFrag)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcel parcel = inputInfoFrag.getParcel();
        outState.putParcelable(PARCEL, parcel);
    }
}
