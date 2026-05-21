package ua.university.view;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartMenu extends BorderPane {

    public StartMenu(Stage stage) {

        setStyle(
                "-fx-background-color: #e8e2cc;"
        );

        EmbroideryCanvas embroideryCanvas =
                new EmbroideryCanvas();

        setCenter(embroideryCanvas);

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

        controls.setPadding(
                new Insets(20)
        );

        controls.setStyle(
                "-fx-background-color: #e8e2cc;"
        );

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

        exitButton.setOnAction(e -> {

            stage.close();
        });

        createButton.setOnAction(e -> {

            EmbroideryEditor editor =
                    new EmbroideryEditor(

                            900,
                            600,
                            26
                    );

            setCenter(editor);

            EditorControls editorControls =
                    new EditorControls(
                            editor,
                            stage
                    );

            setBottom(editorControls);
        });

        setBottom(controls);

        PauseTransition pause =
                new PauseTransition(
                        Duration.seconds(4.5)
                );

        pause.setOnFinished(e -> {

            createButton.setDisable(false);

            exitButton.setDisable(false);
        });

        pause.play();
    }
}
