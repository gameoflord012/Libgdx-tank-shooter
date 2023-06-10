package core.system.debug;

import core.system.EntitySystemWrapable;
import core.system.EntitySystemWrapper;

public class DebugSystem extends EntitySystemWrapper<DebugSystem>
{
    public static class Wrapable extends EntitySystemWrapable<DebugSystem>
    {
        @Override
        public void update(float deltaTime) {
            super.update(deltaTime);
        }
    }

    @Override
    protected EntitySystemWrapable<DebugSystem> getWrappable()
    {
        return new Wrapable();
    }
}
