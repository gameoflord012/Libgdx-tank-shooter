package com.mygdx.game.engine.system.event;

import com.badlogic.ashley.core.Component;

public class EntityCallbackReceiver implements Component {
    IUpdateCallback update;
    ICreateCallback create;

    public void onUpdate(float delta)
    {
        update.onUpdate(delta);
    }

    public void onCreate()
    {
        create.onCreate();
    }

    public EntityCallbackReceiver setUpdater(IUpdateCallback updater)
    {
        this.update = updater;
        return this;
    }

    public EntityCallbackReceiver setCreator(ICreateCallback creater)
    {
        this.create = creater;
        return this;
    }
}
