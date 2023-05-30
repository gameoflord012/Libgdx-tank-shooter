package com.mygdx.game.gameObjects.target;

import com.mygdx.game.WorldLisenerRegister;

public class TargetPoolObject
{
    private Target target;
    private TargetPoolFactory factory;
    WorldLisenerRegister world;

    public TargetPoolObject(WorldLisenerRegister world, TargetPoolFactory factory)
    {
        this.world = world;
        target = new Target(world);
        this.factory = factory;

        target.addTargetEventLisener(new Target.TargetEvent()
        {
            @Override
            public void onTargetGoHeaven()
            {
                returnToPool();
            }
        });
    }

    public void wakeyWakeyEggsAndBakey(float px, float py)
    {
        target.setActive(true);
        target.setPosition(px, py);
    }

    public void sleep()
    {
        target.setActive(false);
    }

    public void removeFromPool()
    {
        factory.poolObjects.remove(this);
        target.dispose();
    }

    public void returnToPool()
    {
        factory.returnPool(this);
    }
}
