package com.drk3931.platplus;



abstract class TimedRoutine{
    float currentTimeStep;
    float runtime;

    public void reset(float runtime)
    {
        this.currentTimeStep = 0;
        this.runtime = runtime;
    }
    
    public abstract void exec(float delta);

    public void routine(float delta)
    {
        this.currentTimeStep += delta;
        if(this.currentTimeStep > runtime)
        {
            return;
        }
        exec(delta);

    }
}

