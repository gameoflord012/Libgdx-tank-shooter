package systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

public abstract class EntitySystemWrapper
{
    public final EntitySystem base;

    public EntitySystemWrapper()
    {
        base = new EntitySystem() {
            @Override
            public void addedToEngine(Engine engine) {
                super.addedToEngine(engine);
            }

            @Override
            public void removedFromEngine(Engine engine) {
                super.removedFromEngine(engine);
            }

            @Override
            public void update(float deltaTime) {
                EntitySystemWrapper.this.update(deltaTime);
            }

            @Override
            public boolean checkProcessing() {
                return super.checkProcessing();
            }

            @Override
            public void setProcessing(boolean processing) {
                super.setProcessing(processing);
            }

            @Override
            public Engine getEngine() {
                return super.getEngine();
            }
        };
    }

    public void update(float deltaTime) {}
    public Engine getEngine()
    {
        return base.getEngine();
    }
}
