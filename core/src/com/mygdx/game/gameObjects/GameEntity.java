package com.mygdx.game.gameObjects;

import com.badlogic.gdx.Game;

import java.util.ArrayList;
import java.util.List;

public abstract class GameEntity
{
    public static List<GameEntity> list = new ArrayList<GameEntity>();

    protected GameEntity()
    {
        list.add(this);
    }

    public void update(float delta){}
    public void dispose(){}
}
