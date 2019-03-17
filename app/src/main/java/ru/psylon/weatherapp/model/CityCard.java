package ru.psylon.weatherapp.model;

public class CityCard {
    private String cityName;
    private boolean isChecked;


    public CityCard() {
    }

    public CityCard(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
