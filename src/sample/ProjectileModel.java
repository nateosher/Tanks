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

    /* Model of a projectile, including its initial x and y velocities,
    * x and y positions, damage, and blast radius.
    */
    public ProjectileModel(int pos_x, int pos_y, double angle, double intensity) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.vel_x = intensity*Math.cos((angle*Math.PI)/180);
        this.vel_y = -intensity*Math.sin((angle * Math.PI) / 180);
        this.blast_radius = 30;
        this.damage = 30;
        System.out.println("Launched at " + this.pos_x + ", " +this.pos_y);
    }

    public int getPosX() {
        return this.pos_x;
    }

    public int getPosY() {
        return this.pos_y;
    }

    public double getVelX() {
        return this.vel_x;
    }

    public double getVelY() {
        return this.vel_y;
    }

    public void updateCoordinates() {
        updateX();
        updateY();
        System.out.println("Moved to " + this.pos_x + ", " + this.pos_y);
    }

    public void updateX() {
        this.pos_x += this.vel_x;
    }

    public void updateY() {
        this.pos_y += this.vel_y;
        this.vel_y += 9.8;
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

}