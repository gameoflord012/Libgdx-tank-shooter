package donut.core.api.system.physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import donut.core.api.system.EntitySystemWrapable;
import donut.core.api.system.EntitySystemWrapper;

import java.util.HashSet;
import java.util.Set;

public class PhysicSystem extends EntitySystemWrapper<PhysicSystem>
{
    private Set<ContactListener> contactListeners = new HashSet<>();

    public static class Wrapable extends EntitySystemWrapable<PhysicSystem>
    {
        @Override
        public void update(float deltaTime)
        {
            getWrapper().getWorld().step(deltaTime, 3, 3);
        }
    }

    @Override
    protected EntitySystemWrapable<PhysicSystem> getWrappable() {
        return new Wrapable();
    }

    final World world;

    public PhysicSystem()
    {
        this.world = new World(new Vector2(0, 0), true);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                for (ContactListener contactListener : getContactListeners())
                {
                    contactListener.beginContact(contact);
                }
            }

            @Override
            public void endContact(Contact contact) {
                for (ContactListener contactListener : getContactListeners())
                {
                    contactListener.endContact(contact);
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                for (ContactListener contactListener : getContactListeners())
                {
                    contactListener.preSolve(contact, oldManifold);
                }
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                for (ContactListener contactListener : getContactListeners())
                {
                    contactListener.postSolve(contact, impulse);
                }
            }
        });
    }

    public World getWorld()
    {
        return world;
    }

    public PhysicBody getPhysicBody(BodyDef def)
    {
        return new PhysicBody(world, def);
    }


    public final ContactListener[] getContactListeners()
    {
        contactListeners.remove(null);
        return contactListeners.toArray(new ContactListener[0]);
    }

    public void addContactListener(ContactListener lisener)
    {
        contactListeners.add(lisener);
    }

    public void removeContactLisener(ContactListener lisener)
    {
        contactListeners.remove(lisener);
    }
}
