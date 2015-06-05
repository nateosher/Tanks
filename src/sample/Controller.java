package sample;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Controller implements EventHandler<KeyEvent> {
    @FXML private Label TankHealth;
    @FXML private Label Fuel;
    @FXML private Label Angle;
    @FXML private Label ShotIntensity;
    @FXML private Slider ShotSlider;
    @FXML private Slider AngleSlider;
    @FXML private AnchorPane AnchorController;
    @FXML private Label Winner;

    private Environment environment;

    public Group TerrainGroup;
    public Group TankGroup;
    public Group ProjectileGroup;



    public Controller() {}

    /*
     * Instantiates an instance of the Environment class, which contains all of
     * the relevant models and views for the terrain/tanks/projectiles.
     */
    public void initialize() {
        this.environment = new Environment(this.TerrainGroup, this.TankGroup,
                this.TankHealth, this.Fuel, this.Angle, this.ShotIntensity,
                this.ShotSlider, this.AngleSlider, this.AnchorController,
                this.Winner);
        this.environment.updateAngleDisplay();
        this.environment.updateHealthDisplay();
        this.environment.updateFuelDisplay();
        this.environment.updateIntensityDisplay();
    }

    /* Handles the key inputs, which work as follows:
     * LEFT ARROW KEY OR A: Moves tank left
     * RIGHT ARROW KEY OR D: Moves tank right
     * UP ARROW KEY OR W: Increases angle of nozzle
     * DOWN ARROW KEY OR S: Decreases angle of nozzle
     * F KEY: Fires a projectile
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
            if ((code == KeyCode.LEFT || code == KeyCode.A)
                    && this.environment.getActiveTankModel().getFuel() > 0) {
                this.environment.getActiveTankModel().moveLeft();
                this.environment.getActiveTankView().update();
                this.environment.updateFuelDisplay();
                keyEvent.consume();
            } else if ((code == KeyCode.RIGHT || code == KeyCode.D)
                    && this.environment.getActiveTankModel().getFuel() > 0) {
                this.environment.getActiveTankModel().moveRight();
                this.environment.getActiveTankView().update();
                this.environment.updateFuelDisplay();
                keyEvent.consume();
            } else if (code == KeyCode.F
                    && this.environment.getProjectileExploded()) {
                this.environment.shootProjectile(this.ProjectileGroup);
                this.environment.swapTanks();
                this.environment.updateAngleDisplay();
                this.environment.updateHealthDisplay();
                this.environment.updateFuelDisplay();
                keyEvent.consume();
            } else if (code == KeyCode.UP || code == KeyCode.W) {
                this.environment.increaseNozzleAngle();
                this.environment.updateAngleDisplay();
                keyEvent.consume();
            } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                this.environment.decreaseNozzleAngle();
                this.environment.updateAngleDisplay();
                keyEvent.consume();
        }
        this.environment.updateIntensityDisplay();
    }

    /* When the fire button is clicked, causes the tank to fire a projectile.
     * Same as pressing the F-key.
     */
    public void onFireButton() {
        if (this.environment.getProjectileExploded()) {
            this.environment.shootProjectile(this.ProjectileGroup);
            this.environment.updateHealthDisplay();
            this.environment.swapTanks();
        }
    }

    /* Method to prevent the sliders from stealing the focus */
    public void onMouseReleased() {
        this.environment.getAnchorController().requestFocus();
    }


}
