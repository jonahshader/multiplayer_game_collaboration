package com.compilation.game;

public class Utils {
    public static double interpolate(double fade, double a, double b) { // returns a when fade = 0, returns b when fade = 1, interpolates between a and b when fade is between 0 and 1
        return (a * (1.0 - fade)) + (b * fade);
    }
}
