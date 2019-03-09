package ru.psylon.weatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;


public class CityInputInfoFragment extends Fragment {

    private static final String PARCEL = "parcel";

    private boolean isInfoFragExists;
    private Parcel parcel;

    private Button showWeatherButton;
    private Spinner citiesSpinner;
    private CheckBox tempCheckBox;
    private CheckBox humCheckBox;
    private CheckBox windCheckBox;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            parcel = getArguments().getParcelable(PARCEL);
        } else {
            parcel = new Parcel("Moscow", false, false, false);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_input_info, container, false);
        humCheckBox = layout.findViewById(R.id.humidity_checkBox);
        tempCheckBox = layout.findViewById(R.id.temperature_checkBox);
        windCheckBox = layout.findViewById(R.id.wind_strong_checkBox);
        showWeatherButton = layout.findViewById(R.id.show_weather_button);
        citiesSpinner = layout.findViewById(R.id.cities_spinner);

        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] choice = getResources().getStringArray(R.array.cities);
                parcel.setCityName(choice[position]);
                if (isInfoFragExists) showWeatherInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        humCheckBox.setOnCheckedChangeListener(new OnCheckBoxSelectionChanged());
        tempCheckBox.setOnCheckedChangeListener(new OnCheckBoxSelectionChanged());
        windCheckBox.setOnCheckedChangeListener(new OnCheckBoxSelectionChanged());

        if (savedInstanceState != null) {
            parcel = savedInstanceState.getParcelable(PARCEL);
            List<String> cities = Arrays.asList(getResources().getStringArray(R.array.cities));
            citiesSpinner.setSelection(cities.indexOf(parcel.getCityName()));
            humCheckBox.setSelected(parcel.isHumanityChecked());
            tempCheckBox.setSelected(parcel.isTemperatureChecked());
            windCheckBox.setSelected(parcel.isTemperatureChecked());
        }
        return layout;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View weatherInfoFrag = getActivity().findViewById(R.id.weather_info_fragment);

        isInfoFragExists = weatherInfoFrag != null;

        if (savedInstanceState != null) {
            parcel = savedInstanceState.getParcelable(PARCEL);
        }

        if (isInfoFragExists) {
            showWeatherInfo();
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PARCEL, parcel);
    }


    private void showWeatherInfo() {
        parcel = new Parcel(citiesSpinner.getSelectedItem().toString(),
                windCheckBox.isChecked(),
                tempCheckBox.isChecked(),
                humCheckBox.isChecked());
        WeatherInfoFragment weatherInfo = WeatherInfoFragment.newInstance(parcel);
        if (isInfoFragExists) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.weather_info_fragment, weatherInfo)
                    .commit();

        } else {
            getFragmentManager()
                    .beginTransaction()
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

