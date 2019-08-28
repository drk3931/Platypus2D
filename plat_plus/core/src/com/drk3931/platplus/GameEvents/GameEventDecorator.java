package com.drk3931.platplus.GameEvents;


public class GameEventDecorator extends GameEvent{

    private GameEvent event;
    public GameEventDecorator(GameEvent e)
    {
        this.event = e;
    }

    
    public void action(float delta)
    {
        this.event.action(delta);
    }



}