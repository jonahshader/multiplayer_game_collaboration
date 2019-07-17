package com.compilation.game.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;

public class WorldChunk {
    public static final int CHUNK_SIZE = 64;
    public static final int TILE_SIZE = 32; // size in pixels

    private TiledMap map;

    public WorldChunk() {
        map = new TiledMap();

        TiledMapTileLayer collisionLayer = new TiledMapTileLayer(CHUNK_SIZE, CHUNK_SIZE, TILE_SIZE, TILE_SIZE);
        Cell cell = new Cell();
    }
}
