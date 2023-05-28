package com.mygdx.game.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Tank extends GameEntity
{
    Body body;
    World world;


    public Tank(World world)
    {
        this.world = world;
        CreateBodies();

        Gdx.input.setInputProcessor(new InputAdapter()
        {
            @Override
            public boolean keyDown(int keycode) {
                if(keycode == Input.Keys.A)
                {
                    Bullet bullet = new Bullet(
                        Tank.this.world,
                        body.getWorldPoint(new Vector2(0, 15)),
                        body.getAngle());
                }

                return true;
            }
        });
    }
    private void CreateBodies() {
        BodyDef dynamic = new BodyDef();
        dynamic.type = BodyDef.BodyType.DynamicBody;
        dynamic.position.set(0, 0);

        body = world.createBody(dynamic);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(8,10);
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        body.createFixture(fixture);

        shape.setAsBox(3 ,8, new Vector2(0, 5), 0);
        fixture.shape = shape;
        body.createFixture(fixture);

        shape.dispose();
    }

    @Override
    public void update(float delta)
    {
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            body.setAngularVelocity(3.5f);
        }
        else
        {
            body.setAngularVelocity(-3);
        }

        body.setLinearVelocity(body.getWorldVector(new Vector2(0, 10)));
    }

    @Override
    public void dispose()
    {
        Gdx.input.setInputProcessor(null);
    }
}
