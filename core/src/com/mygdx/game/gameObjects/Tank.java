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

    float fireRateInSecond = 0.1f;
    float elapsedFireRateTime = 2;


    public Tank(World world)
    {
        this.world = world;
        CreateBodies();

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

        body.setAngularDamping(5f);

        shape.dispose();
    }

    @Override
    public void update(float delta)
    {
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            body.setAngularVelocity(2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            body.setAngularVelocity(-2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            if(fireRateInSecond < elapsedFireRateTime)
            {
                Bullet bullet = new Bullet(
                        this.world,
                        body.getWorldPoint(new Vector2(0, 15)),
                        body.getAngle());


                elapsedFireRateTime = 0;
            }
        }
        body.setLinearVelocity(body.getWorldVector(new Vector2(0, 10)));

        elapsedFireRateTime += delta;
    }

    @Override
    public void dispose()
    {
        Gdx.input.setInputProcessor(null);
    }
}
