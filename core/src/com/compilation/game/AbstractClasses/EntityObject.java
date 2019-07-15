package com.compilation.game.AbstractClasses;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

abstract public class EntityObject {

    public Entity entity = new Entity();

    public EntityObject(Engine engine){
        engine.addEntity(entity);
    }

    public void Destroy(Engine engine){
        engine.removeEntity(entity);
    }

}
