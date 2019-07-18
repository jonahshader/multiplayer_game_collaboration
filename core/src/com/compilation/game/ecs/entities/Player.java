package com.compilation.game.ecs.entities;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.compilation.game.AbstractClasses.EntityObject;
import com.compilation.game.ecs.components.*;

public class Player extends EntityObject {

    public Player(Engine engine) {
        super(engine);

        entity.add(new ChunkPosition());
        entity.add(new ExactPosition());
        entity.add(new Graphic(new Sprite()));
        entity.add(new Velocity());
        entity.add(new CollisionBox(null));
    }

}
