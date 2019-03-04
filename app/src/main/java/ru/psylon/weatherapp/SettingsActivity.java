package ru.psylon.weatherapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {
    public static final String THEME = "theme";
    private SharedPreferences sharedPreferences;


    private View.OnClickListener onRadioClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton radioButton = (RadioButton) v;
            setNewTheme(radioButton.getId());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int theme = sharedPreferences.getInt(THEME, R.style.AppTheme);
        setTheme(theme);
        setContentView(R.layout.activity_settings);

        RadioButton darkThemeRadio = findViewById(R.id.dark_theme_radio);
        darkThemeRadio.setOnClickListener(onRadioClickListener);
        RadioButton lightThemeRadio = findViewById(R.id.light_theme_radio);
        lightThemeRadio.setOnClickListener(onRadioClickListener);

    }

    protected void setNewTheme(int color) {
        switch (color) {
            case R.id.light_theme_radio:
                sharedPreferences.edit().putInt(THEME, android.R.style.Theme_Black).apply();
                recreate();
                break;
            case R.id.dark_theme_radio:
                sharedPreferences.edit().putInt(THEME, android.R.style.Theme_Black).apply();
                recreate();
                break;
        }

    }

}
