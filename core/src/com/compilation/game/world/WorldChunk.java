package com.compilation.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.compilation.game.MainGame;

import java.util.ArrayList;

public class WorldChunk {
    public static final int CHUNK_SIZE = 64;
    public static final int TILE_SIZE = 64; // size in

    private static final int[] backgroundLayers = { 0 };    // list of background layers
    private static final int[] foregroundLayers = { 1 };    // list of foreground layers

    private TiledMap map;
    private MapRenderer mapRenderer;
    private MainGame game;
    private ArrayList<WorldChunk> neighboringChunks;
    private World containingWorld;
    private WorldGenerator worldGen;
    private TiledMapTileLayer background, foreground;

    private boolean[][] collisionData; // 2d boolean array for fast access for collision detection
    private float[][] elevationData;

    private final int x;
    private final int y; // x y index of this chunk

    public WorldChunk(int x, int y, MainGame game, WorldGenerator worldGen, World containingWorld) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.worldGen = worldGen;
        this.containingWorld = containingWorld;

        neighboringChunks = new ArrayList<>(8);
        map = new TiledMap();
        elevationData = new float[CHUNK_SIZE][CHUNK_SIZE];

        background = new TiledMapTileLayer(CHUNK_SIZE, CHUNK_SIZE, TILE_SIZE, TILE_SIZE);
        foreground = new TiledMapTileLayer(CHUNK_SIZE, CHUNK_SIZE, TILE_SIZE, TILE_SIZE);

        MapLayers layers = map.getLayers();
        layers.add(background);
        layers.add(foreground);

        for (int yy = 0; yy < CHUNK_SIZE; yy++) {
            for (int xx = 0; xx < CHUNK_SIZE; xx++) {
                float elevationValue = (float) worldGen.getTerrainHeight(xx + getXInUnits(), yy + getYInUnits());
                background.setCell(xx, yy, elevationToTile(elevationValue));
                elevationData[yy][xx] = elevationValue;
            }
        }

        mapRenderer = new OrthogonalTiledMapRenderer(map, 1, MainGame.batch);
    }

    private TiledMapTileLayer.Cell elevationToTile(double height) {
        if (height < -0.25)
            return containingWorld.waterDeepCell;
        else if (height < -0.125)
            return containingWorld.waterShallowCell;
        else if (height < 0.125)
            return containingWorld.sandCell;
        else
            return containingWorld.grassCell;
    }

    public ArrayList<DeltaCell> getDelta() {
        ArrayList<DeltaCell> deltas = new ArrayList<>();
        for (int yy = 0; yy < CHUNK_SIZE; yy++) {
            for (int xx = 0; xx < CHUNK_SIZE; xx++) {
                if (elevationData[yy][xx] != (float) worldGen.getTerrainHeight(xx + getXInUnits(), yy + getYInUnits())) {
                    // save delta to array
                    deltas.add(new DeltaCell(xx, yy, elevationData[yy][xx]));
                }
            }
        }

        return deltas;
    }

    //TODO: determine when and where this method will be called. maybe it will be called by a separate constructor in the future
    //TODO: World class will be handling the loading and unloading of WorldChunks
    public void applyDelta(ArrayList<DeltaCell> deltas) {
        for (DeltaCell delta : deltas) {
            elevationData[delta.y][delta.x] = delta.value;  // update elevation data
            background.setCell(delta.x, delta.y, elevationToTile(delta.value)); // update tilemap
        }
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

    public void renderBackground(OrthographicCamera cam) {
//        mapRenderer.
        cam.translate(-getXInUnits() * TILE_SIZE, -getYInUnits() * TILE_SIZE);
        cam.update();
        mapRenderer.setView(cam);
        mapRenderer.render(backgroundLayers);
        cam.translate(getXInUnits() * TILE_SIZE, getYInUnits() * TILE_SIZE);
        cam.update();
    }

    public void renderForeground() {
        mapRenderer.render(foregroundLayers);
    }
}
