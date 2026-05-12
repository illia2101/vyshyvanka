package ua.university;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ua.university.view.EmbroideryCanvas;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Pane root = new Pane();

        EmbroideryCanvas embroideryCanvas =
                new EmbroideryCanvas();

        root.getChildren().add(embroideryCanvas);

        Scene scene = new Scene(root, 900, 700);

        stage.setTitle("Vyshyvanka Designer");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}