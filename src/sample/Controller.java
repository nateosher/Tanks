package sample;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class Controller {
    public Group terrainGroup;
    private TerrainModel terrainModel;

    public Controller() {
    }

    public void initialize() {
        for (Node node : this.terrainGroup.getChildren()) {
            System.out.println("hi");
            //TerrainView terrain_view = (TerrainView)node;
            //this.terrainModel = new TerrainModel();
            //terrain_view.setTerrainModel(this.terrainModel);
            //terrain_view.update();
        }
    }
}
