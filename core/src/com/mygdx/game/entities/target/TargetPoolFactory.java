package com.mygdx.game.entities.target;

import java.util.Stack;

public class TargetPoolFactory
{
    int size = 2;

    Stack<TargetPoolObject> poolObjects = new Stack<TargetPoolObject>();


    public TargetPoolObject getPoolObject(float px, float py)
    {
        TargetPoolObject poolObject;

        if(poolObjects.isEmpty())
        {
            poolObject = new TargetPoolObject(this);
        }
        else
        {
            poolObject = poolObjects.pop();
        }

          poolObject.wakeyWakeyEggsAndBakey(px, py);

        return poolObject;
    }

    public void returnPool(TargetPoolObject poolObject)
    {
        poolObject.sleep();

        if(poolObjects.size() < size)
        {
            poolObjects.add(poolObject);
        }
        else
        {
            poolObject.removeFromPool();
        }
    }
}
