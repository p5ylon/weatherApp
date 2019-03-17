package ru.psylon.weatherapp.inteface;

import ru.psylon.weatherapp.model.CityCard;

public interface Observer {
    void update(CityCard currentCurd);
}
