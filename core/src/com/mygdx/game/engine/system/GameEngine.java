package com.mygdx.game.engine.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.mygdx.game.engine.BingChilling;

public class GameEngine
{
    public static void addEntity(Entity entity)
    {
        BingChilling.getInstance().getEngine().addEntity(entity);
    }
}
