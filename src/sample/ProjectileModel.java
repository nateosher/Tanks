package sample;

import java.lang.Math;

/**
 * 5/28/15.
 */
public class ProjectileModel {
    private int pos_x;
    private int pos_y;
    private double vel_x;
    private double vel_y;
    private double blast_radius;
    private double damage;
    private TerrainModel terrainModel;

    /* Model of a projectile, including its initial x and y velocities,
    * x and y positions, damage, and blast radius.
    */
    public ProjectileModel
        (int pos_x, int pos_y, double angle, double intensity) {

        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.vel_x = intensity*Math.cos((angle*Math.PI)/180);
        this.vel_y = -intensity*Math.sin((angle * Math.PI) / 180);
        this.blast_radius = 30;
        this.damage = 30;
    }

    public void setTerrainModel(TerrainModel newTerrainModel)
        { this.terrainModel = newTerrainModel; }

    public int getPosX() { return this.pos_x; }

    public int getPosY() { return this.pos_y; }

    public void setPosX(int new_pos_x) {this.pos_x = new_pos_x;}

    public void setPosY(int new_pos_y) {this.pos_y = new_pos_y;}

    public double getVelX() {
        return this.vel_x;
    }

    public double getVelY() {
        return this.vel_y;
    }

    public void updateCoordinates() {
        updateX();
        updateY();
    }

    public void updateX() {
        this.pos_x += this.vel_x;
    }

    public void updateY() {
        this.pos_y += this.vel_y;
        this.vel_y += 5;
    }

    /* Called by resolveProjectile in Environment. Estimates where the
     * projectile collided with the ground by drawing a path backwards
     * from its current velocity vector. If unable to guess accurately,
     * it will return the first y position that was recognized as below ground.
     */
    public int findImpactPointX() {
        double xToYRatio = -(this.vel_x/this.vel_y);
        double yToXRatio = -(this.vel_y/Math.abs(this.vel_x));

        int i = 0;
        boolean projectileIsUnderground = true;
        int undergroundX = this.pos_x;
        while (projectileIsUnderground && i<1200) {
            undergroundX = (int) (this.pos_x+i*xToYRatio);
            i++;
            if(undergroundX<0 || undergroundX>=1200) {
                return this.pos_x;
            }
            if((this.pos_y+i*yToXRatio) > this.terrainModel.getYPos(
                    undergroundX)){
                projectileIsUnderground=false;
            }
        }
        if (Math.abs(undergroundX-this.pos_x) > this.vel_x) {
            return this.pos_x;
        }
        return undergroundX;
    }

    /*
    * Calculates the damage a tank takes based on it's distance from a hit.
    * This method is called whenever a tank is within the blast radius.
     */
    public double getDamage(int distance) {
        return this.damage*((this.blast_radius-distance)/this.blast_radius)
                + this.damage/2;
    }

    public double getBlastRadius() {
        return this.blast_radius;
    }

    /* Returns true if the projectile is out of the bounds of the screen,
    * and false otherwise.
    */
    public boolean outOfScreen() {
        return (this.getPosX()<0 ||
                this.getPosX()>1199 ||
                this.getPosY()>820);
    }

}