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
    }

    public void run() {

    }

    public void render(OrthographicCamera cam) {
        cam.update(); // make sure the cam is updated before using it
        for (WorldChunk worldChunk : loadedChunks) {
            worldChunk.renderBackground(cam);
        }
    }

}
