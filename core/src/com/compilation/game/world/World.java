package com.compilation.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.compilation.game.MainGame;
import com.compilation.game.managers.SqlManager;

import java.util.ArrayList;
import java.util.HashMap;

public class World {
    public static final int LOADED_RADIUS = 2;
    private ArrayList<WorldChunk> loadedChunks;
    private HashMap<String, WorldChunk> chunkDictionary;
    private WorldGenerator worldGen;

    private TextureAtlas worldAtlas;

    TiledMapTileLayer.Cell grassCell = new TiledMapTileLayer.Cell();
    TiledMapTileLayer.Cell waterShallowCell = new TiledMapTileLayer.Cell();
    TiledMapTileLayer.Cell waterDeepCell = new TiledMapTileLayer.Cell();
    TiledMapTileLayer.Cell sandCell = new TiledMapTileLayer.Cell();

    private int chunkCenterX = 0;
    private int chunkCenterY = 0;
    private boolean chunkUpdateQueued = true;


    private MainGame game;

    public World(MainGame game) {
        this.game = game;

        worldAtlas = new TextureAtlas("textures/terrain_64x64.pack");

        StaticTiledMapTile grassTile = new StaticTiledMapTile(worldAtlas.findRegion("grass"));
        StaticTiledMapTile waterShallowTile = new StaticTiledMapTile(worldAtlas.findRegion("water_shallow"));
        StaticTiledMapTile waterDarkTile = new StaticTiledMapTile(worldAtlas.findRegion("water_deep"));
        StaticTiledMapTile sandTile = new StaticTiledMapTile(worldAtlas.findRegion("sand"));

        grassCell.setTile(grassTile);
        waterShallowCell.setTile(waterShallowTile);
        waterDeepCell.setTile(waterDarkTile);
        sandCell.setTile(sandTile);

        loadedChunks = new ArrayList<>(9);
        chunkDictionary = new HashMap<>(9);
        worldGen = new WorldGenerator(125233242);


//        for (int x = -3; x < 3; x++) {
//            for (int y = -3; y < 3; y++) {
//                WorldChunk
//                loadedChunks.add(new WorldChunk(x, y, game, worldGen, this));
//            }
//        }
    }

    public void run() {
        if (chunkUpdateQueued) {
            chunkUpdateQueued = false;
            for (int x = -LOADED_RADIUS; x <= LOADED_RADIUS; x++) {
                for (int y = -LOADED_RADIUS; y <= LOADED_RADIUS; y++) {
                    // if this chunk is not loaded,
                    if (!chunkDictionary.containsKey(WorldChunk.coordToKey(x + chunkCenterX, y + chunkCenterY))) {
                        // load it
                        WorldChunk newChunk = new WorldChunk(x + chunkCenterX, y + chunkCenterY, game, worldGen, this);
                        loadedChunks.add(newChunk);
                        chunkDictionary.put(newChunk.getKey(), newChunk);
                    }
                }
            }
        }
    }

    public void render(OrthographicCamera cam) {
        cam.update(); // make sure the cam is updated before using it
        for (WorldChunk worldChunk : loadedChunks) {
            worldChunk.renderBackground(cam);
        }
    }

    public void updateCenterChunk(double centerX, double centerY) {
        int chunkX = (int)Math.floor(centerX / WorldChunk.CHUNK_SIZE);
        int chunkY = (int)Math.floor(centerY / WorldChunk.CHUNK_SIZE);

        if (this.chunkCenterX != centerX || this.chunkCenterY != centerY) {
            // queue chunk update
            chunkUpdateQueued = true;
            this.chunkCenterX = chunkX;
            this.chunkCenterY = chunkY;
        }
    }

    public WorldChunk getChunk(int x, int y) {
        return chunkDictionary.get(WorldChunk.coordToKey(x, y));
    }

}
