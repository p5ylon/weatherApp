package ru.psylon.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class WeatherInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_show_weather);

        if (savedInstanceState != null) finish();

        Parcel parcel = getIntent().getParcelableExtra("parcel");

        String chosenTown = parcel.getCityName();
        if (chosenTown != null) {
            findViewById(R.id.not_chosen).setVisibility(View.GONE);
            findViewById(R.id.weather_info).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.town_name_textView)).setText(chosenTown);

            if (!parcel.isHumanityChecked())
                findViewById(R.id.humidity_linerLayout).setVisibility(View.GONE);
            if (!parcel.isTemperatureChecked())
                findViewById(R.id.temperature_linerLayout).setVisibility(View.GONE);
            if (!parcel.isWindPowerChecked())
                findViewById(R.id.wind_strong_linerLayout).setVisibility(View.GONE);
        }

        findViewById(R.id.back_to_input_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
