package com.compilation.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.compilation.game.MainGame;

import java.util.ArrayList;
import java.util.HashMap;

public class World {
    private ArrayList<WorldChunk> loadedChunks;
    private HashMap<String, WorldChunk> chunkDictionary;
    private WorldGenerator worldGen;

    private TextureAtlas worldAtlas;
    TiledMapTileLayer.Cell grassCell = new TiledMapTileLayer.Cell();
    TiledMapTileLayer.Cell waterShallowCell = new TiledMapTileLayer.Cell();
    TiledMapTileLayer.Cell waterDarkCell = new TiledMapTileLayer.Cell();
    TiledMapTileLayer.Cell sandCell = new TiledMapTileLayer.Cell();

    private StaticTiledMapTile grassTile, waterShallowTile, waterDarkTile, sandTile;

    private MainGame game;

    public World(MainGame game) {
        this.game = game;

        worldAtlas = new TextureAtlas("textures/terrain_64x64.pack");

        grassTile = new StaticTiledMapTile(worldAtlas.findRegion("grass"));
        waterShallowTile = new StaticTiledMapTile(worldAtlas.findRegion("water_shallow"));
        waterDarkTile = new StaticTiledMapTile(worldAtlas.findRegion("water_deep"));
        sandTile = new StaticTiledMapTile(worldAtlas.findRegion("sand"));

        grassCell.setTile(grassTile);
        waterShallowCell.setTile(waterShallowTile);
        waterDarkCell.setTile(waterDarkTile);
        sandCell.setTile(sandTile);

        loadedChunks = new ArrayList<>(9);
        chunkDictionary = new HashMap<>(9);
        worldGen = new WorldGenerator(13232424);


        for (int x = -3; x < 3; x++) {
            for (int y = -3; y < 3; y++) {
                loadedChunks.add(new WorldChunk(x, y, game, worldGen, this));
            }
        }
//        loadedChunks.add(new WorldChunk(0, 0, game, worldGen));
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

    public void render(OrthographicCamera cam) {
//        cam.zoom = 32;
//        cam.translate(0.9f, 0);
//        MainGame.batch.setProjectionMatrix(cam.combined);
        cam.update(); // make sure the cam is updated before using it
        for (WorldChunk worldChunk : loadedChunks) {

            worldChunk.renderBackground(cam);
        }
    }

}
