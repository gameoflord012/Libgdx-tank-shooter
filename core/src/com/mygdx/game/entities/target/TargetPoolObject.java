package com.mygdx.game.entities.target;
import com.mygdx.game.Core;

public class TargetPoolObject
{
    private Target target;
    private TargetPoolFactory factory;

    public TargetPoolObject(TargetPoolFactory factory)
    {
        this.factory = factory;

        target = new Target();
        Core.gameEngine().addEntity(target);

        target.addTargetEventLisener(new Target.Event()
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
        target.Destroy();
    }

    public void returnToPool()
    {
        factory.returnPool(this);
    }
}
