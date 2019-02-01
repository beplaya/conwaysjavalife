package conway.main;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    public static final int MIN_POPULATION = 2;

    private boolean alive;
    public final int row;
    public final int column;

    public Cell(int row, int column) {

        this.row = row;
        this.column = column;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void cycle(World world) {
        if (isUnderpopulated(world)) {
            die();
        }
    }

    private void die() {
        alive = false;
    }

    int numberOfLivingNeighbors(World world) {
        List<Cell> neighbors = getNeighbors(world);
        int livingCount = 0;
        for (Cell c : neighbors) {
            if (c.isAlive()) {
                livingCount++;
            }
        }
        return livingCount;
    }

    List<Cell> getNeighbors(World world) {
        List<Cell> neighbors = new ArrayList<Cell>();
        Cell neighbor;
        for (int rowOff = -1; rowOff <= 1; rowOff++) {
            for (int colOff = -1; colOff <= 1; colOff++) {
                if (rowOff != 0 || colOff != 0) {
                    neighbor = world.getCell(row + rowOff, column + colOff);
                    if (neighbor != null) {
                        neighbors.add(neighbor);
                    }
                }
            }
        }

        return neighbors;
    }

    boolean isUnderpopulated(World world) {
        return numberOfLivingNeighbors(world) < MIN_POPULATION;
    }
}

