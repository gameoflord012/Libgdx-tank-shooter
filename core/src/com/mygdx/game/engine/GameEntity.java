package com.mygdx.game.engine;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.engine.system.destroy.Destroyer;

import java.util.ArrayList;
import java.util.List;

public class GameEntity extends Entity
{
    public void Destroy()
    {
        add(new Destroyer());
    }
}
