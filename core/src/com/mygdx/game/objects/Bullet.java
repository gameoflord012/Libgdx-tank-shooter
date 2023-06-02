package com.mygdx.game.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.engine.BingChilling;
import com.mygdx.game.engine.GameEntity;
import com.mygdx.game.engine.system.event.EntityCallbackReceiver;
import com.mygdx.game.engine.system.event.IUpdateCallback;

public class Bullet extends GameEntity implements IUpdateCallback
{
    public Body body;
    private World world;

    @Override
    public void onUpdate(float delta)
    {
        if(body != null && !BingChilling.getInstance().isPointScreen(body.getPosition().x, body.getPosition().y))
        {
            world.destroyBody(body);
            body = null;
        }
    }

    public Bullet(World world, Vector2 position, float angle)
    {
        this.world = world;

        add(new EntityCallbackReceiver().setUpdater(this));

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(position);
        def.angle = angle;

        body = world.createBody(def);

        FixtureDef fixture = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.set(new float[]{
                0, 4,
                2, -2,
                -2, -2
        });
        fixture.shape = shape;
        fixture.isSensor = true;
        body.createFixture(fixture);

        body.setLinearVelocity(body.getWorldVector(new Vector2(0, 50)));
    }

}