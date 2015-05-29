package sample;

/**
 * 5/28/15.
 */
public class ProjectileModel {
    private double pos_x;
    private double pos_y;
    private double vel_x;
    private double vel_y;
    private int power;

    public ProjectileModel(double pos_x, double pos_y, double vel_x, double vel_y, int power) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.vel_x = vel_x;
        this.vel_y = vel_y;
        this.power = power;
    }

    public double getPosX() {
        return this.pos_x;
    }

    public double getPosY() {
        return this.pos_y;
    }

    public double getVelX() {
        return this.vel_x;
    }

    public double getVelY() {
        return this.vel_y;
    }

    public void updatePosX() {
        this.pos_x += this.vel_x;
    }

    public void updatePosY() {
        this.pos_y += this.vel_y;
    }

    public void updateVelYWithTime(int sec) {
        this.vel_y = -9.8 * sec + this.vel_y;
    }
}