package com.mygdx.game.gameObjects;

import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class GameEntity implements Disposable {
    public static Set<GameEntity> entities = new HashSet<GameEntity>();
    private static List<GameEntity> garbage = new ArrayList<>();

    protected GameEntity()
    {
        entities.add(this);
    }

    public void update(float delta){}

    private void addToGarbage()
    {
        garbage.add(this);
    }

    public static void updateAll(float delta)
    {
        for (GameEntity entity: entities)
        {
            entity.update(delta);
        }

        entities.removeAll(garbage);
        garbage.clear();
    }

    @Override
    public void dispose() {
        addToGarbage();
    }
}
