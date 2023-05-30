package com.mygdx.game.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameClass;

public class Bullet extends GameEntity
{
    public Body body;
    private World world;
    public Bullet(World world, Vector2 position, float angle)
    {
        this.world = world;

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

    @Override
    public void update(float delta)
    {
        if(body != null && !GameClass.getInstance().isPointScreen(body.getPosition().x, body.getPosition().y))
        {
            world.destroyBody(body);
            body = null;
        }
    }
}