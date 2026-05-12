package ua.university.service;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class OrnamentGenerator {

    private static final int CELL = 26;


    private static final int[][] PATTERN = {

            {0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1},
            {0,0,1,1,0,0,1,0,0,0,1,0,0,1,1,0},
            {0,1,1,1,0,0,0,1,0,1,0,0,0,1,1,1},

            {0,0,0,0,2,0,1,0,1,0,1,0,2,0,0,0},
            {0,0,0,0,0,2,0,1,0,1,0,2,0,0,0,0},

            {0,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0},
            {0,0,0,1,0,1,0,2,0,2,0,1,0,1,0,0},

            {0,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0},
            {0,0,0,1,0,1,0,2,0,2,0,1,0,1,0,0},

            {0,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0},
            {0,0,0,0,0,2,0,1,0,1,0,2,0,0,0,0},

            {0,0,0,0,2,0,1,0,1,0,1,0,2,0,0,0},

            {0,1,1,1,0,0,0,1,0,1,0,0,0,1,1,1},
            {0,0,1,1,0,0,1,0,0,0,1,0,0,1,1,0},

            {0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1}


    };


    private static void drawPattern(
            GraphicsContext gc,
            int startX,
            int startY
    ) {

        Timeline timeline = new Timeline();

        int delay = 0;

        for (int row = 0; row < PATTERN.length; row++) {

            for (int col = 0; col < PATTERN[row].length; col++) {

                int value = PATTERN[row][col];

                if (value == 0) {
                    continue;
                }

                int x = startX + col * CELL;
                int y = startY + row * CELL;

                Color color;

                if (value == 1) {

                    color = Color.DARKRED;

                } else {

                    color = Color.BLACK;
                }

                KeyFrame keyFrame = new KeyFrame(

                        Duration.millis(delay),

                        e -> drawCell(
                                gc,
                                x,
                                y,
                                color
                        )
                );

                timeline.getKeyFrames().add(keyFrame);

                delay += 60;
            }
        }

        timeline.play();
    }

    private static void drawCell(
            GraphicsContext gc,
            int x,
            int y,
            Color color
    ) {

        gc.setFill(color);

        gc.fillRect(
                x,
                y + 2,
                CELL - 1,
                CELL - 1
        );
    }
    public static void drawOrnament(
            GraphicsContext gc
    ) {

        int patternWidth = PATTERN[0].length * CELL;
        int patternHeight = PATTERN.length * CELL;

        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        int startX =
                (int)((canvasWidth - patternWidth) / 2);

        int startY =
                (int)((canvasHeight - patternHeight) / 2 - 100);
        startX = (startX / CELL) * CELL;
        startY = (startY / CELL) * CELL;

        drawPattern(gc, startX, startY + 50);

        drawName(
                gc,
                startY - 480,
                patternHeight
        );
    }

    private static void drawName(
            GraphicsContext gc,
            int startY,
            int patternHeight
    ) {

        gc.setFill(Color.BLACK);

        gc.setFont(new Font(40));

        String text = "І Л Л Я";

        double textWidth =
                text.length() * 20;
        double canvasWidth = gc.getCanvas().getWidth();
        double textX =
                ((canvasWidth - textWidth) / 2);

        textX = ((int)textX / CELL) * CELL;

        gc.fillText(
                text,
                textX + 32,
                startY + patternHeight + 115
        );
    }
}
