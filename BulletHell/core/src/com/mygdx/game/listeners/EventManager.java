package com.mygdx.game.listeners;

import java.util.ArrayList;

public class EventManager {
    private ArrayList<ShotListener> shotListeners = new ArrayList<ShotListener>();

    public void addShotListener(ShotListener listener) {
        shotListeners.add(listener);
    }
    public void removeShotListener(ShotListener listener) {
        shotListeners.remove(listener);
    }
    public void eventCall(boolean event) {
        for(ShotListener listener : shotListeners){
            listener.onShoot(event);
        }
    }
}
