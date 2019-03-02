package ru.psylon.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


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


    public void showWeatherDetails(View view) {
        String townName = townNameEditText.getText().toString();
        boolean isTempChecked = tempCheckBox.isChecked();
        if (isTempChecked) Toast.makeText(this, "tempChecked", Toast.LENGTH_SHORT).show();
        boolean isWindChecked = windCheckBox.isChecked();
        boolean isHumChecked = huminityCheckBox.isChecked();

        Intent intent = new Intent(this, ShowTownWeatherActivity.class);

        intent.putExtra(TOWN, townName);
        intent.putExtra(WIND, isWindChecked);
        intent.putExtra(TEMP, isTempChecked);
        intent.putExtra(HUMIDITY, isHumChecked);

        startActivity(intent);
    }


}
