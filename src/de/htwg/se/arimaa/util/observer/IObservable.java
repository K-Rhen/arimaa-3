package de.htwg.se.arimaa.util.observer;

import java.awt.Event;

public interface IObservable {

    void addObserver(IObserver s);

    void removeObserver(IObserver s);

    void removeAllObservers();

    void notifyObservers();

    void notifyObservers(Event e);
}