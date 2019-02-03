package conway.main;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private static final int MIN_POPULATION = 2;
    private static final int MAX_POPULATION = 3;
    private static final int FERTILE_POPULATION = 3;

    private boolean alive;

    public final int row;
    public final int column;
    private boolean tempAlive;
    private boolean willBeAlive;

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
        if (isAlive()) {
            markToLive();
        }

        if (isUnderpopulated(world) || isOverpopulated(world)) {
            markToDie();
        } else if (isInFertileTerritory(world)) {
            markToLive();
        }
    }

    private void markToLive() {
        willBeAlive = true;
    }

    private void markToDie() {
        willBeAlive = false;
    }

    public int numberOfLivingNeighbors(World world) {
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
        Delta[] deltas = new Delta[]{
                new Delta(-1, -1), new Delta(-1, 0), new Delta(-1, 1),
                new Delta(0, -1), new Delta(0, 1),
                new Delta(1, -1), new Delta(1, 0), new Delta(1, 1),
        };
        for (int rdi = 0; rdi < deltas.length; rdi++) {
            int R = deltas[rdi].row + row;
            int C = deltas[rdi].col + column;
            if (R < 0) {
                R += world.numberOfRows();
            }
            if (R >= world.numberOfRows()) {
                R -= world.numberOfRows();
            }
            if (C < 0) {
                C += world.numberOfColumns();
            }
            if (C >= world.numberOfColumns()) {
                C -= world.numberOfColumns();
            }
            Cell neighbor = world.getCell(R, C);
            neighbors.add(neighbor);
        }

        return neighbors;
    }

    private class Delta {
        public final int row;
        public final int col;

        private Delta(int row, int col) {
            this.row = row;
            this.col = col;
        }

    }

    boolean isUnderpopulated(World world) {
        return numberOfLivingNeighbors(world) < MIN_POPULATION;
    }

    boolean isOverpopulated(World world) {
        return numberOfLivingNeighbors(world) > MAX_POPULATION;
    }

    boolean isInFertileTerritory(World world) {
        return numberOfLivingNeighbors(world) == FERTILE_POPULATION;
    }

    public boolean willBeAlive() {
        return willBeAlive;
    }

    public void commit() {
        alive = willBeAlive;
    }
}

