package ru.psylon.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView townName;
    private TextView temp;
    private TextView condition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        townName = findViewById(R.id.townNameTextView);
        temp = findViewById(R.id.tempValueTextView);
        condition = findViewById(R.id.conditionValueTextView);

        // так понимаю, что далее это будет интерактивом в зависимости от местоположения и т.д. а пока так
        townName.setText(getString(R.string.moscow));
        temp.setText("-20");
        condition.setText(getString(R.string.cloudy));
    }


}
