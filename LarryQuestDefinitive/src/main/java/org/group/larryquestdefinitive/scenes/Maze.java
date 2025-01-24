package org.group.larryquestdefinitive.scenes;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.group.larryquestdefinitive.scenes.GameScene;

public class Maze {
    private final int rows;
    private final int cols;
    private final boolean[][] maze;

    public Maze(int size) {
        this.rows = size * 2 + 1;
        this.cols = size * 2 + 1;
        this.maze = new boolean[rows][cols];
        generateMaze();
    }

    private void generateMaze() {
        // Fill with walls
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = false;
            }
        }

        // Start DFS from the entrance
        carvePath(1, 1);

        // Create entrance and exit
        maze[1][0] = true; // Entrance
        maze[rows - 2][cols - 1] = true; // Exit
    }

    private void carvePath(int row, int col) {
        maze[row][col] = true;

        // Randomize directions
        int[][] directions = {{0, 2}, {0, -2}, {2, 0}, {-2, 0}};
        for (int i = 0; i < directions.length; i++) {
            int[] temp = directions[i];
            int randomIndex = (int) (Math.random() * directions.length);
            directions[i] = directions[randomIndex];
            directions[randomIndex] = temp;
        }

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (isValidCell(newRow, newCol)) {
                maze[row + direction[0] / 2][col + direction[1] / 2] = true;
                carvePath(newRow, newCol);
            }
        }
    }

    private boolean isValidCell(int row, int col) {
        return row > 0 && row < rows - 1 && col > 0 && col < cols - 1 && !maze[row][col];
    }

    public void addToGameScene(GameScene gameScene, int tileSize) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!maze[row][col]) { // Wall
                    Rectangle wall = new Rectangle(tileSize, tileSize);
                    wall.setFill(Color.DARKGRAY); // Wall color
                    wall.setStroke(Color.BLACK); // Wall border
                    gameScene.addObject(new ImageView(), col * tileSize, row * tileSize, tileSize, tileSize, tileSize, tileSize, 0, 0);
                }
            }
        }
    }

    public void printMaze() {
        for (int i = 0; i < rows; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < cols; j++) {
                if (i == 1 && j == 0) {
                    line.append("E "); // Entrance
                } else if (i == rows - 2 && j == cols - 1) {
                    line.append(" X"); // Exit
                } else if (!maze[i][j]) {
                    if (i % 2 == 0) {
                        line.append(j % 2 == 0 ? "+" : "__"); // Horizontal walls
                    } else {
                        line.append(j % 2 == 0 ? "|" : "  "); // Vertical walls
                    }
                } else {
                    line.append("  "); // Path
                }
            }
            System.out.println(line);
        }
    }
}
