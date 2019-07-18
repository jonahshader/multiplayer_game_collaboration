package com.compilation.game.world;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.compilation.game.MainGame;

import java.util.ArrayList;

public class WorldChunk {
    public static final int CHUNK_SIZE = 64;
    public static final int TILE_SIZE = 32; // size in

    private static final int[] backgroundLayers = { 0 };    // list of background layers
    private static final int[] foregroundLayers = { 1 };    // list of foreground layers

    private TiledMap map;
    private MapRenderer mapRenderer;
    private MainGame game;
    private ArrayList<WorldChunk> neighboringChunks;

    private boolean[][] collisionMap; // 2d boolean array for fast access for collision detection

    private int x;
    private int y; // x y index of this chunk

    public WorldChunk(int x, int y, MainGame game) {
        this.x = x;
        this.y = y;
        this.game = game;

        neighboringChunks = new ArrayList<>(8);

        map = new TiledMap();

        TiledMapTileLayer background = new TiledMapTileLayer(CHUNK_SIZE, CHUNK_SIZE, TILE_SIZE, TILE_SIZE);
        TiledMapTileLayer foreground = new TiledMapTileLayer(CHUNK_SIZE, CHUNK_SIZE, TILE_SIZE, TILE_SIZE);

        MapLayers layers = map.getLayers();
        layers.add(background);
        layers.add(foreground);

        mapRenderer = new OrthogonalTiledMapRenderer(map, 1f/TILE_SIZE, MainGame.batch); // one tile translates to one unit in distance
    }

    public void addNeighboringChunk(WorldChunk neighbor) {
        neighboringChunks.add(neighbor);
    }

    public int getXInUnits() {
        return x * CHUNK_SIZE;
    }

    public int getYInUnits() {
        return y * CHUNK_SIZE;
    }

    public int getXIndex() {
        return x;
    }

    public int getYIndex() {
        return y;
    }

    public String getKey() {
        return x + " " + y;
    }

//    public void renderBackground() {
//        mapRenderer.render(backgroundLayers);
//    }
//
//    public void renderForeground() {
//        mapRenderer.render(foregroundLayers);
//    }
}
