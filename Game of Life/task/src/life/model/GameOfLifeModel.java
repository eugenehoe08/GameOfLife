package life.model;

import java.util.Random;

public class GameOfLifeModel {
    final char alive = 'O';
    final char dead = ' ';
    char[][] universe;
    char[][] newUniverse;
    int size;
    int generation;

    // Creating a new board with random alive and dead
    public GameOfLifeModel(int size) {
        this.size = size;
        this.universe = new char[size][size];
        this.generation = 0;
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (random.nextBoolean()) {
                    universe[i][j] = alive;
                } else {
                    universe[i][j] = dead;
                }
            }
        }
    }

    public void startNewGame() {
        this.universe = new char[size][size];
    }

    private void createNewBoard() {

        // Generating new board
        this.newUniverse = new char[10][10];
        generation++;
    }

    // Generate new board based on rules
    public void generateNewBoard() {
        createNewBoard();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (universe[i][j] == alive) {
                    if (survive(i, j)) {
                        newUniverse[i][j] = alive;
                    } else {
                        newUniverse[i][j] = dead;
                    }
                } else {
                    if (reborn(i, j)) {
                        newUniverse[i][j] = alive;
                    } else {
                        newUniverse[i][j] = dead;
                    }
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                universe[i][j] = newUniverse[i][j];
            }
        }
    }

    // Check if alive cell can survive
    private boolean survive(int i, int j) {
        int count = noOfAliveCellsAroundCell(i, j);
        return (count >= 2 && count <= 3);
    }

    // find number of alive cells around each cell
    private int noOfAliveCellsAroundCell(int i, int j) {
        int count = 0; // Number of alive neighbours

        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                if (!(k == 0 && l == 0)) {
                    if (universe[((k + i) % size + size) % size][((l + j) % size + size) % size] == alive) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int noOfAliveCellsInMatrix() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (universe[i][j] == alive) {
                    count++;
                }
            }
        }
        return count;
    }

    // Check if dead cell can reborn
    private boolean reborn(int i, int j) {
        int count = noOfAliveCellsAroundCell(i, j);
        return count == 3;
    }

    // print the board
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(universe[i][j]);
            }
            System.out.println();
        }
    }

    public char[][] getUniverse() {
        return universe;
    }

    public char[][] getNewUniverse() {
        return newUniverse;
    }

    public int getSize() {
        return size;
    }

    public int getGeneration() {
        return generation;
    }
}
