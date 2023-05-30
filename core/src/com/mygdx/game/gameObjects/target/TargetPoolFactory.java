package com.mygdx.game.gameObjects.target;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.WorldLisenerRegister;

import java.util.Stack;

public class TargetPoolFactory
{
    WorldLisenerRegister world;
    int size = 2;

    Stack<TargetPoolObject> pool = new Stack<TargetPoolObject>();

    public TargetPoolFactory(WorldLisenerRegister world)
    {
        this.world = world;
    }

    public TargetPoolObject getPoolObject(float px, float py)
    {
        TargetPoolObject poolObject;

        if(pool.isEmpty())
        {
            poolObject = new TargetPoolObject(world, this);

        }
        else
        {
            poolObject = pool.pop();

        }

        poolObject.wakeyWakeyEggsAndBakey(px, py);

        return poolObject;
    }

    public void returnPool(TargetPoolObject poolObject)
    {
        poolObject.sleep();

        if(pool.size() < size)
        {
            pool.add(poolObject);
        }
        else
        {
            pool.remove(poolObject);
        }
    }
}
