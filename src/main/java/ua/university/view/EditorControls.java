package ua.university.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class EditorControls extends VBox {

    public EditorControls(
            EmbroideryEditor editor,
            Stage stage
    ) {

        ColorPicker colorPicker =
                new ColorPicker(Color.DARKRED);
        ComboBox<String> cellSizeBox =
                new ComboBox<>();

        cellSizeBox.getItems().addAll(

                "Small",
                "Medium",
                "Large"
        );


        cellSizeBox.setValue("Medium");

        Button horizontalButton =
                new Button("Горизонтальна");

        Button verticalButton =
                new Button("Вертикальна");

        Button fullButton =
                new Button("Повна");

        Button drawModeButton =
                new Button("Режим малювання");

        Button saveButton =
                new Button("Зберегти");

        Button openButton =
                new Button("Відкрити");

        Button eraserButton =
                new Button("Гумка");

        Button clearButton =
                new Button("Очистити");

        Button exitButton =
                new Button("Вийти");

        colorPicker.setOnAction(event -> {

            editor.setCurrentColor(
                    colorPicker.getValue()
            );
        });
        cellSizeBox.setOnAction(event -> {

            String selected =
                    cellSizeBox.getValue();

            if (selected.equals("Small")) {

                editor.setCellSize(14);

            } else if (selected.equals("Medium")) {

                editor.setCellSize(20);

            } else {

                editor.setCellSize(32);
            }
        });

        eraserButton.setOnAction(event -> {

            editor.setCurrentColor(
                    Color.BEIGE
            );
        });

        clearButton.setOnAction(event -> {

            editor.clear();
        });

        drawModeButton.setOnAction(event -> {

            editor.setHorizontalSymmetry(false);

            editor.setVerticalSymmetry(false);
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

        saveButton.setOnAction(event -> {

            FileChooser chooser =
                    new FileChooser();

            chooser.setTitle(
                    "Зберегти вишиванку"
            );

            chooser.getExtensionFilters().add(

                    new FileChooser.ExtensionFilter(
                            "PNG files",
                            "*.png"
                    )
            );

            File file =
                    chooser.showSaveDialog(stage);

            if (file != null) {

                try {

                    editor.saveToPNG(file);

                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
            if (editor.isEmpty()) {

                Alert alert = new Alert(
                        Alert.AlertType.WARNING
                );

                alert.setTitle("Помилка");

                alert.setHeaderText(null);

                alert.setContentText(
                        "Полотно порожнє!"
                );

                alert.showAndWait();

                return;
            }
        });

        openButton.setOnAction(event -> {

            FileChooser chooser =
                    new FileChooser();

            chooser.setTitle(
                    "Відкрити схему"
            );

            chooser.getExtensionFilters().add(

                    new FileChooser.ExtensionFilter(
                            "PNG files",
                            "*.png"
                    )
            );

            File file =
                    chooser.showOpenDialog(stage);

            if (file != null) {

                try {

                    editor.openPNG(file);

                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        });

        exitButton.setOnAction(event -> {

            stage.close();
        });

        HBox topControls = new HBox(

                15,

                colorPicker,

                horizontalButton,
                verticalButton,
                cellSizeBox,
                fullButton,
                drawModeButton
        );

        HBox bottomControls = new HBox(

                20,

                saveButton,
                openButton,
                eraserButton,
                clearButton,
                exitButton
        );

        topControls.setAlignment(Pos.CENTER);

        bottomControls.setAlignment(Pos.CENTER);

        topControls.setPadding(
                new Insets(10)
        );

        bottomControls.setPadding(
                new Insets(0, 0, 20, 0)
        );

        getChildren().addAll(

                topControls,
                bottomControls
        );

        setSpacing(5);

        setPadding(
                new Insets(0, 0, 20, 0)
        );

        setStyle(
                "-fx-background-color: #e8e2cc;"
        );
    }
}
