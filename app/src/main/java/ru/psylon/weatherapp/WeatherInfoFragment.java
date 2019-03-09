package ru.psylon.weatherapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class WeatherInfoFragment extends Fragment {

    private static final String PARCEL = "parcel";
    private static final String LANDSCAPE = "landscape";

    private Parcel parcel;
    private boolean isLandscape;

    public static WeatherInfoFragment newInstance(Parcel parcel, boolean isLandscape) {
        WeatherInfoFragment fragment = new WeatherInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(PARCEL, parcel);
        args.putBoolean(LANDSCAPE, isLandscape);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            parcel = getArguments().getParcelable(PARCEL);
            isLandscape = getArguments().getBoolean(LANDSCAPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_show_weather, container, false);
        TextView notChosen = layout.findViewById(R.id.not_chosen);
        TextView townName = layout.findViewById(R.id.town_name_textView);
        LinearLayout weatherInfo = layout.findViewById(R.id.weather_info);
        if (isLandscape) layout.findViewById(R.id.back_to_input_button).setVisibility(View.GONE);

        String chosenTown = parcel.getCityName();
        if (chosenTown != null) {
            notChosen.setVisibility(View.GONE);
            weatherInfo.setVisibility(View.VISIBLE);
            townName.setText(chosenTown);

            if (!parcel.isHumanityChecked())
                layout.findViewById(R.id.humidity_linerLayout).setVisibility(View.GONE);
            if (!parcel.isTemperatureChecked())
                layout.findViewById(R.id.temperature_linerLayout).setVisibility(View.GONE);
            if (!parcel.isWindPowerChecked())
                layout.findViewById(R.id.wind_strong_linerLayout).setVisibility(View.GONE);
        }
        return layout;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        parcel = null;
    }
}
