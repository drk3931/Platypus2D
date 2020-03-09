package com.drk3931.platplus.GameEvents;

import com.drk3931.platplus.CharacterEntity;
import com.drk3931.platplus.Entity;

public abstract class EntityOnEntityEvent implements GameEvent{

    Entity source;
    CharacterEntity target; 

}