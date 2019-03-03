package ru.psylon.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowTownWeatherActivity extends AppCompatActivity {

    private TextView townTextView;

    private LinearLayout tempLayout;
    private LinearLayout windLayout;
    private LinearLayout humLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_town_weather);

        townTextView = findViewById(R.id.town_name_textView);
        tempLayout = findViewById(R.id.temperature_linerLayout);
        windLayout = findViewById(R.id.wind_strong_linerLayout);
        humLayout = findViewById(R.id.humidity_linerLayout);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            townTextView.setText(extras.getString(MainActivity.TOWN));
            if (extras.getBoolean(MainActivity.TEMP)) tempLayout.setVisibility(View.VISIBLE);
            if (extras.getBoolean(MainActivity.WIND)) windLayout.setVisibility(View.VISIBLE);
            if (extras.getBoolean(MainActivity.HUMIDITY)) humLayout.setVisibility(View.VISIBLE);
        }

    }

    public void backToMain(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
