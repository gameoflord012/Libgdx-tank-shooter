package com.mygdx.game.gameObjects.target;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.WorldLisenerRegister;

public class TargetPoolObject
{
    private Target target;
    private TargetPoolFactory factory;

    public TargetPoolObject(WorldLisenerRegister world, TargetPoolFactory factory)
    {
        target = new Target(world);
        this.factory = factory;

        target.lisener = new Target.TargetEvent()
        {
            @Override
            public void onTargetGoHeaven()
            {
                returnToPool();
            }
        };
    }

    public void wakeyWakeyEggsAndBakey(float px, float py)
    {
        target.body.setActive(true);
        target.setPosition(px, py);
    }

    public void sleep()
    {
        target.body.setActive(false);
    }

    public void returnToPool()
    {
        factory.returnPool(this);
    }
}
