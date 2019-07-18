package com.compilation.game.world;

public class World {
    private static World ourInstance;

    private WorldChunk[][] loadedChunks = new WorldChunk[3][3]; // 3 by 3 grid of world chunks will be loaded
    private WorldGenerator worldGen;

    private World() {
        worldGen = new WorldGenerator();
    }



    public static World getInstance() {
        if (ourInstance == null)
            ourInstance = new World();
        return ourInstance;
    }

    public void run() {
        /*
        pseudo code:
        check if the player has entered a new chunk.
        if the player has:
            unload the chunks furthest from the player, and load the new chunks.
            generate the base world from the worldGen.
            ask the database if this chunk is unmodified from the WorldGenerator or if its modified.
            if its modified:
                download the permutations from the database and apply them over the existing world data from the worldGen.



        collision detection:
        keep an array of the collidable entities of the 9 chunks via Family filtering.
        get the entities and iterate over them to see who is colliding.
        NOTE: this should probably be implemented into the collision system or into the movement system when those are
        created.
         */
    }

}
