package ru.psylon.weatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


public class CityInputInfoFragment extends Fragment {
    public static final String TAG = CityInputInfoFragment.class.getSimpleName();

    private static final String PARCEL = "parcel";

    private boolean isInfoFragExists;
    private Parcel parcel;

    private RecyclerView citiesRecycler;
    private CheckBox tempCheckBox;
    private CheckBox humCheckBox;
    private CheckBox windCheckBox;

    private View selfView;

    public static CityInputInfoFragment newInstance(Parcel parcel) {
        CityInputInfoFragment fragment = new CityInputInfoFragment();
        Bundle args = new Bundle();
        if (parcel == null) {
            parcel = new Parcel("", false, false, false);
        }
        args.putParcelable(PARCEL, parcel);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input_info, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            parcel = getArguments().getParcelable(PARCEL);
        } else {
            parcel = new Parcel("", false, false, false);
        }

    }


    private void setViews(final View view) {
        humCheckBox = view.findViewById(R.id.humidity_checkBox);
        tempCheckBox = view.findViewById(R.id.temperature_checkBox);
        windCheckBox = view.findViewById(R.id.wind_strong_checkBox);
        final Button showWeatherButton = view.findViewById(R.id.show_weather_button);

        citiesRecycler = view.findViewById(R.id.cities_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        citiesRecycler.setLayoutManager(manager);
        CitiesRecyclerAdapter adapter = new CitiesRecyclerAdapter(getResources().getStringArray(R.array.cities));
        citiesRecycler.setAdapter(adapter);
        //TODO: do smth with selection - change background - need wrapper?
        adapter.setOnCityClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parcel.setCityName(((TextView) v).getText().toString());
                if (isInfoFragExists) showWeatherInfo();
            }
        });

        showWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWeatherInfo();
            }
        });

        humCheckBox.setOnCheckedChangeListener(new OnCheckBoxSelectionChanged());
        tempCheckBox.setOnCheckedChangeListener(new OnCheckBoxSelectionChanged());
        windCheckBox.setOnCheckedChangeListener(new OnCheckBoxSelectionChanged());
    }

    private void setValues(Bundle savedInstanceState) {
        parcel = savedInstanceState.getParcelable(PARCEL);
        humCheckBox.setSelected(parcel.isHumanityChecked());
        tempCheckBox.setSelected(parcel.isTemperatureChecked());
        windCheckBox.setSelected(parcel.isTemperatureChecked());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selfView = view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View weatherInfoFrag = getActivity().findViewById(R.id.weather_info_fragment);

        isInfoFragExists = weatherInfoFrag != null;
        setViews(selfView);

        if (isInfoFragExists) {
            selfView.findViewById(R.id.show_weather_button).setVisibility(View.GONE);
            showWeatherInfo();
        }

        if (savedInstanceState != null) {
            parcel = savedInstanceState.getParcelable(PARCEL);
            setValues(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PARCEL, parcel);
    }

    private void showWeatherInfo() {
        WeatherInfoFragment weatherInfo = WeatherInfoFragment.newInstance(parcel, isInfoFragExists);
        if (isInfoFragExists) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.weather_info_fragment, weatherInfo)
                    .commit();

        } else {
            getFragmentManager()
                    .beginTransaction()
                    .addToBackStack(TAG)
                    .replace(R.id.input_info_fragment, weatherInfo)
                    .commit();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parcel = null;
    }

    private class OnCheckBoxSelectionChanged implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView == humCheckBox) parcel.setHumanityChecked(isChecked);
            if (buttonView == windCheckBox) parcel.setWindPowerChecked(isChecked);
            if (buttonView == tempCheckBox) parcel.setTemperatureChecked(isChecked);
            if (isInfoFragExists) showWeatherInfo();
        }
    }

}

