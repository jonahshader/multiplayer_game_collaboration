package com.compilation.game.networking;

import com.badlogic.ashley.core.Entity;
import com.compilation.game.ecs.components.*;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class ServerManager {

    public final static int PORT = 29383;

    private Server server;

    public ServerManager() {
        server = new Server();
        registerClasses(server.getKryo());
        server.start();
        try {
            server.bind(PORT, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof Entity) {
                    Entity request = (Entity)object;
                    System.out.println(request);

                    connection.sendTCP("thx 4 entity :)");
                }
            }
        });
    }

    public static void registerClasses(Kryo kryo) {
        kryo.register(Entity.class);
//        kryo.register(Acceleration.class);
//        kryo.register(CollisionBox.class);
//        kryo.register(Graphic.class);
//        kryo.register(MaxSpeed.class);
//        kryo.register(NetworkTransmitID.class);
//        kryo.register(PlayerControlled.class);
//        kryo.register(Position.class);
//        kryo.register(Spectating.class);
//        kryo.register(Trigger.class);
//        kryo.register(UUID.class);
//        kryo.register(Velocity.class);
    }
}
