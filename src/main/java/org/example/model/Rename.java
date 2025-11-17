package org.example.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Rename {
    private List<Observer> observers =  new CopyOnWriteArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.onModelChanged();
        }
    }

}
