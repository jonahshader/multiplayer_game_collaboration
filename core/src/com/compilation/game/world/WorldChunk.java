package com.compilation.game.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.compilation.game.MainGame;
import com.compilation.game.managers.SqlManager;

import java.util.ArrayList;
import java.util.HashMap;

public class WorldChunk {
    public static final int CHUNK_TILE_SIZE = 64;
    public static final int TILE_SIZE = 64; // size in
    public static final int CHUNK_SIZE = CHUNK_TILE_SIZE * TILE_SIZE;

    private static final int[] backgroundLayers = { 0 };    // list of background layers
    private static final int[] foregroundLayers = { 1 };    // list of foreground layers

    private TiledMap map;
    private MapRenderer mapRenderer;
    private MainGame game;
    private World containingWorld;
    private WorldGenerator worldGen;
    private TiledMapTileLayer background, foreground;

    private HashMap<Long, Entity> entityDictionary;

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

        entityDictionary = new HashMap<>();

        map = new TiledMap();
        elevationData = new float[CHUNK_TILE_SIZE][CHUNK_TILE_SIZE];

        background = new TiledMapTileLayer(CHUNK_TILE_SIZE, CHUNK_TILE_SIZE, TILE_SIZE, TILE_SIZE);
        foreground = new TiledMapTileLayer(CHUNK_TILE_SIZE, CHUNK_TILE_SIZE, TILE_SIZE, TILE_SIZE);

        MapLayers layers = map.getLayers();
        layers.add(background);
        layers.add(foreground);

        for (int yy = 0; yy < CHUNK_TILE_SIZE; yy++) {
            for (int xx = 0; xx < CHUNK_TILE_SIZE; xx++) {
                float elevationValue = (float) worldGen.getTerrainHeight(xx + getXInUnits(), yy + getYInUnits());
                background.setCell(xx, yy, elevationToTile(elevationValue));
                elevationData[yy][xx] = elevationValue;
            }
        }

        mapRenderer = new OrthogonalTiledMapRenderer(map, 1, MainGame.batch);
    }

    private TiledMapTileLayer.Cell elevationToTile(double height) {
        if (height < -0.0)
            return containingWorld.waterDeepCell;
        else if (height < 0.15)
            return containingWorld.waterShallowCell;
        else if (height < 0.22)
            return containingWorld.sandCell;
        else
            return containingWorld.grassCell;
    }

    public ArrayList<DeltaCell> getDelta() {
        ArrayList<DeltaCell> deltas = new ArrayList<>();
        for (int yy = 0; yy < CHUNK_TILE_SIZE; yy++) {
            for (int xx = 0; xx < CHUNK_TILE_SIZE; xx++) {
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

    public boolean SaveDelta(){
        var deltas = getDelta();
        var sqlQuery = "";

        for(var d : deltas){
            sqlQuery += "INSERT INTO Chunk (ChunkX, ChunkY, CellX, CellY, CellValue) VALUES (" + x + ", " + y + ", " + d.x + ", " + d.y + ", " + d.value +") ";
        }

        try
        {
            if(!sqlQuery.equals("")){
                return MainGame.sql.executeSql(sqlQuery);
            }
        }catch(Exception e){
            System.out.println("Failed to save delta: " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean LoadDelta(){
        ArrayList<DeltaCell> deltas = new ArrayList<>();
        try
        {
            var results = MainGame.sql.getTable("EXEC GetChunkByXY " + x + ", " + y);
            for(var row : results){
                deltas.add(new DeltaCell((int)row.get(0), (int)row.get(1), Float.parseFloat(row.get(2).toString())));
            }

            applyDelta(deltas);
        }catch(Exception e){
            System.out.println("Failed to load delta: " + e.getMessage());
            return false;
        }

        return true;
    }

    public int getXInUnits() {
        return x * CHUNK_TILE_SIZE;
    }

    public int getYInUnits() {
        return y * CHUNK_TILE_SIZE;
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

    public HashMap<Long, Entity> getEntityDictionary() {
        return entityDictionary;
    }

    public static String coordToKey(int x, int y) {
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
