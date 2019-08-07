package com.compilation.game.networking;

import com.badlogic.ashley.core.Entity;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class ServerManager {

    public final static int PORT = 29383;

    private Server server;

    public ServerManager() {
        server = new Server();
        server.start();
        try {
            server.bind(29383, 29383);
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
}
