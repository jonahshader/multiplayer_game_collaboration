package com.compilation.game.networking;

import com.badlogic.ashley.core.Entity;
import com.compilation.game.ecs.components.Position;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

import static com.compilation.game.networking.ServerManager.registerClasses;

public class ClientManager {

    private Client client;

    public ClientManager() {
        client = new Client();
        registerClasses(client.getKryo());
        client.start();
        try {
            client.connect(5000, "0.0.0.0", ServerManager.PORT, ServerManager.PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Entity entity = new Entity();
        entity.add(new Position(3, 4));

    }
}
