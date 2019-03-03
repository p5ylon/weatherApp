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
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            townTextView.setText(extras.getString(MainActivity.TOWN));
            if (extras.getBoolean(MainActivity.TEMP)) setVisibility(R.id.temperature_linerLayout);
            if (extras.getBoolean(MainActivity.WIND)) setVisibility(R.id.wind_strong_linerLayout);
            if (extras.getBoolean(MainActivity.HUMIDITY)) setVisibility(R.id.humidity_linerLayout);
        }

    }

    public void backToMain(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private void setVisibility(int id) {
        findViewById(id).setVisibility(View.VISIBLE);
    }
}
