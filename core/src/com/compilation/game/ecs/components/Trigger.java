package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Entities with a Trigger + CollisionBox component will activate TriggerAction based on the eventType.
 * Use for triggering events.
 * TODO: could be built in to other components that would normally use a trigger. i.e. an OpenUponRequest component
 *  might have trigger built in. idk yet.
 */
public class Trigger implements Component, Pool.Poolable {
    public interface TriggerAction {
        void action();
    }


    enum TriggerEvent {
        ON_TRIGGER,     // activates on overlap
        OFF_TRIGGER,    // activates when un-overlapped
        WHILE_TRIGGER   // activates while overlapping
    }
    public TriggerEvent eventType;
    public TriggerAction action;

    public Trigger(TriggerEvent eventType, TriggerAction action) {
        this.eventType = eventType;
        this.action = action;
    }

    @Override
    public void reset() {
        eventType = null;
        action = null;
    }

}
