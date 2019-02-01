package conway.main;

public class World {

    private final Cell[][] grid;

    public World(int rows, int columns) {
        grid = new Cell[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                grid[r][c] = new Cell(r, c);
            }
        }
    }

    public Cell getCell(int row, int column) {
        Cell cell = null;
        if (row >= 0 && row < grid.length) {
            if (column >= 0 && column < grid[0].length) {
                cell = grid[row][column];
            }
        }
        return cell;
    }
}
