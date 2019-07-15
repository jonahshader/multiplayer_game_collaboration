package com.compilation.game.Entities;

import com.badlogic.ashley.core.Engine;
import com.compilation.game.AbstractClasses.EntityObject;
import com.compilation.game.Components.*;

public class Player extends EntityObject {

    public Player(Engine engine) {
        super(engine);

        entity.add(new ChunkPosition());
        entity.add(new ExactPosition());
        entity.add(new GridPosition());
        entity.add(new Texture());
        entity.add(new Velocity());
        entity.add(new CollisionBox());
    }

}
