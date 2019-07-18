package com.compilation.game.world;

import java.util.ArrayList;
import java.util.HashMap;

public class World {
    private static World ourInstance;

    private ArrayList<WorldChunk> loadedChunks;
    private HashMap<String, WorldChunk> chunkDictionary;
    private WorldGenerator worldGen;

    private World() {
        loadedChunks = new ArrayList<>(9);
        chunkDictionary = new HashMap<>(9);
        worldGen = new WorldGenerator();
    }

    public static World getInstance() {
        if (ourInstance == null)
            ourInstance = new World();
        return ourInstance;
    }

    public void run() {
        //TODO: generify the following pseudocode to work on ALL entities that are non static (and the static ones that
        // have collision, but that might be a little different).
        //TODO: along with the Collidable component, we should add a Trigger component that has a collision rectangle but
        // only checks if other entities collided with it. it would be used to trigger events or open doors/etc.
        /*
        pseudo code:
        check if the player has entered a new chunk.
        if the player has:
            unload the chunks furthest from the player, and load the new chunks.
            generate the base world from the worldGen.
            ask the database if this chunk is unmodified from the WorldGenerator or if its modified.
            if its modified:
                download the permutations from the database and apply them over the existing world data from the
                worldGen.



        collision detection:
        keep an array of the collidable entities of the 9 chunks via Family filtering.
        get the entities and iterate over them to see who is colliding.
        NOTE: this should probably be implemented into the collision system or into the movement system when those are
        created.
         */
    }

}
