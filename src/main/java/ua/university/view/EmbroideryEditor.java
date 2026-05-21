package ua.university.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

import java.io.FileInputStream;

import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class EmbroideryEditor extends Canvas {

    private int cellSize;

    private boolean horizontalSymmetry = false;

    private boolean verticalSymmetry = false;

    private Color currentColor = Color.DARKRED;

    private Color[][] grid;

    public EmbroideryEditor(
            int width,
            int height,
            int cellSize
    ) {

        super(width, height);

        this.cellSize = cellSize;

        grid = new Color[
                (int)getHeight() / cellSize
                ][
                (int)getWidth() / cellSize
                ];

        drawBackground();

        setOnMouseClicked(this::handleClick);
    }

    public void saveToPNG(File file)
            throws IOException {

        WritableImage image =
                snapshot(null, null);

        ImageIO.write(

                SwingFXUtils.fromFXImage(
                        image,
                        null
                ),

                "png",

                file
        );
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

        for (int row = 0; row < getHeight() / cellSize; row++) {

            for (int col = 0; col < getWidth() / cellSize; col++) {

                gc.strokeRect(

                        col * cellSize,
                        row * cellSize,

                        cellSize,
                        cellSize
                );
            }
        }
    }

    private void handleClick(MouseEvent event) {

        int col =
                (int)event.getX() / cellSize;

        int row =
                (int)event.getY() / cellSize;
        if (row >= grid.length ||
                col >= grid[0].length ||
                row < 0 ||
                col < 0) {

            return;
        }

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
    public void openPNG(File file)
            throws Exception {

        Image image =
                new Image(
                        new FileInputStream(file)
                );

        PixelReader reader =
                image.getPixelReader();

        clear();

        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[row].length; col++) {

                int x =
                        col * cellSize + cellSize / 2;

                int y =
                        row * cellSize + cellSize / 2;

                Color color =
                        reader.getColor(x, y);

                if (color.equals(Color.BEIGE) ||
                        color.equals(Color.rgb(220, 210, 190))) {

                    continue;
                }

                grid[row][col] = color;

                drawCell(row, col, color);
            }
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

                col * cellSize,
                row * cellSize,

                cellSize - 1,
                cellSize - 1
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
    public boolean isEmpty() {

        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[row].length; col++) {

                if (grid[row][col] != null) {

                    return false;
                }
            }
        }

        return true;
    }
    public void setCellSize(int size) {

        Color[][] oldGrid = grid;

        this.cellSize = size;

        grid = new Color[
                (int)getHeight() / cellSize
                ][
                (int)getWidth() / cellSize
                ];

        for (int row = 0; row < oldGrid.length; row++) {

            for (int col = 0; col < oldGrid[row].length; col++) {

                if (row < grid.length &&
                        col < grid[row].length) {

                    grid[row][col] =
                            oldGrid[row][col];
                }
            }
        }

        drawBackground();

        redrawGrid();
    }
    private void redrawGrid() {

        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[row].length; col++) {

                if (grid[row][col] != null) {

                    drawCell(

                            row,
                            col,
                            grid[row][col]
                    );
                }
            }
        }
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
