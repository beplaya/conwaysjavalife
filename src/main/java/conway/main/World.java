package conway.main;

public class World {

    private final Cell[][] grid;

    public World(int rows, int columns) {
        grid = new Cell[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                grid[r][c] = new Cell(r,c);
            }
        }
    }

    public Cell getCell(int row, int column) {
        return grid[row][column];
    }
}
