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

    public TerrainModel() {
        this.terrain_heights = new int[800];
        makeTerrain();
        this.randomNumberGenerator = new Random();
    }

    private void makeTerrain() {
        for (int i=0; i<800; i++) {
            this.terrain_heights[i]=terrainFunction(i);
        }
    }

    private int terrainFunction(int x_pos) {
        return 500;
    }

    public int getYPos(int x_pos) {
        return this.terrain_heights[x_pos];
    }
}
