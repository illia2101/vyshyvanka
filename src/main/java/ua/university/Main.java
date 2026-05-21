package ua.university;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ua.university.view.StartMenu;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        StartMenu startMenu =
                new StartMenu(stage);

        Scene scene =
                new Scene(startMenu, 900, 700);

        stage.setTitle("Vyshyvanka Designer - Ілля Кабиш");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}