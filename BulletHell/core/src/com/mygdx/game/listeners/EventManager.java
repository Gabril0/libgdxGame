package com.mygdx.game.listeners;

import java.util.ArrayList;

public class EventManager {
    private ArrayList<Listener> shotListeners = new ArrayList<Listener>();

    public void addShotListener(Listener listener) {
        shotListeners.add(listener);
    }
    public void removeShotListener(Listener listener) {
        shotListeners.remove(listener);
    }

    public void eventCall(boolean event) {
        for(Listener listener : shotListeners){
            listener.onShoot(event);
        }
    }

}
