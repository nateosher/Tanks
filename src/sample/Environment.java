/**
 * Created by oshern on 6/2/15.
 */

package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import java.util.*;

public class Environment {

    private TerrainModel terrainModel;
    private TerrainView terrainView;
    private TankView activeTankView;
    private TankModel activeTankModel;
    private ProjectileView projectileView;
    private List<TankModel> tankModels;
    private List<TankView> tankViews;
    private int activeTankIndex;
    private Random randomNumberGenerator;
    private double randomFactor;
    private ProjectileModel projectileModel;
    private Timer timer;
    private boolean projectileExploded;
    private Label TankHealth;
    private Label Fuel;
    private Label Angle;
    private Label ShotIntensity;
    private Slider ShotSlider;

    private Label GameOver;
    private Slider AngleSlider;
    private AnchorPane AnchorController;

    public Environment(Group terrainGroup, Group tankGroup, Label tankHealth,
                       Label fuel, Label angle, Label shotIntensity,
                       Slider shotSlider, Slider angleSlider,
                       AnchorPane anchorController){
        this.TankHealth = tankHealth;
        this.Fuel = fuel;
        this.Angle = angle;
        this.ShotIntensity = shotIntensity;
        this.ShotSlider = shotSlider;
        this.AngleSlider = angleSlider;
        this.AnchorController = anchorController;
        this.randomNumberGenerator = new Random();
        this.randomFactor = randomNumberGenerator.nextDouble();

        this.terrainModel = new TerrainModel();
        for (Node node : terrainGroup.getChildren()) {
            this.terrainView = (TerrainView) node;
            this.terrainView.setTerrainModel(this.terrainModel);
            this.terrainView.update();
        }

        this.tankModels = new ArrayList<TankModel>();
        this.tankViews = new ArrayList<TankView>();

        int team = 0;
        for (Node node : tankGroup.getChildren()) {
            TankModel tankModel = new TankModel(team);
            tankModel.setTerrainModel(this.terrainModel);
            tankModel.setNozzleAngle(team*180);

            TankView tankView = (TankView) node;
            tankView.setTankModel(tankModel);
            tankView.updateNozzle();
            tankView.update();


            tankModel.setPositionByX((int) (39 + (team + 1) * (randomFactor) * 580));


            tankView.getBody().setLayoutX(tankModel.getX());
            tankView.getBody().setLayoutY(tankModel.getY());
            tankView.getNozzle().setLayoutX(tankModel.getX() +
                    (tankModel.getWidth() / 2));
            tankView.getNozzle().setLayoutY(tankModel.getY());

            this.tankModels.add(tankModel);
            this.tankViews.add(tankView);

            this.activeTankModel = tankModel;
            this.activeTankView = tankView;
            team++;
        }
        this.activeTankIndex = 1;

        ShotSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                setTankIntensity();
                updateIntensityDisplay();
            }
        });

        AngleSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                setNozzleAngle();
                updateAngleDisplay();
            }
        });
    }

    public TerrainModel getTerrainModel() { return this.terrainModel; }

    public TerrainView getTerrainView() { return this.terrainView; }

    public void setTerrainView(TerrainView newTer) { this.terrainView = newTer; }

    public TankView getActiveTankView() { return this.activeTankView; }

    public TankModel getActiveTankModel() { return this.activeTankModel; }

    public ProjectileView getProjectileView() { return this.projectileView; }

    public ProjectileModel getProjectileModel() { return this.projectileModel; }

    public List<TankModel> getTankModels() { return this.tankModels;}

    public List<TankView> getTankViews() { return this.tankViews; }

    public int getActiveTankIndex() { return this.activeTankIndex; }

    public double getRandomFactor() { return this.randomFactor; }

    public Timer getTimer() { return this.timer; }

    public Boolean getProjectileExploded() { return this.projectileExploded; }

    public AnchorPane getAnchorController() { return this.AnchorController; }

    private void setTankIntensity() {
        this.activeTankModel.setShotIntensity((int) ShotSlider.getValue());
    }


    /*
    * Allows the angle slider to change the angle of a nozzle
     */
    private void setNozzleAngle() {
        this.activeTankModel.setNozzleAngle((int) this.AngleSlider.getValue());
        this.activeTankView.updateNozzle();
        this.activeTankView.update();
    }

    /*
     * Used in response to UP key press. This method increases the angle of the
     * active tank's nozzle, rotating it to the left.
     */
    public void increaseNozzleAngle() {
        if (this.activeTankModel.getNozzleAngle() < 180){
            this.activeTankModel.setNozzleAngle(
                    this.activeTankModel.getNozzleAngle() + 1);
            this.activeTankView.getNozzle().getTransforms()
                    .add(this.activeTankView.getrPos());
            this.activeTankView.getTank().setNozzleY(
                    this.activeTankView.getTank().getNozzleY());
            this.activeTankView.getNozzle().setLayoutY(
                    this.activeTankView.getNozzle().getLayoutY());
        } else{
            this.activeTankView.getTank().setNozzleY(
                    this.activeTankView.getTank().getNozzleY());
            this.activeTankView.getNozzle().setLayoutY(
                    this.activeTankView.getNozzle().getLayoutY());
        }
    }
    /*
     * Used in response to DOWN key press. This method decreases the angle of the
     * active tank's nozzle, rotating it to the right.
     */
    public void decreaseNozzleAngle() {
        if (this.activeTankModel.getNozzleAngle() > 0){
            this.activeTankModel.setNozzleAngle(this.activeTankModel.getNozzleAngle() - 1);
            this.activeTankView.getNozzle().getTransforms().add(this.activeTankView
                    .getrNeg());
            this.activeTankView.getTank().setNozzleY(this.activeTankView.getTank()
                    .getNozzleY());
            this.activeTankView.getNozzle().setLayoutY(this.activeTankView.getNozzle()
                    .getLayoutY());
        } else{
            this.activeTankView.getTank().setNozzleY(this.activeTankView.getTank()
                    .getNozzleY());
            this.activeTankView.getNozzle().setLayoutY(this.activeTankView.getNozzle()
                    .getLayoutY());
        }
    }

    public void updateIntensityDisplay() {
        int shotIntensity = (int) this.ShotSlider.getValue();
        String shotIntensityString = String.format("Shot Intensity: %s",
                Integer.toString(shotIntensity));
        this.ShotIntensity.setText(shotIntensityString);
    }

    public void updateAngleDisplay() {
        int angle = this.activeTankModel.getNozzleAngle();
        String angleString = String.format("Angle: %s", (Integer.toString(angle)));
        this.Angle.setText(angleString);
    }

    public void updateHealthDisplay() {
        double health = this.activeTankModel.getHealth();
        String healthString = String.format("Health: %s", (Double.toString(health)));
        this.TankHealth.setText(healthString);
    }

    public void updateFuelDisplay() {
        this.activeTankModel.getFuel();
        int fuel = this.activeTankModel.getFuel();
        String fuelString = String.format("Fuel Remaining: %s", (fuel));
        this.Fuel.setText(fuelString);
    }

    private void updateDisplays() {
        updateIntensityDisplay();
        updateAngleDisplay();
        updateHealthDisplay();
        updateFuelDisplay();
    }

    /*
    * When a turn is over (after a projectile is fired), the turns are swapped
    * using this method.
    */
    public void swapTanks() {
        isGameOver();
        if (this.activeTankIndex == 0) {
            this.activeTankModel = this.tankModels.get(1);
            this.activeTankView = this.tankViews.get(1);
            this.activeTankIndex = 1;
        } else {
            this.activeTankModel = this.tankModels.get(0);
            this.activeTankView = this.tankViews.get(0);
            this.activeTankIndex = 0;
        }
        updateDisplays();
    }

    private void isGameOver() {
        if (this.activeTankModel.getHealth()<=0) {
            try {
                endGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * Ends the current game by displaying a game over menu. Called when isGameOver
    * is true.
    *
    * Code based on answer here: http://stackoverflow.com/questions/17252401/call-other-class-method-from-controller-in-javafx
    */
    public void endGame() throws Exception {
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameover.fxml"));
        root = (Parent)loader.load();
        Stage stage = new Stage();
        stage.setTitle("Tanks?");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
        root.requestFocus();
    }

    /* Creates and draws a new projectile in the location of the tank that
    * fired and animates it based on the angle and initial power of the
    * shot
    */
    public void shootProjectile(Group projectileGroup) {
        this.projectileExploded= false;
        for (Node node : projectileGroup.getChildren()) {
            this.projectileModel = new ProjectileModel(this.activeTankModel.getX(),this.activeTankModel.getY(),
                    this.activeTankModel.getNozzleAngle(), this.ShotSlider.getValue());
            this.projectileModel.setTerrainModel(this.terrainModel);
            this.projectileView = (ProjectileView) node;
            this.projectileView.createNewProjectile(this.projectileModel);
            this.projectileView.update();
        }
        animateProjectile();
    }

    /* Updates the coordinates of the projectile based on the given path
    * and re-draws it based on this
    */
    public void updateAnimation() {
        this.projectileModel.updateCoordinates();
        this.projectileView.update();
    }

    /* Animates a given projectile. A projectile "explodes" if it has left
    * the screen or if it has collided with the ground
    */
    public void animateProjectile() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if(projectileView.getProjectileModel().outOfScreen()){
                            projectileView.destroyProjectile();
                            projectileExploded=true;
                            cancel();
                        }
                        else {
                            updateAnimation();
                            if(!projectileView.getProjectileModel().outOfScreen()
                                    && !projectileExploded
                                    && projectileModel.getPosY()>terrainModel
                                    .getYPos(projectileModel.getPosX())) {
                                projectileExploded = true;
                                resolveProjectile();
                                cancel();
                            }
                        }
                    }
                });
            }
        };


        long frameTimeInMilliseconds = (long) (1000.0 / 20.0);
        this.timer = new java.util.Timer();
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);

    }

    /* When a projectile explodes, determines whether or not either tank
    * was in the blast radius. If so, it deals damage to the tank
    * based on its distance from the explosion
    *
    * Regardless of whether or not a tank is hit, destroys a chunk of the
    * terrain and removes the projectile from the window.
    */
    public void resolveProjectile() {
        int impactPointX = this.projectileModel.findImpactPointX();
        this.projectileModel.setPosX(impactPointX);
        this.projectileModel.setPosY(terrainModel.getYPos(impactPointX));
        this.terrainModel.destroyChunk(this.projectileModel.getPosX(), (int)
                this.projectileModel.getBlastRadius());
        for (TankModel tank : this.tankModels) {
            double distFromProjectile = Math.sqrt(
                    Math.pow(
                            (tank.getY()+tank.getHeight()) -
                                    this.projectileModel.getPosY(), 2) +
                            Math.pow(tank.getX()+tank.getWidth()/2 - this.projectileModel.getPosX(), 2));
            System.out.println("Tank is " + distFromProjectile + " away");
            if (distFromProjectile < this.projectileModel.getBlastRadius()) {
                tank.takeDamage(this.projectileModel.getDamage((int) distFromProjectile));
            }
            tank.setPositionByX(tank.getX());
        }
        this.projectileView.destroyProjectile();
        for (TankView tankView : this.tankViews) {
            tankView.update();
        }
        this.terrainView.update();
    }





}
