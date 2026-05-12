package ua.university.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ua.university.service.OrnamentGenerator;

public class EmbroideryCanvas extends Canvas {
    private static final int CELL = 26;
    public EmbroideryCanvas() {

        super(900, 700);

        GraphicsContext gc =
                getGraphicsContext2D();

        drawBackground(gc);

        OrnamentGenerator.drawOrnament(gc);
    }

    private void drawBackground(
            GraphicsContext gc
    ) {

        gc.setFill(Color.BEIGE);

        gc.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );


        gc.setStroke(Color.rgb(220, 210, 190));


        for (int row = 0; row < getHeight() / CELL; row++) {

            for (int col = 0; col < getWidth() / CELL; col++) {

                gc.strokeRect(
                        col * CELL,
                        row * CELL,
                        CELL,
                        CELL
                );
            }
        }
    }
}
