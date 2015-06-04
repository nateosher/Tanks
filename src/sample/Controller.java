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

    public Group TerrainGroup;
    private TerrainModel terrainModel;
    private TerrainView terrainView;
    public Group TankGroup;
    private TankView activeTankView;
    private TankModel activeTankModel;
    public Group ProjectileGroup;
    private ProjectileView projectileView;
    private List<TankModel> tankModels;
    private List<TankView> tankViews;
    private int activeTankIndex;
    private Random randomNumberGenerator;
    private double randomFactor;
    private ProjectileModel projectileModel;
    private Timer timer;
    private boolean projectileExploded;


    public Controller() {
    }

    /*
    * Draws the actual terrain, sets the positions of the tanks,
    * and draws them in those positions
    */
    public void initialize() {
        this.randomNumberGenerator = new Random();
        this.randomFactor = randomNumberGenerator.nextDouble();

        this.terrainModel = new TerrainModel();
        for (Node node : this.TerrainGroup.getChildren()) {
            this.terrainView = (TerrainView) node;
            this.terrainView.setTerrainModel(this.terrainModel);
            this.terrainView.update();
        }

        this.tankModels = new ArrayList<TankModel>();
        this.tankViews = new ArrayList<TankView>();

        int team = 0;
        for (Node node : this.TankGroup.getChildren()) {
            TankModel tankModel = new TankModel(team);
            tankModel.setTerrainModel(this.terrainModel);

            TankView tankView = (TankView) node;
            tankView.setTankModel(tankModel);
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
    }

    public void updateFuelDisplay() {
        this.activeTankModel.getFuel();
        int fuel = this.activeTankModel.getFuel();
        String fuelString = String.format("Fuel Remaining: %s", (fuel));
        this.Fuel.setText(fuelString);
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        if ((code == KeyCode.LEFT || code == KeyCode.A)
                && this.activeTankModel.getFuel()>0) {
            this.activeTankModel.moveLeft();
            this.activeTankView.update();
            updateFuelDisplay();
            keyEvent.consume();
        } else if ((code == KeyCode.RIGHT || code == KeyCode.D)
                && this.activeTankModel.getFuel()>0) {
            this.activeTankModel.moveRight();
            this.activeTankView.update();
            updateFuelDisplay();
            keyEvent.consume();
        } else if (code == KeyCode.F) {
            shootProjectile();
            swapTanks();
            keyEvent.consume();
        } else if (code == KeyCode.UP || code == KeyCode.W){
            increaseNozzleAngle();
            updateAngleDisplay();
            keyEvent.consume();
        } else if (code == KeyCode.DOWN || code == KeyCode.S){
            decreaseNozzleAngle();
            updateAngleDisplay();
            keyEvent.consume();
        }
        updateIntensityDisplay();
    }

    /*
    * Used in response to UP key press. This method increases the angle of the
    * active tank's nozzle, rotating it to the left.
     */
    private void increaseNozzleAngle() {
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
    private void decreaseNozzleAngle() {
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

    public void onFireButton() {
        shootProjectile();
        swapTanks();
    }

    private void updateIntensityDisplay() {
        int shotIntensity = (int) ShotSlider.getValue();
        String shotIntensityString = String.format("Shot Intensity: %s",
                Integer.toString(shotIntensity));
        ShotIntensity.setText(shotIntensityString);
    }

    private void updateAngleDisplay() {
        int angle = this.activeTankModel.getNozzleAngle();
        String angleString = String.format("Angle: %s", (Integer.toString(angle)));
        Angle.setText(angleString);
    }

    private void updateHealthDisplay() {
        double health = this.activeTankModel.getHealth();
        String healthString = String.format("Health: %s", (Double.toString(health)));
        TankHealth.setText(healthString);
    }

    /*
    * When a turn is over (after a projectile is fired), the turns are swapped
    * using this method.
    */
    public void swapTanks() {
        if (this.activeTankIndex == 0) {
            this.activeTankModel = this.tankModels.get(1);
            this.activeTankView = this.tankViews.get(1);
            this.activeTankIndex = 1;
        } else {
            this.activeTankModel = this.tankModels.get(0);
            this.activeTankView = this.tankViews.get(0);
            this.activeTankIndex = 0;
        }
        isGameOver();
        updateAngleDisplay();
        updateHealthDisplay();
        updateFuelDisplay();
    }

    private void isGameOver() {
        if (this.activeTankModel.getHealth()<=0) {
            System.out.println("Game Over");
            //this.GameOver.setText(String.format("GAME OVER!!!"));
        }
    }

    /* Creates and draws a new projectile in the location of the tank that
    * fired and animates it based on the angle and initial power of the
    * shot
    */
    public void shootProjectile() {
        System.out.println("pew!");
        this.projectileExploded= false;
        for (Node node : this.ProjectileGroup.getChildren()) {
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
                updateHealthDisplay();
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

