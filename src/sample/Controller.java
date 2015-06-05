package sample;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.*;


public class Controller implements EventHandler<KeyEvent> {
    @FXML private Label TankHealth;
    @FXML private Label Fuel;
    @FXML private Label Angle;
    @FXML private Label ShotIntensity;
    @FXML private Slider ShotSlider;
    @FXML private Label GameOver;

    private Environment environment;

    public Group TerrainGroup;
    public Group TankGroup;
    public Group ProjectileGroup;



    public Controller() {}

    /*
    * Draws the actual terrain, sets the positions of the tanks,
    * and draws them in those positions
    */
    public void initialize() {
        this.environment = new Environment(this.TerrainGroup, this.TankGroup);
        this.environment.updateAngleDisplay(this.Angle);
        this.environment.updateHealthDisplay(this.TankHealth);
        this.environment.updateFuelDisplay(this.Fuel);
        this.environment.updateIntensityDisplay(this.ShotSlider, this.ShotIntensity);
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        if ((code == KeyCode.LEFT || code == KeyCode.A)
                && this.environment.getActiveTankModel().getFuel()>0) {
            this.environment.getActiveTankModel().moveLeft();
            this.environment.getActiveTankView().update();
            this.environment.updateFuelDisplay(this.Fuel);
            keyEvent.consume();
        } else if ((code == KeyCode.RIGHT || code == KeyCode.D)
                && this.environment.getActiveTankModel().getFuel()>0) {
            this.environment.getActiveTankModel().moveRight();
            this.environment.getActiveTankView().update();
            this.environment.updateFuelDisplay(this.Fuel);
            keyEvent.consume();
        } else if (code == KeyCode.F) {
            this.environment.shootProjectile(this.ShotSlider, this.ProjectileGroup);
            this.environment.swapTanks();
            this.environment.updateAngleDisplay(this.Angle);
            this.environment.updateHealthDisplay(this.TankHealth);
            this.environment.updateFuelDisplay(this.Fuel);
            keyEvent.consume();
        } else if (code == KeyCode.UP || code == KeyCode.W){
            this.environment.increaseNozzleAngle();
            this.environment.updateAngleDisplay(this.Angle);
            keyEvent.consume();
        } else if (code == KeyCode.DOWN || code == KeyCode.S){
            this.environment.decreaseNozzleAngle();
            this.environment.updateAngleDisplay(this.Angle);
            keyEvent.consume();
        }
        this.environment.updateIntensityDisplay(this.ShotSlider, this.ShotIntensity);
    }


    public void onFireButton() {
        this.environment.shootProjectile(this.ShotSlider, this.ProjectileGroup);
        this.environment.updateHealthDisplay(this.TankHealth);
        this.environment.swapTanks();
    }


}