package sample;

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


            tankModel.setPositionByX((int) (40 + (team + 1) * (randomFactor) * 580));

            tankView.getBody().setLayoutX(tankModel.getX());
            tankView.getBody().setLayoutY(tankModel.getY());

            this.tankModels.add(tankModel);
            this.tankViews.add(tankView);

            this.activeTankModel = tankModel;
            this.activeTankView = tankView;
            team++;
        }
        this.activeTankIndex = 1;
    }

    public ProjectileView initializeProjectile(TankModel tank) {
        ProjectileView projectile = new ProjectileView();
        projectile.getProjectileModel().setPosX(tank.getX());
        projectile.getProjectileModel().setPosY(tank.getY());
        projectile.getProjectileModel().setVelX((int) (Math.cos((double) tank.getPower())));
        projectile.getProjectileModel().setVelY((int) (Math.sin((double) tank.getPower())));
        return projectile;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        // System.out.println("Got here");
        if (code == KeyCode.LEFT || code == KeyCode.A) {
            //if (this.tankView.getTank().getX() + offset - 5) >= 0)
            this.activeTankModel.moveLeft();
            this.activeTankView.getBody().setLayoutX(this.activeTankModel.getX());
            this.activeTankView.getBody().setLayoutY(this.activeTankModel.getY());
            keyEvent.consume();

        } else if (code == KeyCode.RIGHT || code == KeyCode.D) {
            // move tank right
            // System.out.println("Right");
            this.activeTankModel.moveRight();
            this.activeTankView.getBody().setLayoutX(this.activeTankModel.getX());
            this.activeTankView.getBody().setLayoutY(this.activeTankModel.getY());
            keyEvent.consume();

            // Just a test to demonstrate destructible terrain
        } else if (code == KeyCode.T) {
            this.terrainView.destroyChunk(500, 50);
            this.terrainView.destroyChunk(750, 20);

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
        }
        keyEvent.consume();
        int testXCor = this.activeTankModel.getX();
        int testYCor = this.activeTankModel.getY();
        String testString = String.format("Tank x cor: %s, Tank y cor: %s", (testXCor), (testYCor));
        System.out.println(testString);

    }

    public void swapTanks() {
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
    }

    public void shootProjectile() {
        System.out.println("pew!");
        for (Node node : this.ProjectileGroup.getChildren()) {
            System.out.println("there's a projectile node!");
            this.projectileModel = new ProjectileModel(this.activeTankModel.getX(),this.activeTankModel.getY(),
                    90, 4);
            this.projectileView = (ProjectileView) node;
            this.projectileView.setProjectileModel(this.projectileModel);
            this.projectileView.update();
            //create projectile
            //gravity
            //collide with terrain
            //collide with tanks?
            //blow up stuff
            System.out.println("boom");
        }
        animateProjectile();
        resolveProjectile();
    }

    public void animateProjectile() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (projectileModel.getPosY()>terrainModel.getYPos(projectileModel.getPosX())) {
                            projectileModel.updateCoordinates();
                            //draw it somehow
                        }
                        else {
                            ;
                        }
                        }
                    });
                }

            };

        Timer timer = new java.util.Timer();
        // long frameTimeInMillisceonds = (long) (1000.0 / FRAMES_PER_SECOND)
        //
        timer.schedule(timerTask, 0, (long) 100);
    }

    public void resolveProjectile() {

        for (TankModel tank : this.tankModels) {
            if (Math.abs(tank.getX()-this.terrainModel.getYPos( this.projectileModel.getPosX() )) <
                    this.projectileModel.getBlastRadius()) {
                tank.takeDamage(this.projectileModel.getDamage(
                        Math.abs(tank.getX()-this.terrainModel.getYPos( this.projectileModel.getPosX() ))));
            }

        }
        this.terrainModel.destroyChunk(this.projectileModel.getPosX(), (int) this.projectileModel.getBlastRadius());
    }
}

