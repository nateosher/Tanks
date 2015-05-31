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
    private int[] terrainHeights;
    private double randomFactor;
    final int DEFAULT_WIDTH = 1200;
    final int DEFAULT_HEIGHT = 800;

    public TerrainModel() {
        this.terrainHeights = new int[DEFAULT_WIDTH];
        this.randomNumberGenerator = new Random();
        this.randomFactor = this.randomNumberGenerator.nextDouble();
        makeTerrain();
    }

    private void makeTerrain() {
        for (int i=0; i<DEFAULT_WIDTH; i++) {
            this.terrainHeights[i] = terrainFunction(i);
        }
    }

    private int terrainFunction(int x_pos) {
        double x = (double) x_pos;
        return (int) (((double)DEFAULT_HEIGHT)-((this.randomFactor*30+48)*(Math.cos(x/(randomFactor*100+50)+(randomFactor+.25)*70)+2))-150);
        //if (x_pos < 500)
        //    return 300;
        //else return 350;
    }

    public void destroyChunk(int xCor, int radius) {
        for (int i = (xCor - radius); i <= (xCor + radius); i++) {
            if ((i > 0) && (i < 1200)) {
                int explosionDepth = (int) (Math.sqrt((double) ((radius*radius)-(Math.abs(xCor-i)*Math.abs(xCor-i)))));
                terrainHeights[i] += explosionDepth;
            }
        }
    }

    public int getYPos(int x_pos) {
        return this.terrainHeights[x_pos];
    }
}
