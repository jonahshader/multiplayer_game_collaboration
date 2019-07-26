package com.compilation.game.world;

import com.compilation.game.external.SimplexNoise;

import java.io.Serializable;
import java.util.ArrayList;

public class WorldGenerator implements Serializable {
    public class Octave implements Serializable {
        double scale;       // scale of the noise. smaller number means zoomed out.
        double magnitude;   // magnitude of noise.
        double xOffset;     // offset of noise
        double yOffset;

        public Octave(double scale, double magnitude) {
            this.scale = scale;
            this.magnitude = magnitude;
            xOffset = (Math.random() - 0.5) * Integer.MAX_VALUE * 0.5;
            yOffset = (Math.random() - 0.5) * Integer.MAX_VALUE * 0.5;
        }

        double getValue(long x, long y) {
            double xPos = (x / scale) + xOffset;
            double yPos = (y / scale) + yOffset;
            return SimplexNoise.noise(xPos, yPos) * magnitude;
        }
    }

    public class OctaveSet implements Serializable {
        private ArrayList<Octave> octaves;
        private double masterScale;

        OctaveSet() {
            octaves = new ArrayList<>();
            masterScale = 1.0;
        }

        public double getValue(long x, long y) {
            double height = 0.0;
            for (Octave octave : terrainOctaveSet.octaves)
                height += octave.getValue(x, y);

            return height;
        }
    }

    private OctaveSet terrainOctaveSet, temperatureOctaveSet;

    // default constructor. generates default map settings.
    public WorldGenerator() {
        // create octaves for terrain
        terrainOctaveSet = new OctaveSet();
        terrainOctaveSet.octaves.add(new Octave(300, 1)); // continental

        for (int i = 0; i < 6; i++) {
            terrainOctaveSet.octaves.add(new Octave(Math.pow(0.5, i) * 20, .25 * Math.pow(0.5, i)));
        }

        // create octaves for temperature
        temperatureOctaveSet = new OctaveSet();
        temperatureOctaveSet.octaves.add(new Octave(150, 1));
        temperatureOctaveSet.octaves.add(new Octave(75, 0.4));
    }

    public double getTerrainHeight(long x, long y) {
        return terrainOctaveSet.getValue(x, y);
    }

    public double getTemperature(long x, long y) {
        return temperatureOctaveSet.getValue(x, y);
    }

}
