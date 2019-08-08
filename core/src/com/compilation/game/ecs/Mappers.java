package com.compilation.game.ecs;

import com.badlogic.ashley.core.ComponentMapper;
import com.compilation.game.ecs.components.*;

/**
 * this class contains all the component mappers that are used for retrieving components from entities.
 */
public class Mappers {
    public static final ComponentMapper<Acceleration> accelerationMpr = ComponentMapper.getFor(Acceleration.class);
    public static final ComponentMapper<CollisionBox> collisionBoxMpr = ComponentMapper.getFor(CollisionBox.class);
    public static final ComponentMapper<Graphic> graphicMpr = ComponentMapper.getFor(Graphic.class);
    public static final ComponentMapper<MaxSpeed> maxSpeedMpr = ComponentMapper.getFor(MaxSpeed.class);
    public static final ComponentMapper<NetworkTransmitID> networkTransmitIDMpr = ComponentMapper.getFor(NetworkTransmitID.class);
    public static final ComponentMapper<PlayerControlled> playerControlledMpr = ComponentMapper.getFor(PlayerControlled.class);
    public static final ComponentMapper<Position> positionMpr = ComponentMapper.getFor(Position.class);
    public static final ComponentMapper<Spectating> spectatingMpr = ComponentMapper.getFor(Spectating.class);
    public static final ComponentMapper<Trigger> triggerMpr = ComponentMapper.getFor(Trigger.class);
    public static final ComponentMapper<UUID> uuidMpr = ComponentMapper.getFor(UUID.class);
    public static final ComponentMapper<Velocity> velocityMpr = ComponentMapper.getFor(Velocity.class);
}
