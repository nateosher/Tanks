package sample;

/**
 * 5/28/15.
 */
public class ProjectileModel {
    private int pos_x;
    private int pos_y;
    private double vel_x;
    private double vel_y;
    private int power;

    final int DEFAULT_X = 100;
    final int DEFAULT_Y = 100;
    final int DEFAULT_VEL_X = 0;
    final int DEFAULT_VEL_Y = 0;

    public ProjectileModel() {
        this.pos_x = DEFAULT_X;
        this.pos_y = DEFAULT_Y;
        this.vel_x = DEFAULT_VEL_X;
        this.vel_y = DEFAULT_VEL_Y;
        this.power = power;
    }

    public int getPosX() {
        return this.pos_x;
    }

    public int getPosY() {
        return this.pos_y;
    }

    public void setPosX(int newPosX) {
        this.pos_x = newPosX;
    }

    public void setPosY(int newPosY) {
        this.pos_y = newPosY;
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