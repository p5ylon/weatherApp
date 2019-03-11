package ru.psylon.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private EditText townNameEditText;
    private CheckBox tempCheckBox;
    private CheckBox windCheckBox;
    private CheckBox huminityCheckBox;

    public static String TOWN = "town";
    public static String WIND = "wind";
    public static String TEMP = "temp";
    public static String HUMIDITY = "humidity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        townNameEditText = findViewById(R.id.town_name_textEdit);
        tempCheckBox = findViewById(R.id.temperature_checkBox);
        windCheckBox = findViewById(R.id.wind_strong_checkBox);
        huminityCheckBox = findViewById(R.id.humidity_checkBox);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showWeatherDetails(View view) {

        Intent intent = new Intent(this, ShowTownWeatherActivity.class);

        intent.putExtra(TOWN, townNameEditText.getText().toString());
        intent.putExtra(WIND, windCheckBox.isChecked());
        intent.putExtra(TEMP, tempCheckBox.isChecked());
        intent.putExtra(HUMIDITY, huminityCheckBox.isChecked());

        startActivity(intent);
    }


    public void onSettingsClicked(MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
