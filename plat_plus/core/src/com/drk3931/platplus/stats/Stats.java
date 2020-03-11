package com.drk3931.platplus.stats;

import java.util.HashMap;
import java.util.Map;

public class Stats{

    HashMap<String,Integer> stats;
    
    public Stats(){
        this.stats = new HashMap<String,Integer>();

    }

    public int modStat(String key,int value) 
    {

        return stats.put(key, value);


    }

    public int getStat(String key)
    {

        return stats.get(key);

    }

}