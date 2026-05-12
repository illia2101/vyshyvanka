package ua.university.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EmbroideryEditor extends Canvas {

    private static final int CELL = 26;

    private boolean horizontalSymmetry = false;

    private boolean verticalSymmetry = false;

    private Color currentColor = Color.DARKRED;

    private final Color[][] grid;

    public EmbroideryEditor() {

        super(900, 600);

        grid = new Color[
                (int)getHeight() / CELL
                ][
                (int)getWidth() / CELL
                ];

        drawBackground();

        setOnMouseClicked(this::handleClick);
    }

    private void drawBackground() {

        GraphicsContext gc =
                getGraphicsContext2D();

        gc.setFill(Color.BEIGE);

        gc.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        gc.setStroke(
                Color.rgb(220, 210, 190)
        );

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

    private void handleClick(MouseEvent event) {

        int col =
                (int)event.getX() / CELL;

        int row =
                (int)event.getY() / CELL;

        drawAndSave(row, col);

        if (horizontalSymmetry) {

            int mirrorCol =
                    grid[0].length - 1 - col;

            drawAndSave(row, mirrorCol);
        }

        if (verticalSymmetry) {

            int mirrorRow =
                    grid.length - 1 - row;

            drawAndSave(mirrorRow, col);
        }

        if (horizontalSymmetry && verticalSymmetry) {

            int mirrorCol =
                    grid[0].length - 1 - col;

            int mirrorRow =
                    grid.length - 1 - row;

            drawAndSave(mirrorRow, mirrorCol);
        }
    }
    private void drawAndSave(
            int row,
            int col
    ) {

        grid[row][col] = currentColor;

        drawCell(
                row,
                col,
                currentColor
        );
    }

    private void drawCell(
            int row,
            int col,
            Color color
    ) {

        GraphicsContext gc =
                getGraphicsContext2D();

        gc.setFill(color);

        gc.fillRect(

                col * CELL,
                row * CELL,

                CELL - 1,
                CELL - 1
        );
    }

    public void setCurrentColor(Color color) {

        currentColor = color;
    }

    public void clear() {

        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[row].length; col++) {

                grid[row][col] = null;
            }
        }

        drawBackground();
    }
    public void setHorizontalSymmetry(
            boolean value
    ) {

        horizontalSymmetry = value;
    }

    public void setVerticalSymmetry(
            boolean value
    ) {

        verticalSymmetry = value;
    }
}
