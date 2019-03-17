package ru.psylon.weatherapp.common;

import java.util.ArrayList;
import java.util.List;

import ru.psylon.weatherapp.inteface.Observer;
import ru.psylon.weatherapp.model.CityCard;

public class CityChangedObservable {
    public static CityCard currentCityCard = null;

    private static int currentIndex = -1;

    private static List<Observer> observers = new ArrayList<>();


    public static void subscribe(Observer observer) {
        observers.add(observer);
    }

    public static void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public static void cityCardChanged(CityCard newCityCard) {
        currentCityCard = newCityCard;
        notifyObservers();
    }

    private static void notifyObservers() {
        for (Observer o : observers)
            o.update(currentCityCard);
    }

    public static int getCurrentIndex() {
        return currentIndex;
    }

    public static void setCurrentIndex(int currentIndex) {
        CityChangedObservable.currentIndex = currentIndex;
    }
}

