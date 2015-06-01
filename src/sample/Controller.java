package sample;

import com.sun.javafx.binding.StringFormatter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import java.util.*;


public class Controller implements EventHandler<KeyEvent> {
    @FXML private Label TankHealth;
    @FXML private Label Fuel;

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
    private int Angle;



    public Controller() {
    }

    public void initialize() {

        this.randomNumberGenerator = new Random();
        this.randomFactor = randomNumberGenerator.nextDouble();

        this.terrainModel = new TerrainModel();
        for (Node node : this.TerrainGroup.getChildren()) {
            this.terrainView = (TerrainView) node;
            this.terrainView.setTerrainModel(this.terrainModel);
            this.terrainView.update();
        }

        //this.tankModel = new TankModel();
        //this.tankView = new TankView();
        //this.tankView.setTankModel(this.tankModel);
        //this.tankView.getTank().setTerrainModel(this.terrainModel);
        //this.tankModel = this.tankView.getTank();

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
        // System.out.println("Got here");
        if ((code == KeyCode.LEFT || code == KeyCode.A) && this.activeTankModel.getFuel()>0) {
            //if (this.tankView.getTank().getX() + offset - 5) >= 0)
            this.activeTankModel.moveLeft();
            this.activeTankView.update();
            updateFuelDisplay();
            keyEvent.consume();

        } else if ((code == KeyCode.RIGHT || code == KeyCode.D) && this.activeTankModel.getFuel()>0) {
            // move tank right
            // System.out.println("Right");

            this.activeTankModel.moveRight();
            this.activeTankView.update();
            updateFuelDisplay();
            keyEvent.consume();

            // Just a test to demonstrate destructible terrain
        } else if (code == KeyCode.T) {
            this.terrainView.destroyChunk(500, 50);
            this.terrainView.destroyChunk(750, 20);
            int team = 0;
            for (Node node : this.TankGroup.getChildren()) {
                TankView tankView = (TankView) node;
                tankView.getTank().takeHit(500, 50, 0);
                tankView.getTank().takeHit(750, 20, 0);
                tankView.getBody().setLayoutY(tankView.getTank().getY());
                tankView.update();
                team++;
            }
            keyEvent.consume();

        } else if (code == KeyCode.F) {
            //this.activeTankModel.getX(),this.activeTankModel.getY(),angle,intensity);
            shootProjectile();
            swapTanks();
        } else if (code == KeyCode.Q) {
            System.out.println("my turn");
            if (this.activeTankIndex == 0) {
                this.activeTankModel = this.tankModels.get(1);
                this.activeTankView = this.tankViews.get(1);
                this.activeTankIndex = 1;
            } else {
                this.activeTankModel = this.tankModels.get(0);
                this.activeTankView = this.tankViews.get(0);
                this.activeTankIndex = 0;
            }
        } else if (code == KeyCode.UP || code == KeyCode.W){
            // Rotate nozzle left
            if (this.activeTankModel.getNozzleAngle() < 180){
                this.activeTankModel.setNozzleAngle(this.activeTankModel.getNozzleAngle() + 1);
                this.activeTankView.getNozzle().getTransforms().add(this.activeTankView
                        .getrPos());
                this.activeTankView.getTank().setNozzleY(this.activeTankView.getTank()
                        .getNozzleY());
                this.activeTankView.getNozzle().setLayoutY(this.activeTankView.getNozzle()
                        .getLayoutY());
                keyEvent.consume();
            } else{
                this.activeTankView.getTank().setNozzleY(this.activeTankView.getTank()
                        .getNozzleY());
                this.activeTankView.getNozzle().setLayoutY(this.activeTankView.getNozzle()
                        .getLayoutY());
                keyEvent.consume();
            }


        } else if (code == KeyCode.DOWN || code == KeyCode.S){
            // Rotate nozzle left
            if (this.activeTankModel.getNozzleAngle() > 0){
                this.activeTankModel.setNozzleAngle(this.activeTankModel.getNozzleAngle() - 1);
                this.activeTankView.getNozzle().getTransforms().add(this.activeTankView
                        .getrNeg());
                this.activeTankView.getTank().setNozzleY(this.activeTankView.getTank()
                        .getNozzleY());
                this.activeTankView.getNozzle().setLayoutY(this.activeTankView.getNozzle()
                        .getLayoutY());
                keyEvent.consume();
            } else{
                this.activeTankView.getTank().setNozzleY(this.activeTankView.getTank()
                        .getNozzleY());
                this.activeTankView.getNozzle().setLayoutY(this.activeTankView.getNozzle()
                        .getLayoutY());
                keyEvent.consume();
            }
    }
//        int testXCor = this.activeTankModel.getX();
//        int testYCor = this.activeTankModel.getY();
//        String testString = String.format("Tank x cor: %s, Tank y cor: %s", (testXCor), (testYCor));
//        System.out.println(testString) ;

//        int nzang = this.activeTankModel.getNozzleAngle();
//        String testString = String.format("Nozzle angle: %s", (nzang));
//        System.out.println(testString) ;
//        keyEvent.consume();
//        int testXCor = this.activeTankModel.getX();
//        int testYCor = this.activeTankModel.getY();
//        String testString = String.format("Tank x cor: %s, Tank y cor: %s", (testXCor), (testYCor));
//        System.out.println(testString);

        }

    public void onFireButton() {
        shootProjectile();
        swapTanks();
    }

    public void updateHealthDisplay() {
        double health = this.activeTankModel.getHealth();
        String healthString = String.format("Health: %s", (Double.toString(health)));
        TankHealth.setText(healthString);
    }

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
        updateFuelDisplay();
        updateHealthDisplay();
    }

    public void shootProjectile() {
        System.out.println("pew!");
        this.projectileExploded= false;
        for (Node node : this.ProjectileGroup.getChildren()) {
            this.projectileModel = new ProjectileModel(this.activeTankModel.getX(),this.activeTankModel.getY(),
                    80, 50);
            this.projectileView = (ProjectileView) node;
            this.projectileView.createNewProjectile(this.projectileModel);
            this.projectileView.update();
        }
        animateProjectile();
    }

    public void updateAnimation() {
        this.projectileModel.updateCoordinates();
        this.projectileView.update();
    }

    public void animateProjectile() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if(projectileView.outOfScreen()){
                            projectileView.destroyProjectile();
                            projectileExploded=true;
                            cancel();
                        }
                        else {
                        updateAnimation();
                        if(projectileView.outOfScreen()==false && projectileExploded == false
                            && projectileModel.getPosY()>terrainModel.getYPos(projectileModel.getPosX())
                                ) {
                            projectileExploded = true;
                            System.out.println(projectileModel.getPosY() + "and " + terrainModel.getYPos(projectileModel.getPosX()));
                            System.out.println("KABLAM");
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

    public void resolveProjectile() {

        for (TankModel tank : this.tankModels) {
            if (Math.abs(tank.getX()-this.terrainModel.getYPos( this.projectileModel.getPosX() )) <
                    this.projectileModel.getBlastRadius()) {
                tank.takeDamage(this.projectileModel.getDamage(
                        Math.abs(tank.getX()-this.terrainModel.getYPos( this.projectileModel.getPosX() ))));
            }

        }
        this.projectileView.destroyProjectile();
        this.terrainModel.destroyChunk(this.projectileModel.getPosX(), (int) this.projectileModel.getBlastRadius());
        this.terrainView.update();
    }

}

