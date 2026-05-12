package ua.university;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import ua.university.view.EmbroideryCanvas;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import ua.university.view.EmbroideryEditor;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
        root.setStyle(
                "-fx-background-color: #e8e2cc;"
        );
        EmbroideryCanvas embroideryCanvas =
                new EmbroideryCanvas();

        root.setCenter(embroideryCanvas);

        Button createButton =
                new Button("Створити свою вишиванку");

        Button exitButton =
                new Button("Вийти");
        createButton.setDisable(true);
        exitButton.setDisable(true);

        HBox controls = new HBox(

                20,

                createButton,
                exitButton
        );


        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(20));

        createButton.setPrefSize(250, 45);



        exitButton.setPrefSize(150, 45);

        createButton.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-background-color: darkred;" +
                        "-fx-text-fill: white;"
        );

        exitButton.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-background-color: black;" +
                        "-fx-text-fill: white;"
        );

        controls.setStyle(
                "-fx-background-color: #e8e2cc;"
        );
        exitButton.setOnAction(e -> {

            stage.close();
        });
        createButton.setOnAction(e -> {

            root.setCenter(null);

            EmbroideryEditor editor =
                    new EmbroideryEditor();


            root.setCenter(editor);
            ColorPicker colorPicker =
                    new ColorPicker(Color.DARKRED);

            Button eraserButton =
                    new Button("Eraser");

            Button clearButton =
                    new Button("Очистити");
            Button horizontalButton =
                    new Button("Горизонтальна");

            Button verticalButton =
                    new Button("Вертикальна");

            Button fullButton =
                    new Button("Повна");

            colorPicker.setOnAction(event -> {

                editor.setCurrentColor(
                        colorPicker.getValue()
                );
            });
            HBox editorControls = new HBox(

                    15,

                    colorPicker,

                    horizontalButton,
                    verticalButton,
                    fullButton,

                    eraserButton,
                    clearButton,

                    exitButton
            );


            editorControls.setAlignment(Pos.CENTER);

            editorControls.setPadding(
                    new Insets(20)
            );

            editorControls.setStyle(
                    "-fx-background-color: #e8e2cc;"
            );


            eraserButton.setOnAction(event -> {

                editor.setCurrentColor(
                        Color.BEIGE
                );
            });

            clearButton.setOnAction(event -> {

                editor.clear();
            });
            horizontalButton.setOnAction(event -> {

                editor.setHorizontalSymmetry(true);

                editor.setVerticalSymmetry(false);
            });

            verticalButton.setOnAction(event -> {

                editor.setVerticalSymmetry(true);

                editor.setHorizontalSymmetry(false);
            });

            fullButton.setOnAction(event -> {

                editor.setHorizontalSymmetry(true);

                editor.setVerticalSymmetry(true);
            });

            root.setBottom(editorControls);
        });

        root.setBottom(controls);

        Scene scene = new Scene(root, 900, 700);

        scene.setFill(Color.web("#e8e2cc"));

        stage.setTitle("Vyshyvanka Designer");

        stage.setScene(scene);

        stage.show();
        PauseTransition pause =
                new PauseTransition(Duration.seconds(4.5));

        pause.setOnFinished(e -> {

            createButton.setDisable(false);
            exitButton.setDisable(false);
        });

        pause.play();

    }

    public static void main(String[] args) {
        launch();
    }
}