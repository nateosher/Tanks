package sample;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
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
    @FXML private Label EndQuote;
    @FXML private Button PlayAgain;
    @FXML private Button QuitButton;


    private Environment environment;

    public Group TerrainGroup;
    public Group TankGroup;
    public Group ProjectileGroup;
    public Group HealthGroup;



    public Controller() {}

    /* Called when the user clicks the start button */
    public void onStartButton() throws Exception {
        initialize();
    }

    public void onQuitButton() throws Exception {
        System.exit(0);
    }

    /*
     * Instantiates an instance of the Environment class, which contains all of
     * the relevant models and views for the terrain/tanks/projectiles.
     */
    public void initialize() {
        this.environment = new Environment(this.TerrainGroup, this.TankGroup,
                this.HealthGroup, this.TankHealth,
                this.Fuel, this.Angle, this.ShotIntensity,
                this.ShotSlider, this.AngleSlider, this.AnchorController,
                this.EndQuote, this.PlayAgain, this.QuitButton);
        this.EndQuote.setVisible(false);
        this.PlayAgain.setVisible(false);
        this.QuitButton.setVisible(false);
        this.environment.updateDisplays();
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
        if(this.environment.isProjectileExploded() && this.environment.isGameRunning()) {
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
            } else if (code == KeyCode.F) {
                this.environment.shootProjectile(this.ProjectileGroup);
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
        } else{
            keyEvent.consume();
        }
    }

    /* When the fire button is clicked, causes the tank to fire a projectile.
     * Same as pressing the F-key.
     */
    public void onFireButton() {
        if(this.environment.isProjectileExploded() && this.environment.isGameRunning()) {
            this.environment.shootProjectile(this.ProjectileGroup);
        }
    }

    /* Method to prevent the sliders from stealing the focus */
    public void onMouseReleased() {
        this.environment.getAnchorController().requestFocus();
    }


}
