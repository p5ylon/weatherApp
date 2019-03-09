package ru.psylon.weatherapp;

import android.os.Parcelable;

class Parcel implements Parcelable {

    private String cityName;
    private boolean isWindPowerChecked;
    private boolean isTemperatureChecked;
    private boolean isHumanityChecked;

    Parcel(String cityName, boolean isWindPowerChecked, boolean isTemperatureChecked, boolean isHumanityChecked) {
        this.cityName = cityName;
        this.isWindPowerChecked = isWindPowerChecked;
        this.isTemperatureChecked = isTemperatureChecked;
        this.isHumanityChecked = isHumanityChecked;
    }

    String getCityName() {
        return cityName;
    }

    public boolean isWindPowerChecked() {
        return isWindPowerChecked;
    }

    public boolean isTemperatureChecked() {
        return isTemperatureChecked;
    }

    public boolean isHumanityChecked() {
        return isHumanityChecked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.cityName);
        dest.writeByte(this.isWindPowerChecked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isTemperatureChecked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isHumanityChecked ? (byte) 1 : (byte) 0);
    }

    protected Parcel(android.os.Parcel in) {
        this.cityName = in.readString();
        this.isWindPowerChecked = in.readByte() != 0;
        this.isTemperatureChecked = in.readByte() != 0;
        this.isHumanityChecked = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Parcel> CREATOR = new Parcelable.Creator<Parcel>() {
        @Override
        public Parcel createFromParcel(android.os.Parcel source) {
            return new Parcel(source);
        }

        @Override
        public Parcel[] newArray(int size) {
            return new Parcel[size];
        }
    };

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setWindPowerChecked(boolean windPowerChecked) {
        isWindPowerChecked = windPowerChecked;
    }

    public void setTemperatureChecked(boolean temperatureChecked) {
        isTemperatureChecked = temperatureChecked;
    }

    public void setHumanityChecked(boolean humanityChecked) {
        isHumanityChecked = humanityChecked;
    }
}
