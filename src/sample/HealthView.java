package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

/**
 * Created by hollowaya on 6/5/15.
 */
public class HealthView extends Group {


    private List<TankModel> tankModels;

    public HealthView() {
    }

    public void setTankModels(List<TankModel> tankModels) {
        this.tankModels = tankModels;
    }

    public void update() {
        this.getChildren().clear();
        for (int i=0; i<this.tankModels.size(); i++) {
            TankModel tank = this.tankModels.get(i);
            Rectangle maxHealthBar = new Rectangle
                    (773, (i*20)-2,
                            154,14);
            Rectangle healthBar = new Rectangle
                    (775, (i*20),
                            (int) tank.getHealth()*1.5,10);
            healthBar.setFill(tank.getTankCol());
            maxHealthBar.setFill(Color.GRAY);
            this.getChildren().add(maxHealthBar);
            this.getChildren().add(healthBar);
        }
    }
}
