/**
 * TerrainModel
 *
 * Nate Osher, Ben Wedin, Aidan Halloway-Bidwell, Raven Pillmann
 *
 * A Model class for the destructible terrain
 *
 * Based On:
 * DiceModel.java
 * Jeff Ondich, 10 Nov 2014
 *
 */

package sample;

import java.util.Random;

public class TerrainModel {
    private Random randomNumberGenerator;
    private int[] terrainHeights;
    private double randomFactor;
    final int DEFAULT_WIDTH = 1200;
    final int DEFAULT_HEIGHT = 800;

    public TerrainModel() {
        /* Constructor for TerrainModel class. Creates an array of 1200 integers
         * that represent the heights of the terrain at a given x-value.
         *
         * Also creates a random number for the purpose of placing the tanks.
         */

        this.terrainHeights = new int[DEFAULT_WIDTH];
        this.randomNumberGenerator = new Random();
        this.randomFactor = this.randomNumberGenerator.nextDouble();
        makeTerrain();
    }

    private void makeTerrain() {
        /* Sets the indices of this.terrainHeights to integers based on output
         * of terrainFunction
         */
        for (int i=0; i<DEFAULT_WIDTH; i++) {
            this.terrainHeights[i] = terrainFunction(i);
        }
    }

    private int terrainFunction(int x_pos) {
        /* Generates heights for the terrain based on the Cos() function */
        double x = (double) x_pos;
        return (int) (((double)DEFAULT_HEIGHT)-((this.randomFactor*30+48)*(Math.cos(x/(randomFactor*100+50)+(randomFactor+.25)*70)+2))-150);
    }

    public void destroyChunk(int xCor, int radius) {
        /* Destroys a chunk of terrain by decreasing the heights of the terrain
         * for all terrain calculated to have been caught in the explosion.
         */
        for (int i = (xCor - radius); i <= (xCor + radius); i++) {
            if ((i >= 0) && (i < 1200)) {
                int explosionDepth = (int) (Math.sqrt((double) ((radius*radius)-(Math.abs(xCor-i)*Math.abs(xCor-i)))));
                terrainHeights[i] += explosionDepth;
            }
        }
    }

    public int getYPos(int x_pos) {
        return this.terrainHeights[x_pos];
    }
}
