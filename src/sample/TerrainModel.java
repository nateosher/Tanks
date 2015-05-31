/**
 * DiceModel.java
 * Jeff Ondich, 10 Nov 2014
 *
 * A Model class for an MVC demo using JavaFX.
 */

package sample;
import java.util.ArrayList;
import java.util.List;


import java.util.Random;

public class TerrainModel {
    private Random randomNumberGenerator;
    private int[] terrain_heights;
    private double randomFactor;
    final int DEFAULT_WIDTH = 1200;
    final int DEFAULT_HEIGHT = 685;

    public TerrainModel() {
        this.terrain_heights = new int[DEFAULT_WIDTH];
        this.randomNumberGenerator = new Random();
        this.randomFactor = this.randomNumberGenerator.nextDouble();
        makeTerrain();
    }

    private void makeTerrain() {
        for (int i=0; i<DEFAULT_WIDTH; i++) {
            this.terrain_heights[i] = terrainFunction(i);
        }
    }

    private int terrainFunction(int x_pos) {
        double x = (double) x_pos;
        return (int) (((double)DEFAULT_HEIGHT)-((this.randomFactor*30+48)*(Math.cos(x/(randomFactor*100+50)+(randomFactor+.25)*70)+2)));
        //if (x_pos < 500)
        //    return 300;
        //else return 350;
    }

    public int getYPos(int x_pos) {
        return this.terrain_heights[x_pos];
    }
}
